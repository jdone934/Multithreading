package edu.matc.threaddemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

public class Car implements Runnable {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final int MAX_CAPACITY = 4;

    private HashSet<Passenger> peopleInCar = new HashSet<Passenger>();
    private int id;
    private boolean onTrack = false;
    private boolean canDepart;
    private Car pairingCar;
    private RollerCoaster coaster;

    private final int TIME_BETWEEN_CAR_DEPART = 4000;
    private final int TIME_ON_RIDE = 12000;

    public Car(int id, boolean canDepart, RollerCoaster coaster) {
        this.id = id;
        this.canDepart = canDepart;
        this.coaster = coaster;
    }

    @Override
    public void run() {
        try {
            logger.info("Car " + id + " has departed");
            Thread.sleep(TIME_BETWEEN_CAR_DEPART);
            pairingCar.setCanDepart(true);
            Thread.sleep(TIME_ON_RIDE - TIME_BETWEEN_CAR_DEPART);
            passengersDepart();
        } catch (Exception e){}
    }

    private void passengersDepart() {
        logger.info("Car " + id + " has finished the ride");
        for (Passenger passenger: peopleInCar) {
            Thread passThread = new Thread(passenger);
            passThread.start();
        }
        peopleInCar.clear();
        coaster.incrementCounter();
        coaster.addCarToQueue(this);
    }

    public int getMAX_CAPACITY() {
        return MAX_CAPACITY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOnTrack() {
        return onTrack;
    }

    public void setOnTrack(boolean onTrack) {
        this.onTrack = onTrack;
    }

    public HashSet<Passenger> getPeopleInCar() {
        return peopleInCar;
    }

    public void setPeopleInCar(HashSet<Passenger> peopleInCar) {
        this.peopleInCar = peopleInCar;
    }

    public void addPassenger(Passenger pass) {
        peopleInCar.add(pass);
    }

    public boolean canDepart() {
        return canDepart;
    }

    public void setCanDepart(boolean canDepart) {
        this.canDepart = canDepart;
    }

    public Car getPairingCar() {
        return pairingCar;
    }

    public void setPairingCar(Car pairingCar) {
        this.pairingCar = pairingCar;
    }
}
