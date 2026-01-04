package it.wineoclock.controller;


import it.wineoclock.dto.ProductDto;
import it.wineoclock.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> addProduct(@RequestBody @Valid ProductDto productDto){
        log.info("ProductController.addProduct");
        productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<ProductDto>> findAllProducts(){
        log.info("ProductController.findAllProducts");
        List<ProductDto> productDtoList =  productService.findAllProducts();
        return ResponseEntity.ok(productDtoList);
    }




}
