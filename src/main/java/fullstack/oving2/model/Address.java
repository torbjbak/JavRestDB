package fullstack.oving2.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Address {
    @Id @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private Long id;
    private int postCode;
    private String city;
    private String streetAdd;

    public Address(int postCode, String city, String streetAdd) {
        this.postCode = postCode;
        this.city = city;
        this.streetAdd = streetAdd;
    }

    public Address() {}

    public Long getId() {
        return this.id;
    }

    public int getPostCode() {
        return this.postCode;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreetAdd() {
        return this.streetAdd;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreetAdd(String streetAdd) {
        this.streetAdd = streetAdd;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (!(obj instanceof Address))
            return false;
        Address employee = (Address) obj;
        return Objects.equals(this.id, employee.id) &&
                Objects.equals(this.postCode, employee.postCode) &&
                Objects.equals(this.city, employee.city) &&
                Objects.equals(this.streetAdd, employee.streetAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.postCode, this.city, this.streetAdd);
    }

    @Override
    public String toString() {
        return streetAdd +", "+ postCode +" "+ city;
    }
}
