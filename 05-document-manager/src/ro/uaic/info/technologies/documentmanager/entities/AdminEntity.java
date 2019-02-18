package ro.uaic.info.technologies.documentmanager.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class AdminEntity extends UsersEntity {
}
