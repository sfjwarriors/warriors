package edu.escuelaing.arsw.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="user", schema = "public")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "rol")
    private String rol;
    @Column(name = "address")
    private String address;
    @Column(name = "image")
    private String image;
    @Column(name = "cash")
    private long cash;
    @Column (length = 10)
    private long cellphone;
    @Column(unique = true)
    private String token;

    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fkUser")
    private List<Orden> ordens;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "fkMechanic")
    private List<Store> store;
    
    public User(){

    }

    public User(String name, String lastName, String email, String password, String rol, String address, String image, long cash, long cellphone) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.address = address;
        this.image = image;
        this.cash = cash;
        this.cellphone = cellphone;
        this.token = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getCash() {
        return cash;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public long getCellphone() {
        return cellphone;
    }

    public void setCellphone(long cellphone) {
        this.cellphone = cellphone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Orden> getOrdens() {
        return ordens;
    }

    public void setOrdens(List<Orden> ordens) {
        this.ordens = ordens;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                ", cash=" + cash +
                ", cellphone=" + cellphone +
                ", token='" + token + '\'' +
                '}';
    }
}
