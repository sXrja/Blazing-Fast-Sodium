package net.caffeinemc.mods.sodium.mixin.fastquit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(method = "disconnect", at = @At("HEAD"))
    private void fastquit$onDisconnect(CallbackInfo ci) {

        Minecraft client = (Minecraft)(Object)this;
        IntegratedServer server = client.getSingleplayerServer();

        if (server == null) return;

        // nur trigger – echte logik im server mixin
    }
}