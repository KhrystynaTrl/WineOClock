package it.wineoclock.service;


import it.wineoclock.dto.ProductDto;
import it.wineoclock.entity.Product;
import it.wineoclock.mappers.ProductMapper;
import it.wineoclock.repository.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo){
        this.productRepo = productRepo;
    }
    
    public void addProduct(ProductDto productDto){
        log.info("ProductService.addProduct");
        Product entity = ProductMapper.fromDto(productDto);
        productRepo.save(entity);
    }

    public List<ProductDto> findAllProducts() {
        log.info("ProductService.findAllProducts");
        List<Product> products = productRepo.findAll();
        List<ProductDto> dtoList = ProductMapper.fromEntities(products);
        return dtoList;
    }
}
