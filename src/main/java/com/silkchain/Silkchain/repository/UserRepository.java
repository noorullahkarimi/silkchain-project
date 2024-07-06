package com.silkchain.Silkchain.repository;

import com.silkchain.Silkchain.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByWalletAddress(String walletAddress);
}