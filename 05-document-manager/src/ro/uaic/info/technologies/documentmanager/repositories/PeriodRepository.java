package ro.uaic.info.technologies.documentmanager.repositories;

import ro.uaic.info.technologies.documentmanager.entities.AdminEntity;
import ro.uaic.info.technologies.documentmanager.entities.PeriodsEntity;
import ro.uaic.info.technologies.documentmanager.models.Period;
import ro.uaic.info.technologies.documentmanager.utils.EntityConverterUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.technologies.documentmanager.utils.EntityConverterUtil.toPeriodsEntity;

@Stateless
public class PeriodRepository {

    @Inject
    private EntityManager entityManager;

    @EJB
    private UserRepository userRepository;

    public void addPeriod(Period period) {
        PeriodsEntity periodsEntity = toPeriodsEntity(period);
        AdminEntity adminEntity = userRepository.getAdminEntityById(period.getUser().getId());
        periodsEntity.setAddedBy(adminEntity);
        entityManager.persist(periodsEntity);
    }

    public List<Period> getAllPeriods() {
        Query query = entityManager.createQuery("SELECT period FROM PeriodsEntity period");

        return ((Collection<PeriodsEntity>) query.getResultList()).stream().map(EntityConverterUtil::toPeriod).collect(Collectors.toList());
    }
}
