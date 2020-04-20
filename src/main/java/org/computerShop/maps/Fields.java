package org.computerShop.maps;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.Views;

import javax.persistence.Embeddable;

@Embeddable
public class Fields {

    @JsonView({Views.CategoryFull.class,Views.ProductFull.class})
    private String fieldName;


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
