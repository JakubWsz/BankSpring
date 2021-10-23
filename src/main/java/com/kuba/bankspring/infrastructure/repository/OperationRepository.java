package com.kuba.bankspring.infrastructure.repository;

import com.kuba.bankspring.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {
    Operation saveOperation(Operation operation);
}
