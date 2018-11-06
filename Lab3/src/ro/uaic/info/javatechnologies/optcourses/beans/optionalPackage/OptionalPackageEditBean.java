package ro.uaic.info.javatechnologies.optcourses.beans.optionalPackage;

import ro.uaic.info.javatechnologies.optcourses.beans.DataEdit;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.repository.OptionalPackageRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("optionalPackageBean")
@RequestScoped
public class OptionalPackageEditBean extends DataEdit<OptionalPackage, String> {
    public OptionalPackageEditBean() {
        super();
        entity = new OptionalPackage();
        repository = new OptionalPackageRepository();
    }

}
