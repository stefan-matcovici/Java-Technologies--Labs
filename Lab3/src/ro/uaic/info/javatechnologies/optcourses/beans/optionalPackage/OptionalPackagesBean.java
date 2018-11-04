package ro.uaic.info.javatechnologies.optcourses.beans.optionalPackage;

import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;

@Named("optionalPackagesAll")
@RequestScoped
public class OptionalPackagesBean implements Serializable {
    private OptionalPackageRepository optionalPackageRepository = new OptionalPackageRepository();

    public OptionalPackage[] getOptionalPackages() throws SQLException {
        return optionalPackageRepository.getAll().stream().toArray(OptionalPackage[]::new);
    }
}
