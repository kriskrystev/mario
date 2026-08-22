package util;

public class Time {
    public static float timeStarted = System.nanoTime();

    /**
     * Returns the number of seconds elapsed since {@link #timeStarted}, i.e. since this class was loaded.
     *
     * @return elapsed time in seconds
     */
    public static float getTime() {
        return (float) ((System.nanoTime() - timeStarted) * 1E-9);
    }
}
