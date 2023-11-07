package org.example.domain.util;

import net.bytebuddy.utility.nullability.NeverNull;

public final class AddCommand implements Command {

    final @NeverNull Integer userId;
    final @NeverNull Integer userGroupId;
    final @NeverNull String userName;

    public AddCommand(
            @NeverNull Integer userId,
            @NeverNull Integer userGroupId,
            @NeverNull String userName) {
        this.userId = userId;
        this.userGroupId = userGroupId;
        this.userName = userName;
    }
}
