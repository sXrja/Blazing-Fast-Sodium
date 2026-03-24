package net.caffeinemc.mods.sodium.mixin.fastquit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.server.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow
    private IntegratedServer singleplayerServer;

    @Inject(
            method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;ZZ)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void fastquit$instantDisconnect(Screen screen, boolean bl, boolean bl2, CallbackInfo ci) {

        Minecraft mc = (Minecraft)(Object)this;

        if (mc.getSingleplayerServer() != null) {

            IntegratedServer server = mc.getSingleplayerServer();

            // 👉 Background save thread
            new Thread(() -> {
                while (!server.isShutdown()) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ignored) {}
                }
            }, "fastquit-save").start();

            // 👉 sofort TitleScreen
            mc.setScreen(new TitleScreen());

            // 👉 disconnect state cleanen
            mc.level = null;
            mc.player = null;
            this.singleplayerServer = null;

            // ❌ verhindert original while-loop
            ci.cancel();
        }
    }
}
