package org.computerShop.controller;


import com.fasterxml.jackson.annotation.JsonView;
import org.computerShop.dto.OpinionsDto;
import org.computerShop.model.OpinionProduct;
import org.computerShop.model.Views;
import org.computerShop.service.impl.OpinionProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/opinion")
public class OpinionController {


    private OpinionProductServiceImpl opinionProductService;

    @Autowired
    public OpinionController(OpinionProductServiceImpl opinionProductService){
        this.opinionProductService = opinionProductService;
    }

    @PostMapping("/createOpinion/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOpinion(@RequestBody OpinionProduct opinionProduct, @PathVariable("userId") long userId){
        return opinionProductService.createOpinion(opinionProduct, userId);
    }

    @GetMapping("/getAverageRanking/{productId}")
    public double getAverageRanking(@PathVariable("productId") long productId){
        return opinionProductService.getAverageRanking(productId);

    }

    @GetMapping("/getOpinionForProduct/{productId}")
    @JsonView(Views.ProductFull.class)
    public OpinionsDto getOpinionForProduct(@PathVariable("productId") long id, @RequestParam(value = "page", defaultValue = "0") int page){
        System.out.println(page);
        return opinionProductService.getOpinionForProduct(id, page);
    }
}
