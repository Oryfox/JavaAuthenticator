import dev.samstevens.totp.time.SystemTimeProvider;

public class Time {

    public static long getSystemTime() {
        return new SystemTimeProvider().getTime();
    } //Returns time in s

    public static int getRemainingTime() {
        long time = getSystemTime();
        return (int) (time % Math.floorDiv(time, 30));
    } //Time until next reset. 30s period
}
