package org.example.domain.use_case;

import com.google.inject.Inject;
import org.example.domain.model.User;
import org.example.domain.repository.UserRepository;

import java.util.List;

public class GetUsersUseCase {
    private final UserRepository userRepository;

    @Inject
    public GetUsersUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> invoke() {
        return userRepository.getUsers();
    }
}
