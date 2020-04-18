package org.computerShop.service;

import org.computerShop.model.OpinionProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OpinionProductService {
    ResponseEntity<?> createOpinion(OpinionProduct opinionProduct, long userId);
    double getAverageRanking(long productId);
    List<OpinionProduct> getOpinionForProduct(long productId);
}
