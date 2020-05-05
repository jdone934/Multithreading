package edu.matc.threaddemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Passenger implements Runnable {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private int id;
    private RollerCoaster coaster;

    public Passenger (int id, RollerCoaster coaster) {
        this.id = id;
        this.coaster = coaster;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(randomTime());
            coaster.addPassengerToQueue(this);
        }catch (Exception e) {

        }
    }

    private int randomTime() {
        int min = 0;
        int max = 5000;
        Random random = new Random();

        int randomNum = random.nextInt(max + 1);
        return randomNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
