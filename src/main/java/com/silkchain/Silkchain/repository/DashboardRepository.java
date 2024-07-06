package com.silkchain.Silkchain.repository;

import com.silkchain.Silkchain.dto.Flowmeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<Flowmeter, Long> {
}
