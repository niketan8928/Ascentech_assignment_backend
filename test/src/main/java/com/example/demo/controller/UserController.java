//package com.example.demo.controller;
//
//import com.example.demo.exception.UserNotFoundException;
//import com.example.demo.model.User;
//import com.example.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin("http://localhost:3000")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/user")
//    User newUser(@RequestBody User newUser) {
//        return userRepository.save(newUser);
//    }
//
//    @GetMapping("/users")
//    List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//     
//
//    @GetMapping("/user/{id}")
//    User getUserById(@PathVariable Long id) {
//        return userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//    }
//
//    @PutMapping("/user/{id}")
//    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    user.setApplication_name(newUser.getApplication_name());
//                    user.setHall_name(newUser.getHall_name());
//                    user.setEmail(newUser.getEmail());
//                    user.setMobile(newUser.getMobile());
//                    user.setStart_date(newUser.getStart_date());
//                    user.setEnd_date(newUser.getEnd_date());
//                    user.setRent(newUser.getRent());
//                    user.setAdditional_charges(newUser.getAdditional_charges());
//                    
//                    return userRepository.save(user);
//                }).orElseThrow(() -> new UserNotFoundException(id));
//    }
//
//    @DeleteMapping("/user/{id}")
//    String deleteUser(@PathVariable Long id){
//        if(!userRepository.existsById(id)){
//            throw new UserNotFoundException(id);
//        }
//        userRepository.deleteById(id);
//        return  "User with id "+id+" has been deleted success.";
//    }
//
//
//
//}
//


package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userService.updateUser(id, newUser);
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
    
    //stored procedure
    @GetMapping("/userssp")
    public List<User> get_All_Users() {
        return userService.get_All_Users();
    }
    
    //stored procedure
    @DeleteMapping("usersp/{id}")
    public ResponseEntity<String> delete_single_user(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    
}
