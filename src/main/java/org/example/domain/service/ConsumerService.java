package org.example.domain.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.model.Buffer;
import org.example.domain.model.User;
import org.example.domain.use_case.UserUseCases;
import org.example.domain.util.AddCommand;
import org.example.domain.util.Command;
import org.example.domain.util.DeleteCommand;
import org.example.domain.util.PrintCommand;

import java.util.List;

/**
 * Asynchronous consumer that consumes from provided buffer and
 * sleeps if the buffer is empty
 */
public class ConsumerService {
    private final Buffer buffer;
    private final UserUseCases userUseCases;

    private final Object lock = new Object();

    @Inject
    public ConsumerService(@NeverNull Buffer buffer,
                           @NeverNull UserUseCases userUseCases) {
        this.buffer = buffer;
        this.userUseCases = userUseCases;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Command command = buffer.poll();
                        if (command instanceof AddCommand) {
                            User user = new User(
                                    ((AddCommand) command).userId,
                                    ((AddCommand) command).userGroupId,
                                    ((AddCommand) command).userName
                            );
                            userUseCases.createUserUseCase().invoke(user);
                        } else if (command instanceof DeleteCommand) {
                            userUseCases.deleteAllUsersUseCase().invoke();
                        } else if (command instanceof PrintCommand) {
                            List<User> users = userUseCases.getUsersUseCase().invoke();
                            for (User user : users) {
                                System.out.printf("id: %d, group id: %s, name: %s\n",
                                        user.getUserId(),
                                        user.getUserGuid(),
                                        user.getUserName());
                            }
                        }

                        synchronized (lock) {
                            lock.wait((int)(Math.random()*50) + 1 );
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();
    }
}
