package ro.uaic.info.javatechnologies.optcourses.repository;

import ro.uaic.info.javatechnologies.optcourses.entities.PackagesEntity;
import ro.uaic.info.javatechnologies.optcourses.models.OptionalPackage;
import ro.uaic.info.javatechnologies.optcourses.models.Semester;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    public void save(OptionalPackage optionalPackage) throws SQLException {
        optCoursesPU.getTransaction().begin();
        optCoursesPU.persist(toOptionalPackageEntity(optionalPackage));
        optCoursesPU.getTransaction().commit();
    }

    @Override
    public List<OptionalPackage> getAll() throws SQLException {
        Query query = optCoursesPU.createQuery("SELECT e FROM PackagesEntity e");
        List<OptionalPackage> optionalPackages = ((Collection<PackagesEntity>) query.getResultList()).stream().map(OptionalPackageRepository::toOptionalPackage).collect(Collectors.toList());

        return optionalPackages;
    }

    @Override
    public void updateEntities(List<OptionalPackage> entities) throws SQLException {

    }

    static OptionalPackage toOptionalPackage(PackagesEntity entity) {
        OptionalPackage result = new OptionalPackage();
        result.setId(String.valueOf(entity.getId()));
        result.setCode(entity.getCode());
        result.setYear(entity.getYear());
        result.setSemester(Semester.valueOf(entity.getSemester().toUpperCase()));
        result.setCode(entity.getCode());

        return result;
    }

    static PackagesEntity toOptionalPackageEntity(OptionalPackage optionalPackage) {
        PackagesEntity result = new PackagesEntity();
        result.setId(Integer.parseInt(optionalPackage.getId()));
        result.setCode(optionalPackage.getCode());
        result.setYear(optionalPackage.getYear());
        result.setSemester(optionalPackage.getSemester().getName());
        result.setCode(optionalPackage.getCode());

        return result;
    }
}
