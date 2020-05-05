package edu.matc.threaddemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

public class InterruptTest {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Test
    public void testThread() throws Exception {
        RollerCoaster rollerCoaster = new RollerCoaster();
        Thread thread = new Thread(rollerCoaster);
        thread.start();

        try {
            Thread.sleep(60000);
        } catch (InterruptedException x) {

        }

        thread.interrupt();
    }
}
