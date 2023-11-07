package org.example.domain.util;

import net.bytebuddy.utility.nullability.NeverNull;

public final class AddCommand implements Command {

    public final @NeverNull Long userId;
    public final @NeverNull String userGroupId;
    public final @NeverNull String userName;

    public AddCommand(
            @NeverNull Long userId,
            @NeverNull String userGroupId,
            @NeverNull String userName) {
        this.userId = userId;
        this.userGroupId = userGroupId;
        this.userName = userName;
    }
}
