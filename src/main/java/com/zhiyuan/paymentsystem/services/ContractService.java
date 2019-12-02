package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.models.Contract;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
public interface ContractService {
    Contract saveContract(Contract contract);
    Contract updateContract(Contract contract);

    Contract findContractByContractId(Integer contractId);
    List<Contract> findContractByClientId(Integer clientId);
    List<Contract> findContractByManagerId(Integer managerId);
}
