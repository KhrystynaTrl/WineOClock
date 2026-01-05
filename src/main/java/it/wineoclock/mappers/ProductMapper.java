package it.wineoclock.mappers;

import it.wineoclock.dto.ProductDto;
import it.wineoclock.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    private static final Logger log = LoggerFactory.getLogger(ProductMapper.class);

    public static Product fromDto(ProductDto dto){
        log.info("ProductMapper.fromDto");
        Product entity = new Product();
        entity.setCapacity(dto.getCapacity());
        entity.setCountry(dto.getCountry());
        entity.setName(dto.getName());
        entity.setProductor(dto.getProductor());
        entity.setType(dto.getType());
        return entity;
    }

    public static ProductDto fromEntity(Product entity){
        log.info("ProductMapper.fromEntity");
        ProductDto dto = new ProductDto();
        dto.setCapacity(entity.getCapacity());
        dto.setCountry(entity.getCountry());
        dto.setName(entity.getName());
        dto.setProductor(entity.getProductor());
        dto.setType(entity.getType());
        return dto;
    }

    public static List<ProductDto> fromEntities(List<Product> products){
        log.info("ProductMapper.fromEntities");
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products){
            productDtoList.add( fromEntity(product));
        }
        return productDtoList;
    }
}
