package com.company;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem 4
 * Implement a scenario where you have 1 publisher, publishing to a queue of strings,
 * and a consumer consuming from the queue, and print/display the de queued item to the console.
 * Notes : The publisher and the consumer must run concurrently, and produce/consume concurrently.
 *
 * Main driver class
 * Assumption: threads are inside package and will access package private vars.
 */
public class Main {
    // declare vars
    static Queue<String> publishingQueue = new LinkedList<>();
    final static Object lock = new Object();

    public static void main(String[] args) {
        // instantiate and run threads
        Publisher producer = new Publisher();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();

        // running threads for a set amount of time
        for (int i = 0; i < 50; ++i) {
            try {
                // sleep thread for 1000 milliseconds
                Thread.sleep(500);
            } catch (Exception e) {
                // do nothing
            }
        }
        // stop threads
        producer.cancel();
        consumer.cancel();
    }
}
