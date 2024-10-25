package com.etarruella.core.entities;

public class KPoint {

    private final KPlayer kPlayer;
    private final KTeam kTeam;
    private final Long timestampLong;

    private boolean valid;

    public KPoint(KPlayer kPlayer) {
        this.kPlayer = kPlayer;
        this.kTeam = kPlayer.getKTeam();
        this.timestampLong = System.currentTimeMillis();

        valid = true;
    }

    public KPlayer getKPlayer() {
        return kPlayer;
    }

    public KTeam getKTeam() {
        return kTeam;
    }

    public Long getTimestampLong() {
        return timestampLong;
    }

    public boolean isValid() {
        return valid;
    }

    public void cancelPoint() {
        valid = false;
    }

}
