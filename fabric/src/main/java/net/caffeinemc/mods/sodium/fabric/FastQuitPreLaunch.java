package net.caffeinemc.mods.sodium.fabric;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class FastQuitPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        System.out.println("[Blazing Fast Sodium] Fast Quit activated – instant world unloading enabled.");

        // Fast Quit aktivieren
        System.setProperty("mixin.legacy.fastquit", "true");
    }
}