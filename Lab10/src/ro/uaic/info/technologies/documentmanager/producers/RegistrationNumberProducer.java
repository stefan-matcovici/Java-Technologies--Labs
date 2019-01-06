package ro.uaic.info.technologies.documentmanager.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Random;

@ApplicationScoped
public class RegistrationNumberProducer {

    @Produces
    @DocumentRegistrationNumber
    public Integer getRegistrationNumber() {
        return new Random().nextInt();
    }
}
