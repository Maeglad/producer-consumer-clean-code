package org.example.domain.use_case;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.bytebuddy.utility.nullability.NeverNull;
import org.example.domain.repository.UserRepository;



@Singleton
public class DeleteAllUsersUseCase {

    private final UserRepository userRepository;

    @Inject
    public DeleteAllUsersUseCase(@NeverNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void invoke() {
        userRepository.deleteAllUsers();
    }
}
