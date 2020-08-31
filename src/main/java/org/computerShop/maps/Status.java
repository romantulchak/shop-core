package org.computerShop.maps;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.enums.CustomStatus;
import org.computerShop.model.Views;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Status {

    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private CustomStatus customStatus;


    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private LocalDateTime statusDateTime;

    @JsonView({Views.CustomFUll.class, Views.UserFull.class,Views.CustomUser.class})
    private int statusCode;

    public Status(){

    }
    public Status(CustomStatus customStatus, LocalDateTime statusDateTime, int statusCode){
        this.customStatus = customStatus;
        this.statusDateTime = statusDateTime;
        this.statusCode = statusCode;
    }
    public Status(CustomStatus customStatus, int statusCode){
        this.customStatus = customStatus;
        this.statusCode = statusCode;
    }

    public CustomStatus getCustomStatus() {
        return customStatus;
    }

    public void setCustomStatus(CustomStatus customStatus) {
        this.customStatus = customStatus;
    }

    public LocalDateTime getStatusDateTime() {
        return statusDateTime;
    }

    public void setStatusDateTime(LocalDateTime statusDateTime) {
        this.statusDateTime = statusDateTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
