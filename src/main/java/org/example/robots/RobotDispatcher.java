package org.example.robots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class RobotDispatcher {

    private final ArrayList<Robot> robots = new ArrayList<>();

    private final Queue<RobotTask> tasks = new LinkedList<>();

    private boolean running = true;

    public RobotDispatcher(int numOfRobots) {
        for(int i = 0; i < numOfRobots; i++) {
            Robot r = new Robot(this, i);
            robots.add(r);
            r.start();
        }
    }

    public void enqueueTask(RobotTask task) {
        synchronized (tasks) {
            tasks.add(task);
            tasks.notify();
        }
    }

    public void shutdown() {
        running = false;

        for(Robot r : robots) {
            r.interrupt();
        }
    }

    public RobotTask pollTask() throws InterruptedException {
        RobotTask task;
        synchronized (tasks) {
            while(tasks.isEmpty()) {
                tasks.wait();
            }

            task = tasks.poll();
        }
        return task;
    }

    public HashMap<Integer, RobotStatus> getRobotStatus() {
        HashMap<Integer, RobotStatus> result = new HashMap<>();
        for(Robot r : robots) {
            result.put(r.getRobotId(), r.getStatus());
        }
        return result;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public boolean isRunning() {
        return running;
    }
}
