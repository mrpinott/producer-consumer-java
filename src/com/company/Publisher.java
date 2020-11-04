package com.company;

import java.util.Random;
import static com.company.Main.lock;
import static com.company.Main.publishingQueue;

/**
 * Publisher class publishes to a queue of strings.
 */
public class Publisher extends Thread implements ZoomThread {
    private boolean isCancelled = false;
    @Override
    public void run() {
        super.run();
        synchronized (lock) {
            try {
                while(!isCancelled) {
                    if (publishingQueue.isEmpty()) {
                        publishingQueue.add("item " + new Random().nextInt(500));
                        System.out.println("new " + publishingQueue.peek() + " produced!");
                        lock.notify();
                        // used for console readability
                        sleep(500);
                    } else
                        lock.wait();
                }
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

    @Override
    public void cancel () {
        isCancelled = true;
    }
}
