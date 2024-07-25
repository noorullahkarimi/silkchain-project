package com.silkchain.Silkchain.service;
import com.silkchain.Silkchain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.silkchain.Silkchain.dto.User;

import java.util.List;
import java.util.stream.Collectors;

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
    public User findUser(String wallet){
        return userRepository.findByWalletAddress(wallet);
    }

    public boolean isAdminByRole(String wallet){
        User user = userRepository.findByWalletAddress(wallet);
        if (user != null) {
            System.out.println("---------------------------->" + user.getRole().equals("ROLE_ADMIN"));
            if (user.getRole().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }
    public User updateUser(String walletAddress, String email, String name, String lastname, String address, String city, String country) {
        User user = userRepository.findByWalletAddress(walletAddress);
        if (user != null) {
            user.setEmail(email);
            user.setName(name);
            user.setLastname(lastname);
            user.setAddress(address);
            user.setCity(city);
            user.setCountry(country);
            userRepository.save(user);
        }
        return user;
    }
    public List<String> getWallets() {
        return userRepository.findAll().stream()
                .map(User::getWalletAddress)
                .collect(Collectors.toList());
    }
}
