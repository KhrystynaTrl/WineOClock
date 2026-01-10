package it.wineoclock.mappers;

import it.wineoclock.dto.UserDetailDto;
import it.wineoclock.dto.UserDto;
import it.wineoclock.entity.User;
import it.wineoclock.entity.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    private static final Logger log = LoggerFactory.getLogger(UserMapper.class);

    public static UserDetailDto fromEntityUserDetail(UserDetail entity){
        log.info("UserMapper.fromEntityUserDetail - {}", entity);
        UserDetailDto dto = new UserDetailDto();
        dto.setCity(entity.getCity());
        dto.setStreet(entity.getStreet());
        dto.setStreetNumber(entity.getStreetNumber());
        dto.setZipCode(entity.getZipCode());
        return dto;
    }

    public static UserDetail fromDtoUserDetail(UserDetailDto dto){
        log.info("UserMapper.fromDtoUserDetail - {}", dto);
        UserDetail entity = new UserDetail();
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setStreetNumber(dto.getStreetNumber());
        entity.setZipCode(dto.getZipCode());
        return entity;
    }

    public static UserDto fromEntityUser(User entity){
        log.info("UserMapper.fromEntity - {}", entity);
        UserDto dto = new UserDto();
        dto.setEmail(entity.getEmail());
        dto.setUserDetail(fromEntityUserDetail(entity.getUserDetail()));
        return dto;
    }

    public static User fromDtoUser(UserDto dto){
        log.info("UserMapper.fromDtoUser - {}", dto);
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setUserDetail(fromDtoUserDetail(dto.getUserDetail()));
        return entity;
    }

    public static List<UserDto> fromUserEntityList(List<User> users){
        log.info("UserMapper.fromUserEntityList");
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : users){
           userDtoList.add(fromEntityUser(user));
        }
        return userDtoList;
    }
}
