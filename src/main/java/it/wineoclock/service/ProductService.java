package it.wineoclock.service;


import it.wineoclock.dto.ProductDto;
import it.wineoclock.entity.Product;
import it.wineoclock.exceptions.NotFound;
import it.wineoclock.exceptions.WineoClockException;
import it.wineoclock.mappers.ProductMapper;
import it.wineoclock.repository.ProductRepo;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return ProductMapper.fromEntities(products);
    }

    public List<ProductDto> findByCountry(String countryName) {
        log.info("ProductService.findByCountry");
        List<Product> products = productRepo.findByCountry(countryName);
        return ProductMapper.fromEntities(products);
    }


    public ProductDto update(ProductDto productDto, int id) throws NotFound {
        log.info("ProductService.update");
        Optional<Product> productByID = productRepo.findById(id);
        if(productByID.isEmpty()){
            throw new NotFound("product not found");
        }
        Product productFound = productByID.get();
        productFound.setName(productDto.getName());
        productFound.setProductor(productDto.getProductor());
        productFound.setCountry(productDto.getCountry());
        productFound.setType(productDto.getType());
        productFound.setCapacity(productDto.getCapacity());

        return ProductMapper.fromEntity(productRepo.save(productFound));
    }

    public void delete(int id) throws NotFound {
        log.info("ProductService.delete");
        if (productRepo.existsById(id)){
            productRepo.deleteById(id);
        } else  {
            throw new NotFound("Invalid id");
        }

    }
}
