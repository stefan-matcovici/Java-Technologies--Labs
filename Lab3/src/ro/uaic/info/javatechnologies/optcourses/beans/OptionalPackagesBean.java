package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.SQLException;

@ManagedBean(name = "optionalPackagesAll")
@ApplicationScoped
public class OptionalPackagesBean {
    private OptionalPackageRepository optionalPackageRepository = new OptionalPackageRepository();

    public OptionalPackage[] getOptionalPackages() throws SQLException {
        return optionalPackageRepository.getAll().stream().toArray(OptionalPackage[]::new);
    }
}
