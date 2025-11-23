package org.example.robots;

import org.example.WorkTimer;

public class Robot extends Thread {
    private RobotStatus status = RobotStatus.IDLE;

    private final RobotDispatcher robotDispatcher;


    private final int id;

    public Robot(RobotDispatcher robotDispatcher, int id) {
        this.robotDispatcher = robotDispatcher;
        this.id = id;
    }

    @Override
    public void run() {
        while(robotDispatcher.isRunning() && !Thread.currentThread().isInterrupted()) {
            RobotTask task;
            try {
                task = robotDispatcher.pollTask();
            } catch (InterruptedException e) {
                break;
            }

            work(task);
        }
    }

    private void work(RobotTask task) {
        startDelivering();

        deliver();

        goBack();

        startIdle();
    }

    public RobotStatus getStatus() {
        return status;
    }

    private void startDelivering() {
        status = RobotStatus.DELIVERING;

        // simulate the robot delivery
        try {
            Thread.sleep(WorkTimer.generateTime());
        } catch (InterruptedException e) {
            return;
        }
    }

    private void deliver() {
        status = RobotStatus.DELIVERED;

        // simulate the robot delivered
        try {
            Thread.sleep(WorkTimer.generateTime());
        } catch (InterruptedException e) {
            return;
        }
    }

    private void goBack() {
        status = RobotStatus.RETURNING;

        // simulate the robot returning
        try {
            Thread.sleep(WorkTimer.generateTime());
        } catch (InterruptedException e) {
            return;
        }
    }

    private void startIdle() {
        status = RobotStatus.IDLE;

        //remove comments if the robot needs break between tasks
//        // simulate the robot idling
//        try {
//            Thread.sleep(WorkTimer.generateTime());
//        } catch (InterruptedException e) {
//            // if sleep fails just continue
//        }
    }

    public int getRobotId() {
        return id;
    }
}
