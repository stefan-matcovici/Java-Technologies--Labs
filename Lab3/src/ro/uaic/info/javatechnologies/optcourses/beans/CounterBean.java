package ro.uaic.info.javatechnologies.optcourses.beans;

import ro.uaic.info.javatechnologies.optcourses.listeners.SessionCounter;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("counterBean")
@ViewScoped
public class CounterBean implements Serializable {
    private int count;

    public CounterBean() {
        getSessions();
    }

    public void getSessions() {
        this.count =  SessionCounter.getConcurrentUsers();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
