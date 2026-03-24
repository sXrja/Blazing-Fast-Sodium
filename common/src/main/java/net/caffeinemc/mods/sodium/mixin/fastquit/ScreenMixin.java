package net.caffeinemc.mods.sodium.mixin.fastquit;

import net.caffeinemc.mods.sodium.fastquit.FastQuitOverlay;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class ScreenMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void fastquit$instantDisconnect(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        FastQuitOverlay.render(guiGraphics);
    }
}