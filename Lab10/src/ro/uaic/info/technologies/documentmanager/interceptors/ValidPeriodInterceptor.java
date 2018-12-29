package ro.uaic.info.technologies.documentmanager.interceptors;

import ro.uaic.info.technologies.documentmanager.models.Period;
import ro.uaic.info.technologies.documentmanager.repositories.PeriodRepository;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ValidPeriod
@Interceptor
public class ValidPeriodInterceptor implements Serializable {

    @EJB
    private PeriodRepository periodRepository;

    @AroundInvoke
    public Object logMethodEntry(InvocationContext invocationContext) throws Exception {
        List<Period> allPeriods = periodRepository.getAllPeriods();

        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);

        Date now = today.getTime();

        boolean validPeriod = allPeriods.stream().anyMatch(period -> period.getStartDate().compareTo(now) * now.compareTo(period.getEndDate()) > 0);

        if (!validPeriod) {
            throw new Exception("Invalid period to register you fool");
        }

        return invocationContext.proceed();
    }

}
