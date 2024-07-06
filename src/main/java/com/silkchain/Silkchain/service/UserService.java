package com.silkchain.Silkchain.service;
import com.silkchain.Silkchain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.silkchain.Silkchain.dto.User;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(String walletAddress, String password, String email) {
        User user = new User();
        user.setWalletAddress(walletAddress);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }

    public User authenticateUser(String walletAddress, String password) {
        User user = userRepository.findByWalletAddress(walletAddress);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User updateUserRole(String walletAddress, String role) {
        User user = userRepository.findByWalletAddress(walletAddress);
        if (user != null) {
            user.setRole(role);
            return userRepository.save(user);
        }
        return null;
    }
}
