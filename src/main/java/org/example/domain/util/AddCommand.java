package org.example.domain.util;

import net.bytebuddy.utility.nullability.NeverNull;

public final class AddCommand implements Command {

    public final @NeverNull Long userId;
    public final @NeverNull Long userGroupId;
    public final @NeverNull String userName;

    public AddCommand(
            @NeverNull Long userId,
            @NeverNull Long userGroupId,
            @NeverNull String userName) {
        this.userId = userId;
        this.userGroupId = userGroupId;
        this.userName = userName;
    }
}
