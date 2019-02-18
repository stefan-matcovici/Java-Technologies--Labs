package ro.uaic.info.technologies.documentmanager.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "documents", schema = "public", catalog = "docs_manager")
public class DocumentsEntity {
    private int id;
    private int registrationNumber;
    private String name;
    private UsersEntity user;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "registration_number", nullable = false)
    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentsEntity that = (DocumentsEntity) o;
        return id == that.id &&
                registrationNumber == that.registrationNumber &&
                name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationNumber, name);
    }
}
