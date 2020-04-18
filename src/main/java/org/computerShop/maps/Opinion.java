package org.computerShop.maps;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.dto.UserDto;
import org.computerShop.model.Views;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Opinion {

    @JsonView(Views.ProductFull.class)
    private String text;

    @JsonView(Views.ProductFull.class)
    private LocalDateTime dateTime;

    @JsonView(Views.ProductFull.class)
    private long userId;


    public Opinion(String text, LocalDateTime dateTime, long userId){
        this.text = text;
        this.dateTime = dateTime;
        this.userId = userId;
    }

    public Opinion(){}





    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
