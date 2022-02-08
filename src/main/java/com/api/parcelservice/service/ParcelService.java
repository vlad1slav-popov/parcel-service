package com.api.parcelservice.service;

import com.api.parcelservice.domain.*;
import com.api.parcelservice.entity.ParcelEntity;
import com.api.parcelservice.entity.Status;
import com.api.parcelservice.exception.ParcelException;
import com.api.parcelservice.repository.ParcelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParcelService {

    private final ParcelRepository parcelRepository;


    public ParcelEntity createParcel(AddParcelRequest request) {

        ParcelEntity parcelEntity = new ParcelEntity();
        BeanUtils.copyProperties(request, parcelEntity);

        if (Objects.isNull(request.getCourierId())) {
            parcelEntity.setStatus(Status.WAITING_FOR_COURIER);
        } else {
            parcelEntity.setStatus(Status.ACCEPTED);
        }

        parcelRepository.save(parcelEntity);

        return parcelEntity;

    }

    public ParcelEntity updateParcelDestOfUser(UpdDestinationRequest request) {

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

        return parcelInfo;

    }

    public ParcelEntity cancelParcel(CancelRequest request) {
        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                .getParcelEntityByIdAndUserId(request.getId(),
                        request.getUserId())).orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));

        parcelInfo.setStatus(Status.CANCEL);
        parcelRepository.save(parcelInfo);

        return parcelInfo;

    }

    public ParcelEntity getParcelOfUser(Long parcelId,
                                        Long userId) {

        return Optional.ofNullable(parcelRepository
                .getParcelEntityByIdAndUserId(parcelId,
                        userId)).orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));
    }

    public List<ParcelEntity> getAllByUserId(Long userId) {
        return parcelRepository.getAllByUserId(userId);
    }

    public List<ParcelEntity> getAll() {
        return parcelRepository.findAll();
    }

    public ParcelEntity changeParcelStatus(ChangeParcelStatusRequest request) {
        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                        .getParcelEntityById(request.getId()))
                .orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));

        parcelInfo.setStatus(request.getStatus());

        parcelRepository.save(parcelInfo);

        return parcelInfo;
    }

    public ParcelEntity assignToCour(AssignToCourRequest request) {
        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                        .getParcelEntityById(request.getId()))
                .orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));

        parcelInfo.setCourierId(request.getCourierId());
        parcelInfo.setStatus(Status.ACCEPTED);

        return parcelInfo;

    }

    public List<ParcelEntity> getAllAssignToCour(Long courId) {
        return parcelRepository.getAllByCourierId(courId);
    }


    public ParcelEntity changeParcelStatusOfCour(ChangeCourParcelStatusRequest request) {
        ParcelEntity parcelInfo = Optional.ofNullable(parcelRepository
                        .getParcelEntityByIdAndCourierId(request.getId(),
                                request.getCourierId()))
                .orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));
        parcelInfo.setStatus(request.getStatus());

        parcelRepository.save(parcelInfo);
        return parcelInfo;
    }

    public ParcelEntity getParcelInfoOfCour(Long parcelId,
                                            Long courId) {

        return Optional.ofNullable(parcelRepository
                        .getParcelEntityByIdAndCourierId(parcelId,
                                courId))
                .orElseThrow(() -> new ParcelException("PARCEL_NOT_FOUND", "001"));

    }


}
