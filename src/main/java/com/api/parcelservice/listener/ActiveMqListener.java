package com.api.parcelservice.listener;

import com.api.parcelservice.domain.*;
import com.api.parcelservice.dto.IdDTO;
import com.api.parcelservice.entity.ParcelEntity;
import com.api.parcelservice.exception.ParcelException;
import com.api.parcelservice.service.ParcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ActiveMqListener {

    private final JmsTemplate jmsTemplate;
    private final ParcelService parcelService;

    @JmsListener(destination = "parcel-create-req-queue")
    public void getCreateParcel(AddParcelRequest addParcelRequest) {
        log.info("message received: " + addParcelRequest);
        jmsTemplate.convertAndSend("parcel-create-res-queue",
                parcelService.createParcel(addParcelRequest));
    }

    @JmsListener(destination = "parcel-updatedest-req-queue")
    public void getUpdateParcelDestOfUser(UpdDestinationRequest updDestinationRequest) {
        log.info("message received: " + updDestinationRequest);
        try {
            jmsTemplate.convertAndSend("parcel-updatedest-res-queue",
                    parcelService.updateParcelDestOfUser(updDestinationRequest));
        } catch (ParcelException e) {
            ParcelEntity parcelEntity = new ParcelEntity();
            parcelEntity.setErrCode(e.getErrorCode());
            parcelEntity.setErrDescription(e.getMessage());
            jmsTemplate.convertAndSend("parcel-updatedest-res-queue",
                    parcelEntity);
        }
    }

    @JmsListener(destination = "parcel-cancel-req-queue")
    public void getCancelParcel(CancelRequest cancelRequest) {
        log.info("message received: " + cancelRequest);
        try {
            jmsTemplate.convertAndSend("parcel-cancel-res-queue",
                    parcelService.cancelParcel(cancelRequest));
        } catch (ParcelException e) {
            ParcelEntity parcelEntity = new ParcelEntity();
            parcelEntity.setErrCode(e.getErrorCode());
            parcelEntity.setErrDescription(e.getMessage());
            jmsTemplate.convertAndSend("parcel-cancel-res-queue",
                    parcelEntity);
        }
    }

    @JmsListener(destination = "parcel-getparcelofuser-req-queue")
    public void getParcelOfUser(IdDTO idDTO) {
        log.info("message received: " + idDTO);
        try {
            jmsTemplate.convertAndSend("parcel-getparcelofuser-res-queue",
                    parcelService.getParcelOfUser(idDTO.getParcelId(), idDTO.getUserId()));
        } catch (ParcelException e) {
            ParcelEntity parcelEntity = new ParcelEntity();
            parcelEntity.setErrCode(e.getErrorCode());
            parcelEntity.setErrDescription(e.getMessage());
            jmsTemplate.convertAndSend("parcel-getparcelofuser-res-queue",
                    parcelEntity);
        }

    }

    @JmsListener(destination = "parcel-getallbyuser-req-queue")
    public void getAllByUserId(IdDTO idDTO) {
        log.info("message received: " + idDTO);
        try {
            jmsTemplate.convertAndSend("parcel-getallbyuser-res-queue",
                    parcelService.getAllByUserId(idDTO.getUserId()));
        } catch (ParcelException e) {
            ParcelEntity parcelEntity = new ParcelEntity();
            parcelEntity.setErrCode(e.getErrorCode());
            parcelEntity.setErrDescription(e.getMessage());
            jmsTemplate.convertAndSend("parcel-getallbyuser-res-queue",
                    parcelEntity);
        }

    }

    @JmsListener(destination = "parcel-getall-req-queue")
    public void getAll(String message) {
        log.info("message received: " + message);
        jmsTemplate.convertAndSend("parcel-getall-res-queue", parcelService.getAll());
    }

    @JmsListener(destination = "parcel-changestatus-req-queue")
    public void getChangeParcelStatus(ChangeParcelStatusRequest changeParcelStatusRequest) {
        log.info("message received: " + changeParcelStatusRequest);
        try {
            jmsTemplate.convertAndSend("parcel-changestatus-res-queue",
                    parcelService.changeParcelStatus(changeParcelStatusRequest));
        } catch (ParcelException e) {
            ParcelEntity parcelEntity = new ParcelEntity();
            parcelEntity.setErrCode(e.getErrorCode());
            parcelEntity.setErrDescription(e.getMessage());
            jmsTemplate.convertAndSend("parcel-changestatus-res-queue",
                    parcelEntity);
        }
    }

    @JmsListener(destination = "parcel-assigntocour-req-queue")
    public void getAssignToCour(AssignToCourRequest assignToCourRequest) {
        log.info("message received: " + assignToCourRequest);
        try {
            jmsTemplate.convertAndSend("parcel-assigntocour-res-queue",
                    parcelService.assignToCour(assignToCourRequest));
        } catch (ParcelException e) {
            ParcelEntity parcelEntity = new ParcelEntity();
            parcelEntity.setErrCode(e.getErrorCode());
            parcelEntity.setErrDescription(e.getMessage());
            jmsTemplate.convertAndSend("parcel-assigntocour-res-queue",
                    parcelEntity);
        }
    }

    @JmsListener(destination = "parcel-getallbycour-req-queue")
    public void getAllAssignToCour(IdDTO idDTO) {
        log.info("message received: " + idDTO);
        try {
            jmsTemplate.convertAndSend("parcel-getallbycour-res-queue",
                    parcelService.getAllAssignToCour(idDTO.getCourId()));

        } catch (ParcelException e) {
            ParcelEntity parcelEntity = new ParcelEntity();
            parcelEntity.setErrCode(e.getErrorCode());
            parcelEntity.setErrDescription(e.getMessage());
            jmsTemplate.convertAndSend("parcel-getallbycour-res-queue",
                    parcelEntity);

        }
    }

    @JmsListener(destination = "parcel-changeparcelstatusofcour-req-queue")
    public void getChangeParcelStatusOfCour(ChangeCourParcelStatusRequest changeCourParcelStatusRequest) {
        log.info("message received: " + changeCourParcelStatusRequest);

        try {
            jmsTemplate.convertAndSend("parcel-changeparcelstatusofcour-res-queue",
                    parcelService.changeParcelStatusOfCour(changeCourParcelStatusRequest));

        } catch (ParcelException e) {
            ParcelEntity parcelEntity = new ParcelEntity();
            parcelEntity.setErrCode(e.getErrorCode());
            parcelEntity.setErrDescription(e.getMessage());
            jmsTemplate.convertAndSend("parcel-changeparcelstatusofcour-res-queue",
                    parcelEntity);

        }
    }

    @JmsListener(destination = "parcel-getparcelofcour-req-queue")
    public void getParcelInfoOfCour(IdDTO idDTO) {
        log.info("message received: " + idDTO);
        try {
            jmsTemplate.convertAndSend("parcel-getparcelofcour-res-queue",
                    parcelService.getParcelInfoOfCour(idDTO.getParcelId(), idDTO.getCourId()));
        } catch (ParcelException e) {
            ParcelEntity parcelEntity = new ParcelEntity();
            parcelEntity.setErrCode(e.getErrorCode());
            parcelEntity.setErrDescription(e.getMessage());
            jmsTemplate.convertAndSend("parcel-getparcelofcour-res-queue",
                    parcelEntity);
        }
    }


}
