package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.enums.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.UserFull.class)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @JsonView(Views.UserFull.class)
    private ERole name;

    public Role(){

    }

    public Role(ERole name){

        this.name = name;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
