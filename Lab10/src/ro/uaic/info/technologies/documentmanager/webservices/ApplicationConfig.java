package ro.uaic.info.technologies.documentmanager.webservices;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(RestService.class);
    }

    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

}
