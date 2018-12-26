package ro.uaic.info.technologies.documentmanager.utils;

import ro.uaic.info.technologies.documentmanager.entities.GuestEntity;
import ro.uaic.info.technologies.documentmanager.entities.UsersEntity;
import ro.uaic.info.technologies.documentmanager.models.User;

public class EntityConverterUtil {

    public static User toUser(UsersEntity usersEntity) {
        User user = new User();

        user.setId(usersEntity.getId());
        user.setUsername(usersEntity.getUsername());
        user.setPassword(usersEntity.getPassword());
        user.setType(User.UserType.valueOf(usersEntity.getType()));

        return user;
    }

    public static UsersEntity toUsersEntity(User user) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(user.getUsername());
        usersEntity.setPassword(user.getPassword());

        return usersEntity;
    }

    public static GuestEntity toGuestEntity(User user) {
        GuestEntity usersEntity = new GuestEntity();
        usersEntity.setUsername(user.getUsername());
        usersEntity.setPassword(user.getPassword());

        return usersEntity;
    }
}
