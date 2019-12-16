package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.Contract;
import com.zhiyuan.paymentsystem.repository.ContractRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Service
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;


    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Contract contract) {
        Contract contractReference;
        try{
            contractReference = findContractByContractId(contract.getContractId());
        }catch (NotFoundException nfe){
            return null;
        }
        contractReference.setContractText(contract.getContractText());
        return contractRepository.save(contractReference);
    }

    @Override
    public Contract findContractByContractId(Integer contractId) {
        Optional<Contract> contractOptional = contractRepository.findContractByContractId(contractId);
        if(!contractOptional.isPresent()){
            log.error("Contract with ID:{} is not found.[from ContractService.updateContract()]", contractId);
            throw new NotFoundException("Contract with ID:" + contractId +" is not found.");
        }
        return contractOptional.get();
    }

    @Override
    public List<Contract> findContractByClientId(Integer clientId) {
        return contractRepository.findContractByClientId(clientId);
    }

    @Override
    public List<Contract> findContractByManagerId(Integer managerId) {
        return contractRepository.findContractByManagerId(managerId);
    }

    @Override
    public List<Contract> findAllContracts() {
        return contractRepository.findAll();
    }
}
