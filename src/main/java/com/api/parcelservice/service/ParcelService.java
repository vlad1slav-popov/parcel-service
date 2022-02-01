package com.api.parcelservice.service;

import com.api.parcelservice.domain.*;
import com.api.parcelservice.entity.ParcelEntity;
import com.api.parcelservice.entity.Status;
import com.api.parcelservice.exception.ParcelException;
import com.api.parcelservice.repository.ParcelRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final Logger logger;


    public ResponseEntity<ParcelEntity> createParcel(AddParcelRequest request)  {

        ParcelEntity parcelEntity = new ParcelEntity();
        BeanUtils.copyProperties(request, parcelEntity);

        if (Objects.isNull(request.getCourierId())) {
            parcelEntity.setStatus(Status.WAITING_FOR_COURIER);
        } else {
            parcelEntity.setStatus(Status.ACCEPTED);
        }

        parcelRepository.save(parcelEntity);

        return ResponseEntity.ok(parcelEntity);

    }

    public ResponseEntity<ParcelEntity> updateParcelDestOfUser(UpdDestinationRequest request) {

        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                        .getParcelEntityByIdAndUserId(request.getId(),
                                request.getUserId()))
                .orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));


        if (Objects.nonNull(request.getCoordinatesFrom())) {
            parcelInfo.setCoordinatesFrom(request.getCoordinatesFrom());
        }
        if (Objects.nonNull(request.getCoordinatesTo())) {
            parcelInfo.setCoordinatesTo(request.getCoordinatesTo());
        }

        parcelRepository.save(parcelInfo);

        return ResponseEntity.ok(parcelInfo);

    }

    public ResponseEntity<ParcelEntity> cancelParcel(CancelRequest request) {
        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                .getParcelEntityByIdAndUserId(request.getId(),
                        request.getUserId())).orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));

        parcelInfo.setStatus(Status.CANCEL);
        parcelRepository.save(parcelInfo);

        return ResponseEntity.ok(parcelInfo);

    }

    public ResponseEntity<ParcelEntity> getParcelOfUser(Long parcelId,
                                                        Long userId) {
        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                .getParcelEntityByIdAndUserId(parcelId,
                        userId)).orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));

        return ResponseEntity.ok(parcelInfo);
    }

    public ResponseEntity<List<ParcelEntity>> getAllByUserId(Long userId) {
        return ResponseEntity.ok(parcelRepository.getAllByUserId(userId));
    }

    public ResponseEntity<List<ParcelEntity>> getAll() {
        return ResponseEntity.ok(parcelRepository.findAll());
    }

    public ResponseEntity<ParcelEntity> changeParcelStatus(ChangeParcelStatusRequest request) {
        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                        .getParcelEntityById(request.getId()))
                .orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));

        parcelInfo.setStatus(request.getStatus());

        parcelRepository.save(parcelInfo);

        return ResponseEntity.ok(parcelInfo);
    }

    public ResponseEntity<ParcelEntity> assingToCour(AssignToCourRequest request) {
        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                        .getParcelEntityById(request.getId()))
                .orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));


        parcelInfo.setCourierId(request.getCourierId());
        parcelInfo.setStatus(Status.ACCEPTED);

        return ResponseEntity.ok(parcelInfo);

    }

    public ResponseEntity<List<ParcelEntity>> getAllAssignToCour(Long courId) {
        return ResponseEntity.ok(parcelRepository.getAllByCourierId(courId));
    }


    public ResponseEntity<ParcelEntity> changeParcelStatusOfCour(ChangeCourParcelStatusRequest request) {
        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                        .getParcelEntityByIdAndCourierId(request.getId(),
                                request.getCourierId()))
                .orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));
        parcelInfo.setStatus(request.getStatus());

        parcelRepository.save(parcelInfo);
        return ResponseEntity.ok(parcelInfo);
    }

    public ResponseEntity<ParcelEntity> getParcelInfoOfCour(Long parcelId,
                                                            Long courId) {
        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                        .getParcelEntityByIdAndCourierId(parcelId,
                                courId))
                .orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));

        return ResponseEntity.ok(parcelInfo);

    }


}
