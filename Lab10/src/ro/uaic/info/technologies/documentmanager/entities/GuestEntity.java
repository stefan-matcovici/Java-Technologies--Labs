package ro.uaic.info.technologies.documentmanager.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("guest")
public class GuestEntity extends UsersEntity {
}
