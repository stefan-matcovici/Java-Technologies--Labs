package ro.uaic.info.technologies.documentmanager.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@ApplicationScoped
public class Resources implements Serializable {

    @Inject
    AuthService authService;

    @PersistenceContext(unitName = "DocsPU")
    private EntityManager docsPu;

    @Produces
    public EntityManager getEntityManager() {
        return docsPu;
    }
}
