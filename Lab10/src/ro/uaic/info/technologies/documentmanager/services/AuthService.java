package ro.uaic.info.technologies.documentmanager.services;

import ro.uaic.info.technologies.documentmanager.models.User;
import ro.uaic.info.technologies.documentmanager.repositories.UserRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthService implements Serializable {

    @EJB
    UserRepository userRepository;

    private User currentUser;

    public void login(User user) {
        try {
            currentUser = userRepository.getUserByName(user);
        } catch (NoResultException noResultException) {
            //handle user not found
        }
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
