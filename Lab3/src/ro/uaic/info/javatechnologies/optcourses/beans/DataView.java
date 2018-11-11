package ro.uaic.info.javatechnologies.optcourses.beans;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import ro.uaic.info.javatechnologies.optcourses.models.AbstractEntity;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import java.sql.SQLException;
import java.util.List;

public class DataView<T extends AbstractEntity<ID>, ID> extends BackingBean<T, ID> {
    protected List<T> entities;

    @PostConstruct
    public void init() {
        super.init();
    }

    public List<T> getEntities() throws SQLException {
        return entities;
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            RequestContext.getCurrentInstance().showMessageInDialog(msg);
        }
    }

    public void save() throws SQLException {
        repository.updateEntities(entities);
    }
}
