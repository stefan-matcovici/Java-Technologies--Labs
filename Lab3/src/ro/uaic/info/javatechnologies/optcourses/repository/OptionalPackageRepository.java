package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.PackagesEntity;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.javatechnologies.optcourses.utils.EntityConverter.toOptionalPackageEntity;

@Stateless
public class OptionalPackageRepository extends DataRepository<OptionalPackage, String> {

    public OptionalPackageRepository() {
    }

    public OptionalPackageRepository(String schema) {
        super(schema);
    }

    @Override
    public OptionalPackage getById(String s) {
        return null;
    }

    @Override
    public void save(OptionalPackage optionalPackage) {
        optCoursesPU.persist(toOptionalPackageEntity(optionalPackage));
    }

    @Override
    public List<OptionalPackage> getAll() {
        Query query = optCoursesPU.createQuery("SELECT e FROM PackagesEntity e");
        List<OptionalPackage> optionalPackages = ((Collection<PackagesEntity>) query.getResultList()).stream().map(EntityConverter::toOptionalPackage).collect(Collectors.toList());

        return optionalPackages;
    }

    public List<OptionalPackage> getByYear(int year) {
        Query query = optCoursesPU.createQuery("SELECT e FROM PackagesEntity e where e.year=" + year);
        List<OptionalPackage> optionalPackages = ((Collection<PackagesEntity>) query.getResultList()).stream().map(EntityConverter::toOptionalPackage).collect(Collectors.toList());

        return optionalPackages;
    }


    @Override
    public void updateEntities(List<OptionalPackage> entities) {

    }
}
