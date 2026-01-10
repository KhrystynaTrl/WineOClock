package it.wineoclock.service;

import it.wineoclock.dto.UserDetailDto;
import it.wineoclock.dto.UserDto;
import it.wineoclock.entity.User;
import it.wineoclock.entity.UserDetail;
import it.wineoclock.exceptions.NotFound;
import it.wineoclock.mappers.UserMapper;
import it.wineoclock.repository.UserRepo;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void create(UserDto userDto) {
        log.info("UserService.create - {}", userDto);
        User user = UserMapper.fromDtoUser(userDto);
        userRepo.save(user);
    }

    public List<UserDto> findAll() {
        log.info("UserService.findAll");
        List<User> userList = userRepo.findAll();
        return UserMapper.fromUserEntityList(userList);
    }


    public UserDto findById(int id) throws NotFound{
        log.info("UserService.findById");
        //return UserMapper.fromEntityUser(userRepo.findById(id).orElseThrow(() -> new NotFound("User not found")));
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()){
            throw new NotFound("User not found");
        }
        User userFind = user.get();
        return UserMapper.fromEntityUser(userFind);
    }

    public void delete(int id) throws NotFound {
        log.info("UserService.delete");
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
        } else {
            throw new NotFound("User not found");
        }

    }

    public UserDto updateUser(int id, UserDto userDto) throws NotFound {
        log.info("UserService.update");
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()){
            throw new NotFound("User not found");
        }
        User userFind = user.get();
        userFind.setEmail(userDto.getEmail());
        UserDetail userDetail = userFind.getUserDetail();
        userDetail.setCity(userDto.getUserDetail().getCity());
        userDetail.setStreet(userDto.getUserDetail().getStreet());
        userDetail.setStreetNumber(userDto.getUserDetail().getStreetNumber());
        userDetail.setZipCode(userDto.getUserDetail().getZipCode());
        return UserMapper.fromEntityUser(userRepo.save(userFind));
    }
}
