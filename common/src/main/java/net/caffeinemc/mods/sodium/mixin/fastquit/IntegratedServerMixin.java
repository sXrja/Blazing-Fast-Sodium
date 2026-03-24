package net.caffeinemc.mods.sodium.mixin.fastquit;

import net.caffeinemc.mods.sodium.fastquit.FastQuitManager;
import net.minecraft.client.server.IntegratedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IntegratedServer.class)
public abstract class IntegratedServerMixin {

    @Inject(method = "stopServer", at = @At("HEAD"))
    private void fastquit$onStop(CallbackInfo ci) {

        IntegratedServer server = (IntegratedServer)(Object)this;

        FastQuitManager.markSaving(server);

        new Thread(() -> {
            try {
                while (!server.isStopped()) {
                    Thread.sleep(50);
                }
            } catch (Exception ignored) {}

            FastQuitManager.unmarkSaving(server);

        }, "fastquit-wait-thread").start();
    }
}