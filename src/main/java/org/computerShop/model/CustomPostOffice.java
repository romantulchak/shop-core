package org.computerShop.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class CustomPostOffice {


    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String departmentName;

    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private String departmentCity;
    

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentCity() {
        return departmentCity;
    }

    public void setDepartmentCity(String departmentCity) {
        this.departmentCity = departmentCity;
    }

}
