package org.example.domain.use_case;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.model.User;
import org.example.domain.repository.UserRepository;

@Singleton
public class CreateUserUseCase {

    private UserRepository userRepository;

    @Inject
    public CreateUserUseCase(@NeverNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void invoke(@NeverNull User newUser) {
        userRepository.addUser(newUser);
    }
}
