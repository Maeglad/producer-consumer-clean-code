package org.example.domain.util;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.bytebuddy.utility.nullability.NeverNull;

import java.util.Queue;

@Singleton
public class Buffer {
    private final Queue<Command> queue;
    private final int capacity;

    @Inject
    public Buffer(@NeverNull Queue<Command> queue,
                  int capacity) {
        this.queue = queue;
        this.capacity = capacity;
    }

    public synchronized void offer(@NeverNull Command command) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }

        queue.offer(command);
        notifyAll();
    }


    @NeverNull
    public synchronized Command poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }

        // this cannot
        Command command = queue.poll();
        notifyAll();
        return command;
    }
}
