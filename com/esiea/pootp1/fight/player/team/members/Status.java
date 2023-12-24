package com.esiea.pootp1.fight.player.team.members;

import java.util.Map;

public enum Status {
    NORMAL, POISONED, PARALYZED, BURNED, HIDDEN;

    private static Map<String, Status> statusConfigText = Map.ofEntries(
        Map.entry("Normal"   , NORMAL   ),
        Map.entry("Poisoned" , POISONED ),
        Map.entry("Paralyzed", PARALYZED),
        Map.entry("Burned"   , BURNED   ),
        Map.entry("Hidden"   , HIDDEN   )
    );

    private static Map<Status, String> statusDisplayText = Map.ofEntries(
        Map.entry(NORMAL   , "Normal"   ),
        Map.entry(POISONED , "Empoisonné"),
        Map.entry(PARALYZED, "Paralysé" ),
        Map.entry(BURNED   , "Brûlé"    ),
        Map.entry(HIDDEN   , "Caché"    )
    );

    public static Map<String, Status> getStatusConfigText() {
        return Status.statusConfigText;
    }

    public static Map<Status, String> getStatsDisplayText() {
        return Status.statusDisplayText;
    }
}
