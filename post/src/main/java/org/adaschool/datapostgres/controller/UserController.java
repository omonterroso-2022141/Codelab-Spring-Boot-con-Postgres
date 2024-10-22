package org.adaschool.datapostgres.controller; // Asegúrate de que el paquete es correcto

import org.adaschool.datapostgres.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.adaschool.datapostgres.data.entity.User;
import org.adaschool.datapostgres.data.dto.UserDto;
import org.adaschool.datapostgres.data.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException();
        }
    }
}
