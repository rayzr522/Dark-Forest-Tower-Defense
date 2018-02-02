package me.rayzr522.towerdefense;

public class Timer {

    /**
     * The ticks-per-second of this timer
     */
    private int tps;

    private long tickTime;
    private long lastTime;

    public Timer(int tps) {

        this.tps = tps;

        // 1,000,000,000 (1 billion) nano seconds in a second
        tickTime = 1000000000L / tps;

        lastTime = System.nanoTime();

    }

    /**
     * Returns the ticks-per-second of this timer
     *
     * @return {@link #tps}
     */
    public int getTps() {
        return tps;
    }

    public void tick() {

        long ellapsedTime = System.nanoTime() - lastTime;
        long sleepTime = tickTime - ellapsedTime;

        if (sleepTime > 0L) {

            try {

                Thread.sleep((sleepTime - sleepTime % 1000000) / 10000000, (int) sleepTime % 1000000);

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

        lastTime = System.nanoTime();

    }
}
