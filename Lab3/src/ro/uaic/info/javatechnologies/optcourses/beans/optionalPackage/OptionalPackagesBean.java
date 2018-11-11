package ro.uaic.info.javatechnologies.optcourses.beans.optionalPackage;

import ro.uaic.info.javatechnologies.optcourses.beans.BackingBean;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named("optionalPackagesAll")
@RequestScoped
public class OptionalPackagesBean extends BackingBean<OptionalPackage, String> implements Serializable {
    private List<OptionalPackage> cache;

    public OptionalPackagesBean() throws SQLException {
        repository = new OptionalPackageRepository();
    }

    @PostConstruct
    public void init() {
        super.init();
        try {
            cache = new ArrayList<>(repository.getAll());
        } catch (SQLException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<OptionalPackage> getOptionalPackages() {
        return cache;
    }

    public void addLecturer(@Observes OptionalPackage optionalPackage) {
        cache.add(optionalPackage);
    }
}
