package com.zhiyuan.paymentsystem.services;

import com.zhiyuan.paymentsystem.models.Offer;

import java.util.List;

/**
 * Created by Zhiyuan Yao
 */
public interface OfferService {
    Offer saveOffer(Offer offer);
    Offer updateOffer(Offer offer);

    Offer findOfferByOfferId(Integer offerId);
    List<Offer> findOfferByClientId(Integer clientId);
    List<Offer> findOfferByManagerId(Integer managerId);
    List<Offer> findOfferByClinetIdAndManagerId(Integer clientId, Integer managerId);

    void deleteOfferByOfferId(Integer offerId);

    List<Offer> findAllOffers();
}
