package net.caffeinemc.mods.sodium.fastquit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

public class FastQuitOverlay {

    public static void render(GuiGraphics g) {

        if (!FastQuitManager.isSaving()) return;

        Minecraft mc = Minecraft.getInstance();

        String text = "Saving...";

        int x = mc.getWindow().getGuiScaledWidth() - 80;
        int y = 10;

        g.drawString(mc.font, text, x, y, 0xFFFFFF);
    }
}