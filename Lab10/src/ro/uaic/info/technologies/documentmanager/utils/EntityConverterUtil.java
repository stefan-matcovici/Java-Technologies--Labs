package ro.uaic.info.technologies.documentmanager.utils;

import ro.uaic.info.technologies.documentmanager.entities.AdminEntity;
import ro.uaic.info.technologies.documentmanager.entities.GuestEntity;
import ro.uaic.info.technologies.documentmanager.entities.PeriodsEntity;
import ro.uaic.info.technologies.documentmanager.entities.UsersEntity;
import ro.uaic.info.technologies.documentmanager.models.Period;
import ro.uaic.info.technologies.documentmanager.models.User;

import java.sql.Date;
import java.sql.Time;

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

    public static AdminEntity toAdminEntity(User user) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setUsername(user.getUsername());
        adminEntity.setPassword(user.getPassword());

        return adminEntity;
    }

    public static PeriodsEntity toPeriodsEntity(Period period) {
        PeriodsEntity result = new PeriodsEntity();

        result.setStartDate(new Date(period.getStartDate().getTime()));
        result.setEndDate(new Date(period.getEndDate().getTime()));

        return result;
    }

    public static Period toPeriod(PeriodsEntity periodsEntity) {
        Period result = new Period();

        result.setStartDate(periodsEntity.getStartDate());
        result.setEndDate(periodsEntity.getEndDate());
        result.setUser(toUser(periodsEntity.getAddedBy()));
        return result;
    }
}
