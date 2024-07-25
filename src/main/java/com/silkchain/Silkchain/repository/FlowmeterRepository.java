package com.silkchain.Silkchain.repository;

import com.silkchain.Silkchain.dto.Flowmeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlowmeterRepository extends JpaRepository<Flowmeter, Integer> {
    Optional<Flowmeter> findByIdAndBatchId(int id, String batchId);

//    List<Flowmeter> findByInputsWalletAddress(String walletAddress);
@Query("SELECT i.walletAddress, i.input FROM flowmeter f JOIN f.inputs i WHERE i.walletAddress = :walletAddress")
List<Object[]> findInputsByWalletAddress(@Param("walletAddress") String walletAddress);
}
