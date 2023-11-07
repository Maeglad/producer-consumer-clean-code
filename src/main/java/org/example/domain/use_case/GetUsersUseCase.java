package org.example.domain.use_case;

import com.google.inject.Inject;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.model.User;
import org.example.domain.repository.UserRepository;

public class GetUsersUseCase {
    private UserRepository userRepository;

    @Inject
    public GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void invoke(@NeverNull User newUser) {
        userRepository.getUsers();
    }
}
