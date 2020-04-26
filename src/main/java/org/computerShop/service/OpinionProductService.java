package org.computerShop.service;

import org.computerShop.dto.OpinionsDto;
import org.computerShop.model.OpinionProduct;
import org.computerShop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OpinionProductService {
    ResponseEntity<?> createOpinion(OpinionProduct opinionProduct, long userId);
    double getAverageRanking(long productId);
    OpinionsDto getOpinionForProduct(long productId, int page, User user);
    ResponseEntity<String> setLike(User user, OpinionProduct opinionProduct);
    ResponseEntity<String> setDislike(User user, OpinionProduct opinionProduct);
}
