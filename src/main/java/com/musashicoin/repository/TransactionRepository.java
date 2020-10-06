package com.musashicoin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musashicoin.model.NewTransaction;

@Repository
public interface TransactionRepository extends JpaRepository<NewTransaction, Long> {
}
