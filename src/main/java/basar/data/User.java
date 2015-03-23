package basar.data;

import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable, Identifiable<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(unique = true, nullable = false)
    private String basarNumber;

    @Basic
    private String name;

    @Basic
    private String email;

    @Basic
    private String lastname;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Position> positions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBasarNumber() {
        return basarNumber;
    }

    public void setBasarNumber(String basarNumber) {
        this.basarNumber = basarNumber;
    }

    public List<Position> getPositions() {
        return this.positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", basarNumber='" + basarNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
