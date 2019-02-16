package ro.uaic.info.technologies.documentmanager.beans;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import ro.uaic.info.technologies.documentmanager.interceptors.ValidPeriod;
import ro.uaic.info.technologies.documentmanager.models.User;
import ro.uaic.info.technologies.documentmanager.repositories.UserRepository;
import ro.uaic.info.technologies.documentmanager.services.AuthService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Named
@RequestScoped
public class LoginBean implements Serializable {

    private User user = new User();

    @Inject
    private AuthService authService;

    @EJB
    private UserRepository userRepository;

    public String login() throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        request.login(user.getUsername(), user.getPassword());

        authService.login(user);

        return "/pages/home?faces-redirect=true";
    }

    public String logout() throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        request.logout();
        authService.logout();

        return "/all/index?faces-redirect=true";
    }

    @ValidPeriod
    public String register() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
        md.update(user.getPassword().getBytes("UTF-8"));
        byte[] passwordDigest = md.digest();
        user.setPassword(Base64.encode(passwordDigest));

        userRepository.addGuestUser(user);

        return "pages/index?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
