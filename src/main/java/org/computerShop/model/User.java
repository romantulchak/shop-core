package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.Nullable;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.swing.text.View;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "email")
    }
)
public class User {


    //TODO: список бажань

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.UserFull.class, Views.CustomUser.class})
    private Long id;

    @NotBlank
    @Size(max = 20)
    @JsonView({Views.UserFull.class})
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @JsonView({Views.UserFull.class, Views.ProductFull.class})
    private String email;

    @NotBlank
    @Size(max = 120)
    @JsonView(Views.UserFull.class)
    private String password;

    @NotBlank
    @Size(max = 20)
    @JsonView({Views.UserFull.class, Views.ProductFull.class})
    private String firstName;

    @NotBlank
    @Size(max = 20)
    @JsonView({Views.UserFull.class, Views.ProductFull.class})
    private String lastName;

    @NotBlank
    @Size(max = 30)
    @JsonView({Views.UserFull.class, Views.ProductFull.class})
    private String city;

    @NotBlank
    @Size(max = 40)
    @JsonView(Views.UserFull.class)
    private String address;

    @NotBlank
    @Size(max = 7)
    @JsonView(Views.UserFull.class)
    private String postalCode;

    @NotBlank
    @JsonView(Views.UserFull.class)
    private String mobilePhone;


    @OneToMany
    @JsonView({Views.UserFull.class})
    private List<OpinionProduct> opinionProducts;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonView(Views.UserFull.class)
    private Set<Role> roles = new HashSet<>();



    @OneToMany(mappedBy = "user")
    @JsonView({Views.CustomFUll.class, Views.UserFull.class})
    private List<Custom> custom;


    @Nullable
    @OneToOne
    private Subscription subscription;

    public User() {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.mobilePhone = mobilePhone;
    }



    public User(String username, String email, String password, String firstName, String lastName, String city, String address, String postalCode, String mobilePhone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.mobilePhone = mobilePhone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Custom> getCustom() {
        return custom;
    }

    public void setCustom(List<Custom> custom) {
        this.custom = custom;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public List<OpinionProduct> getOpinionProducts() {
        return opinionProducts;
    }

    public void setOpinionProducts(List<OpinionProduct> opinionProducts) {
        this.opinionProducts = opinionProducts;
    }
}
