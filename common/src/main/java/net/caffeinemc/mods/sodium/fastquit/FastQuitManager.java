package net.caffeinemc.mods.sodium.fastquit;

import net.minecraft.client.server.IntegratedServer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FastQuitManager {

    private static final Set<IntegratedServer> SAVING =
            Collections.synchronizedSet(new HashSet<>());

    public static void markSaving(IntegratedServer server) {
        SAVING.add(server);
    }

    public static void unmarkSaving(IntegratedServer server) {
        SAVING.remove(server);
    }

    public static boolean isSaving() {
        return !SAVING.isEmpty();
    }
}