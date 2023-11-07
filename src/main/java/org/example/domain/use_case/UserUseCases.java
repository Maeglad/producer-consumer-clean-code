package org.example.domain.use_case;

import com.google.inject.Singleton;
import net.bytebuddy.utility.nullability.NeverNull;

@Singleton
public record UserUseCases(
        @NeverNull GetUsersUseCase getUsersUseCase,
        @NeverNull CreateUserUseCase createUserUseCase,
        @NeverNull DeleteAllUsersUseCase deleteAllUsersUseCase
) {}
