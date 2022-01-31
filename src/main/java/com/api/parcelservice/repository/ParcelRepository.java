package com.api.parcelservice.repository;

import com.api.parcelservice.entity.ParcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {

    ParcelEntity getParcelEntityById(Long id);

    ParcelEntity getParcelEntityByIdAndCourierId(Long id,
                                                 Long courierId);

    ParcelEntity getParcelEntityByOrderId(String orderId);

    ParcelEntity getParcelEntityByOrderIdAndUserId(String orderId,
                                                   Long userId);

    ParcelEntity getParcelEntityByIdAndUserId(Long id,
                                              Long userId);

    ParcelEntity getParcelEntityByOrderIdAndCourierId(String orderId,
                                                      Long id);

    List<ParcelEntity> getAllByUserId(Long userId);

    List<ParcelEntity> getAllByCourierId(Long courId);

}
