package ro.uaic.info.technologies.documentmanager.utils;

import ro.uaic.info.technologies.documentmanager.entities.*;
import ro.uaic.info.technologies.documentmanager.models.Document;
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

    public static DocumentsEntity toDocumentsEntity(Document document) {
        DocumentsEntity documentsEntity = new DocumentsEntity();
        if (document.getId() != null) {
            documentsEntity.setId(document.getId());
        }
        documentsEntity.setRegistrationNumber(document.getRegistrationNumber());
        documentsEntity.setName(document.getName());

        documentsEntity.setUser(toUsersEntity(document.getUser()));

        return documentsEntity;
    }

    public static Document toDocument(DocumentsEntity documentsEntity) {
        Document document = new Document();
        document.setId(documentsEntity.getId());
        document.setRegistrationNumber(documentsEntity.getRegistrationNumber());
        document.setName(documentsEntity.getName());

        if (documentsEntity.getUser() != null) {
            document.setUser(toUser(documentsEntity.getUser()));
        }

        return document;
    }
}
