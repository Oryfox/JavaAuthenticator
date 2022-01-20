package de.oryfox.totpauthenticator;

import dev.samstevens.totp.time.SystemTimeProvider;

public class Time {

    public static long getSystemTime() {
        return new SystemTimeProvider().getTime();
    } //Returns time in s

    public static int getRemainingTime() {
        long time = getSystemTime();
        return (int) (30 - time % 30);
    } //de.oryfox.totpauthenticator.Time until next reset. 30s period
}
