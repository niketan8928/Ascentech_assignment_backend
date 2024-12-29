package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User updateUser(Long id, User newUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setApplication_name(newUser.getApplication_name());
                    user.setHall_name(newUser.getHall_name());
                    user.setEmail(newUser.getEmail());
                    user.setMobile(newUser.getMobile());
                    user.setStart_date(newUser.getStart_date());
                    user.setEnd_date(newUser.getEnd_date());
                    user.setRent(newUser.getRent());
                   
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted successfully.";
    }
    
    
    @Override
    public List<User> get_All_Users() {
        return userRepository.get_All_Users();
    }

//    @Override
//    public String delete_single_user(Long id) {
//        if (!userRepository.existsById(id)) {
//            throw new UserNotFoundException(id);
//        }
//        userRepository.delete_single_user(id);
//        return "User with id " + id + " has been deleted successfully.";
//    }

    
    public void delete_single_user(Long userId) {
        try {
            userRepository.delete_single_user(userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user: " + e.getMessage());
        }
    }
	
}
