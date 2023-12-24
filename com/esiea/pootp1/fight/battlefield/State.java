package com.esiea.pootp1.fight.battlefield;

import java.util.Map;

public enum State {
    NORMAL, FLOODED;

    private static Map<String, State> stateConfigText = Map.ofEntries(
        Map.entry("Normal"  , NORMAL ),
        Map.entry("Flooded" , FLOODED)
    );

    private static Map<State, String> stateDisplayText = Map.ofEntries(
        Map.entry(NORMAL , "Normal" ),
        Map.entry(FLOODED, "Innondé")
    );

    public static Map<String, State> getStateConfigText() {
        return State.stateConfigText;
    }

    public static Map<State, String> getStateDisplayText() {
        return State.stateDisplayText;
    }
}
