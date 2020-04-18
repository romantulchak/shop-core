package org.computerShop.sockets;

public class OpinionMessage {

    private String title;
    private long productId;


    public OpinionMessage(String title, long productId) {
        this.title = title;
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
