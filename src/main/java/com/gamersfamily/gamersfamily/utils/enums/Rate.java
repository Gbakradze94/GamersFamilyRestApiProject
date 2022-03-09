package com.gamersfamily.gamersfamily.utils.enums;

public enum Rate {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final long rate;

    Rate(long rate) {
        this.rate = rate;
    }

    public long getRate() {
        return rate;
    }
}
