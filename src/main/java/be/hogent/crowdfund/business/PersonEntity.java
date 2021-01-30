package be.hogent.crowdfund.business;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "person",
uniqueConstraints = {
        @UniqueConstraint(columnNames = { "email" })
})
public class PersonEntity implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "personid")
    private int personID;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "company")
    private String company;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "personfunction")
    private PersonFunction function;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "personstatus")
    private PersonStatus status;

    public PersonEntity() {
    }

    public PersonEntity(String firstName, String lastName, String email, String company, PersonFunction function, PersonStatus status) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.function = function;
        this.status = status;
    }

    public PersonEntity(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public int getPersonID() {
        return personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCompany() {
        return company;
    }

    public PersonFunction getFunction() {
        return function;
    }

    public PersonStatus getStatus() {
        return status;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "personID=" + personID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", function=" + function +
                ", status=" + status +
                '}';
    }
}
