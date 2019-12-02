package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.exceptions.NotFoundException;
import com.zhiyuan.paymentsystem.models.Offer;
import com.zhiyuan.paymentsystem.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
@Slf4j
@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Offer offer) {
        Offer offerReference;
        try{
            offerReference = findOfferByOfferId(offer.getOfferId());
        }catch (NotFoundException nfe){
            return null;
        }

        offerReference.setOfferText(offer.getOfferText());
        offerReference.setStatus(offer.getStatus());
        return offerRepository.save(offerReference);
    }

    @Override
    public Offer findOfferByOfferId(Integer offerId) {
        Optional<Offer> offerOptional = offerRepository.findOfferByOfferId(offerId);
        if(!offerOptional.isPresent()){
            log.error("Offer with ID:{} is not found.[from OfferServiceImpl.findOfferByOfferId()]", offerId);
            throw new NotFoundException("Offer with ID:" + offerId + " is not found.");
        }
        return offerOptional.get();
    }

    @Override
    public List<Offer> findOfferByClientId(Integer clientId) {
        return offerRepository.findOfferByClientId(clientId);
    }

    @Override
    public List<Offer> findOfferByManagerId(Integer managerId) {
        return offerRepository.findOfferByManagerId(managerId);
    }

    @Override
    public List<Offer> findOfferByClinetIdAndManagerId(Integer clientId, Integer managerId) {
        return offerRepository.findOfferByClientIdAndManagerId(clientId, managerId);
    }

    @Override
    public void deleteOfferByOfferId(Integer offerId) {
        offerRepository.deleteOfferByOfferId(offerId);
    }
}
