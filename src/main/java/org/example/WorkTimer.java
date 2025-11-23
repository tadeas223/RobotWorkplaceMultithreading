package org.example;

import java.util.Random;

public class WorkTimer {
    private static WorkTimer instance = null;

    private final Random rand = new Random();
    private int min = 0;
    private int max = 0;

    private boolean isSet = false;

    private WorkTimer() {}

    public static WorkTimer getInstance() {
        if(instance == null) {
            instance = new WorkTimer();
        }
        return instance;
    }

    public static int generateTime() {
        WorkTimer instance = getInstance();

        if(!instance.isSet) {
            return 0;
        }

        return instance.getRand().nextInt(instance.getMin(), instance.getMax());
    }

    public static void setMinMax(int min, int max) {
        WorkTimer instance = getInstance();
        instance.isSet = true;
        instance.setMax(max);
        instance.setMax(min);
    }

    public Random getRand() {
        return rand;
    }

    public void setMinMax(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
