package org.example.domain.model;

import jakarta.persistence.*;
import net.bytebuddy.utility.nullability.NeverNull;

@Entity
@Table(name = User.USER_TABLE_NAME)
public class User {
    public static final String USER_TABLE_NAME = "SUSERS";
    public static final String USER_ID_FIELD = "USER_ID";
    public static final String USER_GUID_FIELD = "USER_GUID";
    public static final String USER_NAME_FIELD = "USER_NAME";

    public User(@NeverNull Long userId, @NeverNull Long userGuid, @NeverNull String userName) {
        this.userId = userId;
        this.userGuid = userGuid;
        this.userName = userName;
    }

    @Id
    @Column(name = USER_ID_FIELD)
    @NeverNull
    private Long userId;

    @Column(name = USER_GUID_FIELD)
    @NeverNull
    private Long userGuid;

    @Column(name = USER_NAME_FIELD)
    @NeverNull
    private String userName;

    public User() {

    }

    @NeverNull
    public Long getUserId() {
        return userId;
    }

    @NeverNull
    public Long getUserGuid() {
        return userGuid;
    }

    @NeverNull
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userGuid=" + userGuid +
                ", userName='" + userName + '\'' +
                '}';
    }
}
