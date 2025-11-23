package org.example;

import org.example.robots.RobotDispatcher;
import org.example.robots.RobotStatus;
import org.example.workplaces.Workplace;

import java.util.ArrayList;
import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        WorkTimer.setMinMax(1000, 2000);

        RobotDispatcher robotDispatcher = new RobotDispatcher(5);

        ArrayList<Workplace> workplaces = new ArrayList<>();

        for(int i = 0; i < 6; i++) {
            Workplace w = new Workplace(robotDispatcher, Integer.toString(i));

            workplaces.add(w);
            w.start();
        }

        // shutdown workplaces and robot dispatcher after some time
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for(Workplace w : workplaces) {
                    w.shutdown();
                }

                robotDispatcher.shutdown();
            }
        });
        t.start();

        // show robot activity to console
        while(robotDispatcher.isRunning()) {
            Thread.sleep(1000);
            HashMap<Integer, RobotStatus> robots = robotDispatcher.getRobotStatus();

            System.out.println("tasks: " + robotDispatcher.getNumberOfTasks());
            System.out.println("ROBOT ID | STATUS");
            for(Integer id : robots.keySet()) {
                System.out.println(id + " " + robots.get(id).toString());
            }

            System.out.println();
        }

    }
}