package net.caffeinemc.mods.sodium.fabric;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class LazyDFUPreLaunch implements PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        System.out.println("[Blazing Fast Sodium] Lazy DFU activated – DFU will be loaded on demand.");
        System.setProperty("mixin.legacy.lazydfu", "true");
    }
}