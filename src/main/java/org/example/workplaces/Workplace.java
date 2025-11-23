package org.example.workplaces;

import org.example.Material;
import org.example.WorkTimer;
import org.example.robots.RobotDispatcher;
import org.example.robots.RobotStatus;
import org.example.robots.RobotTask;

public class Workplace extends Thread {
    private final RobotDispatcher robotDispatcher;
    private final String address;

    private boolean running = true;

    public Workplace(RobotDispatcher robotDispatcher, String address) {
        this.robotDispatcher = robotDispatcher;
        this.address = address;
    }

    @Override
    public void run() {
        while(running) {
            try {
                Thread.sleep(3L *WorkTimer.generateTime());
            } catch (InterruptedException e) {
                break;
            }

            // after work request more materials
            robotDispatcher.enqueueTask(new RobotTask(address, new Material("good material")));
        }
    }

    public void shutdown() {
        running = false;
        interrupt();
    }
}
