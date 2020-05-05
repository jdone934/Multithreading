package edu.matc.threaddemo;

public class RollerCoasterCarLeaveManager implements Runnable {
    @Override
    public void run() {
        try {
            while(true);
        } catch (Exception e) {

        }
    }

    public void toggleWhenCarCanGo(Car waitingCar) {
        try{
            Thread.sleep(4000);
            waitingCar.setCanDepart(true);
        } catch (Exception e){

        }
    }
}
