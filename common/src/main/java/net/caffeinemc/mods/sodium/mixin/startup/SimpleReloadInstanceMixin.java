package net.caffeinemc.mods.sodium.mixin.startup;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

@Mixin(targets = "net.minecraft.server.packs.resources.SimpleReloadInstance")
public class SimpleReloadInstanceMixin {

    @Inject(method = "done", at = @At("HEAD"), cancellable = true)
    private void blazingfast$skipBlocking(CallbackInfoReturnable<CompletableFuture<?>> cir) {

        // 👉 fake "already finished"
        cir.setReturnValue(CompletableFuture.completedFuture(null));
    }
}