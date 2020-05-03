package org.computerShop.sockets;

import org.computerShop.model.OpinionProduct;
import org.computerShop.model.Replay;

public class OpinionMessage extends Message{

    private long productId;

    private long opinionProductId;


    public OpinionMessage(String title, long productId) {
        super(title);
        this.productId = productId;
    }

    public OpinionMessage(String title, long productId, Long opinionProductId) {
        super(title);
        this.productId = productId;
        this.opinionProductId = opinionProductId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOpinionProductId() {
        return opinionProductId;
    }

    public void setOpinionProductId(long opinionProductId) {
        this.opinionProductId = opinionProductId;
    }
}
