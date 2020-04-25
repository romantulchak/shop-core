package org.computerShop.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.model.OpinionProduct;
import org.computerShop.model.Views;

import java.util.List;

public class OpinionsDto {
    @JsonView(Views.ProductFull.class)
    private List<CommentsDto> comments;

    @JsonView(Views.ProductFull.class)
    private int currentPage;

    @JsonView(Views.ProductFull.class)
    private int totalPages;

    @JsonView(Views.ProductFull.class)
    private int commentsCounter;



    public OpinionsDto(){

    }

    public OpinionsDto(List<CommentsDto> comments, int currentPage, int totalPages, int commentsCounter) {
        this.comments = comments;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.commentsCounter = commentsCounter;
    }


    public List<CommentsDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentsDto> comments) {
        this.comments = comments;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCommentsCounter() {
        return commentsCounter;
    }

    public void setCommentsCounter(int commentsCounter) {
        this.commentsCounter = commentsCounter;
    }

}
