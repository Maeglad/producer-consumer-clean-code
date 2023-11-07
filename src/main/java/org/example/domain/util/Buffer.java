package org.example.domain.model;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.util.Command;

import java.util.Queue;

@Singleton
public class Buffer {
    private Queue<Command> queue;
    private int capacity;

    @Inject
    public Buffer(@NeverNull Queue<Command> queue,
                  int capacity) {
        this.queue = queue;
        this.capacity = capacity;
    }

    public synchronized void offer(@NeverNull Command command) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Buffer full");
            wait();
        }

        queue.offer(command);
        notifyAll();
    }


    @NeverNull
    public synchronized Command poll() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Buffer empty");
            wait();
        }

        // this cannot
        Command command = queue.poll();
        notifyAll();
        return command;
    }
}
