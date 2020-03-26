package org.computerShop.model.enums;

public enum  CustomStatus {
    IS_BEING_PROCESSED(0), IS_COMPLETED(1), IN_TRANSIT(2), AT_THE_DESTINATION(3),RECEIVED(4);

    private int statusCode;

    CustomStatus(int statusCode){
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
