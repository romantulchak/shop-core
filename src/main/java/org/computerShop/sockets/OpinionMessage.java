package org.computerShop.sockets;

public class OpinionMessage extends Message{

    private long productId;


    public OpinionMessage(String title, long productId) {
        super(title);
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
