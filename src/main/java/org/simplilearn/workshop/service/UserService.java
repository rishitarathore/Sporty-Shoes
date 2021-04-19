package org.simplilearn.workshop.service;

import java.util.List;

import org.simplilearn.workshop.model.User;
import org.simplilearn.workshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByLoginUsernameAndLoginPassword(username, password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findBySearchParameter(String parameter) {
        return userRepository.findByFirstNameContainsOrLastNameContainsOrEmailContains(parameter, parameter, parameter);
    }
    
    
    public void saveUser(User user) {
		userRepository.save(user);			
	}
    
    
    public User findByUsername(String username) {
    	
    	return userRepository.findByLoginUsername(username);
    }
}
