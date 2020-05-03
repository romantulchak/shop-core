package org.computerShop.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.computerShop.dto.OpinionsDto;
import org.computerShop.model.OpinionProduct;
import org.computerShop.model.Replay;
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
    List<Replay> getAnswersForOpinion(OpinionProduct opinionProduct);
    ResponseEntity<String> setAnswer(Replay replay, OpinionProduct opinionProduct, User user) throws JsonProcessingException;
}
