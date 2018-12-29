package ro.uaic.info.technologies.documentmanager.beans;

import ro.uaic.info.technologies.documentmanager.interceptors.AdminLoggedIn;
import ro.uaic.info.technologies.documentmanager.interceptors.LoggedIn;
import ro.uaic.info.technologies.documentmanager.models.Period;
import ro.uaic.info.technologies.documentmanager.repositories.PeriodRepository;
import ro.uaic.info.technologies.documentmanager.services.AuthService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@LoggedIn
@AdminLoggedIn
public class PeriodBean implements Serializable {

    @EJB
    private PeriodRepository periodRepository;

    @Inject
    private AuthService authService;

    private Period period = new Period();

    public String add() {
        period.setUser(authService.getCurrentUser());
        periodRepository.addPeriod(period);

        return "home?faces-redirect=true";
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
