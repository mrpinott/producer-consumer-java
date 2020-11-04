package com.company;

import static com.company.Main.lock;
import static com.company.Main.publishingQueue;

/**
 * Consumer class consumes from the publishing queue,
 * and print/display the de queued item to the console.
 */
public class Consumer extends Thread implements ZoomThread {
    private boolean isCancelled = false;

    @Override
    public void run() {
        super.run();
        synchronized (lock) {
            try {
                while(!isCancelled) {
                    if (!publishingQueue.isEmpty()) {
                        System.out.println("Consuming item: " + publishingQueue.remove());
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
