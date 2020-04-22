package org.computerShop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CategorySectionDto {

    private String title;

    public Field[] fields;

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }

    public Field[] getFields() { return fields; }
    public void setFields(Field[] value) { this.fields = value; }


}
