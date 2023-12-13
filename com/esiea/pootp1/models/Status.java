package com.esiea.pootp1.models;

import java.util.Map;

public enum Status {
    NORMAL, POISONED, PARALYZED, BURNED;

    private static Map<String, Status> statusConfigText = Map.ofEntries(
        Map.entry("Normal"   , NORMAL   ),
        Map.entry("Poisoned" , POISONED ),
        Map.entry("Paralyzed", PARALYZED),
        Map.entry("Burned"   , BURNED   )
    );

    private static Map<Status, String> statusDisplayText = Map.ofEntries(
        Map.entry(NORMAL   , "Normal"   ),
        Map.entry(POISONED , "Empoisoné"),
        Map.entry(PARALYZED, "Paralysé" ),
        Map.entry(BURNED   , "Brûlé"    )
    );

    public static Map<String, Status> getStatusConfigText() {
        return Status.statusConfigText;
    }

    public static Map<Status, String> getStatsDisplayText() {
        return Status.statusDisplayText;
    }
}
