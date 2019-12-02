package com.zhiyuan.paymentsystem.repository;

import com.zhiyuan.paymentsystem.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zhiyuan Yao
 */
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    Optional<Offer> findOfferByOfferId(Integer offerId);

    List<Offer> findOfferByClientId(Integer clientId);
    List<Offer> findOfferByManagerId(Integer managerId);
    List<Offer> findOfferByClientIdAndManagerId(Integer clientId, Integer managerId);

    void deleteOfferByOfferId(Integer offerId);
}
