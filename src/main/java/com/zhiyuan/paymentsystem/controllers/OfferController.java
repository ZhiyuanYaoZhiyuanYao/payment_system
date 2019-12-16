package com.zhiyuan.paymentsystem.controllers;

import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.Offer;
import com.zhiyuan.paymentsystem.models.User;
import com.zhiyuan.paymentsystem.services.OfferService;
import com.zhiyuan.paymentsystem.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@RestController
public class OfferController {
    private final OfferService offerService;
    private final UserService userService;

    public OfferController(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @PostMapping("/offer")
    public Offer createOffer(@RequestBody Offer offer){
        if(offer.getClientId() == null || offer.getManagerId() == null){
            return null;
        }
        User client, manager;
        try{
            client = userService.findUserByUserId(offer.getClientId());
            manager = userService.findUserByUserId(offer.getManagerId());
        }catch (NotFoundException nfe){
            return null;
        }

        if(client.getType() != 2 || manager.getType() != 1 || client.getStatus() == 0 || manager.getStatus() == 0){
            return null;
        }

        return offerService.saveOffer(offer);
    }

    @PostMapping("/offer/update")
    public Offer updateOfferByOfferId(@RequestBody Offer offer){
        if(offer.getOfferId() == null){
            return null;
        }

        Offer offerReference = offerService.findOfferByOfferId(offer.getOfferId());
        offerReference.setOfferText(offer.getOfferText());

        return offerService.updateOffer(offerReference);
    }

    @PostMapping("/offer/approve/{offerId}")
    public Offer approveOfferByOfferId(@PathVariable Integer offerId){
        Offer offerReference;
        try{
            offerReference = offerService.findOfferByOfferId(offerId);
        }catch (NotFoundException nfe){
            return null;
        }
        if(offerReference.getStatus() != 0) {
            return null;
        }
        offerReference.setStatus(1);
        return offerService.updateOffer(offerReference);
    }

    @PostMapping("/offer/reject/{offerId}")
    public Offer rejectOfferByOfferId(@PathVariable Integer offerId){
        Offer offerReference;
        try{
            offerReference = offerService.findOfferByOfferId(offerId);
        }catch (NotFoundException nfe){
            return null;
        }

        if(offerReference.getStatus() != 0) {
            return null;
        }

        offerReference.setStatus(2);
        return offerService.updateOffer(offerReference);
    }

    @GetMapping("/offer/to/{clientId}")
    public List<Offer> getOfferByClientId(@PathVariable Integer clientId){
        User user;
        try{
            user = userService.findUserByUserId(clientId);
        }catch (NotFoundException nfe){
            return null;
        }

        if(user.getType() != 2){
            return null;
        }

        return offerService.findOfferByClientId(clientId);
    }

    @GetMapping("/offer/from/{managerId}")
    public List<Offer> getOfferByManagerId(@PathVariable Integer managerId){
        User user;
        try{
            user = userService.findUserByUserId(managerId);
        }catch (NotFoundException nfe){
            return null;
        }

        if(user.getType() != 1){
            return null;
        }

        return offerService.findOfferByManagerId(managerId);
    }

    @GetMapping("/offer/all")
    public List<Offer> getAllOffers(){
        return offerService.findAllOffers();
    }
}
