package ro.uaic.info.technologies.documentmanager.repositories;

import ro.uaic.info.technologies.documentmanager.entities.AdminEntity;
import ro.uaic.info.technologies.documentmanager.entities.UsersEntity;
import ro.uaic.info.technologies.documentmanager.models.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import static ro.uaic.info.technologies.documentmanager.utils.EntityConverterUtil.toAdminEntity;
import static ro.uaic.info.technologies.documentmanager.utils.EntityConverterUtil.toGuestEntity;
import static ro.uaic.info.technologies.documentmanager.utils.EntityConverterUtil.toUser;

@Stateless
public class UserRepository {

    @Inject
    EntityManager entityManager;

    public User getUserByName(User user) {
        Query query = entityManager.createQuery("SELECT ue FROM UsersEntity ue WHERE ue.username=:username");
        query.setParameter("username", user.getUsername());

        Object singleResult = query.getSingleResult();
        return toUser((UsersEntity) singleResult);
    }

    public AdminEntity getAdminEntityById(Integer id) {
        Query query = entityManager.createQuery("SELECT ue FROM AdminEntity ue WHERE ue.id=:id");
        query.setParameter("id", id);

        Object singleResult = query.getSingleResult();
        return (AdminEntity) singleResult;
    }

    public void addGuestUser(User user) {
        UsersEntity usersEntity = toGuestEntity(user);
        entityManager.persist(usersEntity);
    }
}
