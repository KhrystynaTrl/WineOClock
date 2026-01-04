package it.wineoclock.mappers;

import it.wineoclock.dto.ProductDto;
import it.wineoclock.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductMapper {

    private static final Logger log = LoggerFactory.getLogger(ProductMapper.class);

    public static Product fromDto(ProductDto dto){
        log.info("ProductMapper.fromDto");
        Product entity = new Product();
        entity.setCapacity(dto.getCapacity());
        entity.setCountry(dto.getCountry());
        entity.setName(dto.getName());
        entity.setProductor(dto.getProductor());
        return entity;
    }

    public static ProductDto fromEntity(Product entity){
        log.info("ProductMapper.fromEntity");
        ProductDto dto = new ProductDto();
        dto.setCapacity(entity.getCapacity());
        dto.setCountry(entity.getCountry());
        dto.setName(entity.getName());
        dto.setProductor(entity.getProductor());
        return dto;
    }
}
