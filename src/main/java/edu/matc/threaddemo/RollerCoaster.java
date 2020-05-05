package edu.matc.threaddemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

// Implement Runnable
public class RollerCoaster implements Runnable {
    private final Logger logger = LogManager.getLogger(this.getClass());
    // Add code to the run method that you want to execute when the thread is started.

    private final int MAX_PASSENGERS_IN_PARK = 10;

    private int peopleCompletedRide = 0;

    private Car car1;
    private Car car2;
    ArrayBlockingQueue<Passenger> passengerQueue;
    ArrayBlockingQueue<Car> carQueue;
    ArrayBlockingQueue<Car> carsOnTrack;

    @Override
    public void run() {
        openPark();

        while(true) {
            try {
                Car carToGo;
                Passenger passengerToAdd;
                Thread car1Thread;
                Thread car2Thread;

                while (true) {
                    if (carQueue.size() > 0 && passengerQueue.size() > 0) {
                        carToGo = carQueue.peek();
                        if (carToGo.getPeopleInCar().size() < carToGo.getMAX_CAPACITY()) {
                            passengerToAdd = passengerQueue.poll();
                            carToGo.addPassenger(passengerToAdd);
                            logger.info("Passenger " + passengerToAdd.getId() + " added to Car " + carToGo.getId());
                        }

                        if (carToGo.getPeopleInCar().size() == carToGo.getMAX_CAPACITY() && carToGo.canDepart()) {
                            if (carToGo.getId() == 1) {
                                car1Thread = new Thread(carQueue.poll());
                                car1Thread.start();
                            } else {
                                car2Thread = new Thread(carQueue.poll());
                                car2Thread.start();
                            }
                        }
                    }

                    Thread.sleep(1);
                }
            } catch (InterruptedException e) {
                logger.info("Amusement Park Closed!");
                logger.info(getPeopleCompletedRide() + " rode the Roller Coaster today!");
                logger.info(carQueue.size() + " cars in queue");
                logger.info(passengerQueue.size() + " pass in queue");
                return;
            }
        }
    }

    private void openPark() {
        logger.info("Amusement Park Open!");

        passengerQueue = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < MAX_PASSENGERS_IN_PARK; i++) {
            passengerQueue.add(new Passenger(i, this));
        }

        car1 = new Car(1, true, this);
        car2 = new Car(2, false, this);

        car1.setPairingCar(car2);
        car2.setPairingCar(car1);

        carQueue = new ArrayBlockingQueue<>(2);
        carQueue.add(car1);
        carQueue.add(car2);

        carsOnTrack = new ArrayBlockingQueue<>(2);
    }

    public void addCarToQueue(Car car) {
        carQueue.add(car);
    }

    public void addPassengerToQueue(Passenger pass) {
        passengerQueue.add(pass);
        logger.info("Passenger " + pass.getId() + " is in line");
    }

    public synchronized void incrementCounter() {
        peopleCompletedRide += 4;
    }

    public int getPeopleCompletedRide() {
        return peopleCompletedRide;
    }

    public void setPeopleCompletedRide(int peopleCompletedRide) {
        this.peopleCompletedRide = peopleCompletedRide;
    }
}
