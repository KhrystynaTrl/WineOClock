package it.wineoclock.controller;

import it.wineoclock.dto.UserDto;
import it.wineoclock.exceptions.NotFound;
import it.wineoclock.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid UserDto userDto){
        log.info("UserController.create - {}", userDto);
        userService.create(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        log.info("UserController.getAllUsers");
        List<UserDto> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<UserDto> getById(@RequestParam(name = "id") @Min(message = "min 1", value = 1) int id) throws NotFound {
        log.info("UserController.getById - {}", id);
        UserDto userDto = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") @Min(message = "min 1", value = 1) int id) throws NotFound{
        log.info("UserController.delete - {}", id);
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id") @Min(message = "min 1", value = 1) int id, @RequestBody UserDto userDto) throws NotFound {
        log.info("UserController.update - {}", id);
        UserDto user = userService.updateUser(id,userDto);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
