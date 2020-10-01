package edu.escuelaing.arsw.model;

import javax.persistence.*;
import java.util.List;

/*@Entity
@Table(name = "superuser", schema = "public")
public class SuperUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "rol")
    private String rol;
    @Column(name = "address")
    private String address;
    @Column(name = "image")
    private String image;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")
    private List<User> users;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_mechanic")
    private List<Mechanic> mechanics;

    public SuperUser() {
    }

    public SuperUser(String name, String lastName, String email, String password, String rol, String address, String image) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}*/
