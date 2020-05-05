package edu.matc.threaddemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread extends Thread{
    private final Logger logger = LogManager.getLogger(this.getClass());

    public void run() {
        logger.info("In edu.matc.threaddemo.MyThread");
    }
}
