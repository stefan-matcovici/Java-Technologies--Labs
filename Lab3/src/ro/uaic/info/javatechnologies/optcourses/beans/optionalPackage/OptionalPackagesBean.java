package ro.uaic.info.javatechnologies.optcourses.beans.optionalPackage;

import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named("optionalPackagesAll")
@RequestScoped
public class OptionalPackagesBean implements Serializable {
    private List<OptionalPackage> cache;

    private final Logger log = Logger.getLogger(this.getClass().getName());

    public OptionalPackagesBean() throws SQLException {
        OptionalPackageRepository optionalPackageRepository = new OptionalPackageRepository();
        cache = new ArrayList<>(optionalPackageRepository.getAll());
    }

    public List<OptionalPackage> getOptionalPackages() {
        return cache;
    }

    public void addLecturer(@Observes OptionalPackage optionalPackage) {
        cache.add(optionalPackage);
    }
}
