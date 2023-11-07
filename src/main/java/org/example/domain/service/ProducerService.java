package org.example.domain.service;

import com.google.inject.Inject;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.util.*;

public class ProducerService {
    private final Buffer buffer;
    private final int resetCount;

    private final Object lock = new Object();

    @Inject
    public ProducerService(@NeverNull Buffer buffer,
                           @NeverNull Integer resetCount) {
        this.buffer = buffer;
        this.resetCount = resetCount;
    }

    public void start() {
        new Thread(() -> {
            try {
                for (long i = 0L; i < 10000; ++i) {
                    if (i % resetCount == 0) {
                        buffer.offer(new DeleteCommand());
                    } else {
                        buffer.offer(generateRandomCommand(i));
                    }
                    synchronized (lock) {
                        lock.wait((int)(Math.random()*50) + 1);
                    }
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }).start();
    }

    @NeverNull
    private Command generateRandomCommand(long id) {
        double randomValue = Math.random();
        if (randomValue > 0.8) {
            return new PrintCommand();
        } else {
            return new AddCommand(id, "1", "user number " + id);
        }
    }
}
