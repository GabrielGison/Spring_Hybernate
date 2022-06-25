package co.develhope.hybernate.entities;

import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    private String email;

    @NotNull
    private java.lang.String first_name;

    @NotNull
    private java.lang.String last_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.lang.String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(java.lang.String first_name) {
        this.first_name = first_name;
    }

    public java.lang.String getLast_name() {
        return last_name;
    }

    public void setLast_name(java.lang.String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
