package ro.uaic.info.javatechnologies.optcourses.ejb;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
public class TimerSessionBean {

    @Schedule(second = "*/1", minute = "*", hour = "*", persistent = false)
    public void doWork() {
        System.out.println("hard work");
    }
}
