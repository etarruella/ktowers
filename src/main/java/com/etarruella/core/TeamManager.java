package com.etarruella.core;

import com.etarruella.core.entities.KTeam;
import com.etarruella.exceptions.KTeamException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeamManager {

    private static Map<UUID, KTeam> teams = new HashMap<>();

    public static KTeam getKTeam(UUID uuid) throws KTeamException {
        if (teams.get(uuid) == null) {
            throw new KTeamException("This team doesn't exist!");
        }
        return teams.get(uuid);
    }

    public static void addKTeam(KTeam kTeam) {
        teams.put(kTeam.getUuid(), kTeam);
    }

}
