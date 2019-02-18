package ro.uaic.info.javatechnologies.optcourses.beans.optionalPackage;

import ro.uaic.info.javatechnologies.optcourses.beans.BackingBean;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

@Named("optionalPackagesAll")
@ApplicationScoped
public class OptionalPackagesBean extends BackingBean<OptionalPackage, String> implements Serializable {

    @EJB
    private OptionalPackageRepository optionalPackageRepository;

    @PostConstruct
    public void init() {
        repository = optionalPackageRepository;
    }

    public List<OptionalPackage> getOptionalPackages() throws MalformedURLException, SQLException {
        return repository.getAll();
    }
}
