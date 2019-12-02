package com.zhiyuan.paymentsystem.controllers;

import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.Contract;
import com.zhiyuan.paymentsystem.models.User;
import com.zhiyuan.paymentsystem.services.ContractService;
import com.zhiyuan.paymentsystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@RestController
public class ContractController {
    private final UserService userService;
    private final ContractService contractService;

    public ContractController(UserService userService, ContractService contractService) {
        this.userService = userService;
        this.contractService = contractService;
    }

    @PostMapping("/contract")
    public Contract createContract(@RequestBody Contract contract){
        if(contract.getClientId() == null || contract.getManagerId() == null){
            return null;
        }
        User client, manager;
        try{
            client = userService.findUserByUserId(contract.getClientId());
            manager = userService.findUserByUserId(contract.getManagerId());
        }catch (NotFoundException nfe){
            return null;
        }

        if(client.getType() != 2 || manager.getType() != 1 || client.getStatus() == 0 || manager.getStatus() == 0){
            return null;
        }
        return contractService.saveContract(contract);
    }

    @PostMapping("/contract/update")
    public Contract updateContract(@RequestBody Contract contract){
        if(contract.getContractId() == null){
            return null;
        }

        Contract contractReference;
        try{
            contractReference = contractService.findContractByContractId(contract.getContractId());
        }catch (NotFoundException nfe){
            return null;
        }
        if(contractReference.getStatus() == 1){
            return null;
        }

        contractReference.setContractText(contract.getContractText());
        return contractService.updateContract(contractReference);
    }

    @PostMapping("/contract/sign/{contractId}")
    public Contract signContract(@PathVariable Integer contractId){
        Contract contractReference;
        try{
            contractReference = contractService.findContractByContractId(contractId);
        }catch(NotFoundException nfe){
            return null;
        }

        contractReference.setStatus(1);
        return contractService.updateContract(contractReference);
    }

    @GetMapping("/contract/client/{clientId}")
    public List<Contract> getContractsByClientId(@PathVariable Integer clientId){
        User user;
        try{
            user = userService.findUserByUserId(clientId);
        }catch (NotFoundException nfe){
            return null;
        }

        if(user.getType() != 2){
            return null;
        }

        return contractService.findContractByClientId(clientId);
    }

    @GetMapping("/contract/manager/{managerId}")
    public List<Contract> getContractByManagerId(@PathVariable Integer managerId){
        User user;
        try{
            user = userService.findUserByUserId(managerId);
        }catch (NotFoundException nfe){
            return null;
        }

        if(user.getType() != 1){
            return null;
        }

        return contractService.findContractByManagerId(managerId);
    }
}
