package com.zhiyuan.paymentsystem.repository;

import com.zhiyuan.paymentsystem.models.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
public interface ContractRepository extends JpaRepository<Contract, Integer>{
    // find-by
    Optional<Contract> findContractByContractId(Integer contractId);


    List<Contract> findContractByClientId(Integer clientId);


    List<Contract> findContractByManagerId(Integer managerId);


    List<Contract> findContractByStatus(Integer status);
    // delete
}
