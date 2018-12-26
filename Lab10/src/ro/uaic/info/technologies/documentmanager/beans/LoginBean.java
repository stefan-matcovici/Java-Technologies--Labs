package ro.uaic.info.technologies.documentmanager.beans;

import ro.uaic.info.technologies.documentmanager.models.User;
import ro.uaic.info.technologies.documentmanager.repositories.UserRepository;
import ro.uaic.info.technologies.documentmanager.services.AuthService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    private User user = new User();

    @Inject
    private AuthService authService;

    @EJB
    private UserRepository userRepository;

    public String login() {
        authService.login(user);

        return "home?faces-redirect=true";
    }

    public String logout() {
        authService.logout();

        return "index?faces-redirect=true";
    }

    public String register() {
        userRepository.addGuestUser(user);

        return "index?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
