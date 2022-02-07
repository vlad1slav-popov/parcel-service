package com.api.parcelservice.listener;

import com.api.parcelservice.domain.*;
import com.api.parcelservice.dto.IdDTO;
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

    @JmsListener(destination = "requestqueue")
    public void getCreateParcel(AddParcelRequest addParcelRequest) {
        log.info("message received: " + addParcelRequest);
        jmsTemplate.convertAndSend("responsequeue",
                parcelService.createParcel(addParcelRequest));
    }

    @JmsListener(destination = "requestqueue")
    public void getUpdateParcelDestOfUser(UpdDestinationRequest updDestinationRequest) {
        log.info("message received: " + updDestinationRequest);
        jmsTemplate.convertAndSend("responsequeue",
                parcelService.updateParcelDestOfUser(updDestinationRequest));
    }

    @JmsListener(destination = "requestqueue")
    public void getCancelParcel(CancelRequest cancelRequest) {
        log.info("message received: " + cancelRequest);
        jmsTemplate.convertAndSend("responsequeue",
                parcelService.cancelParcel(cancelRequest));
    }

    @JmsListener(destination = "requestqueue")
    public void getParcelOfUser(IdDTO idDTO) {
        log.info("message received: " + idDTO);
        jmsTemplate.convertAndSend("responsequeue",
                parcelService.getParcelOfUser(idDTO.getParcelId(), idDTO.getUserId()));
    }

    @JmsListener(destination = "requestqueue")
    public void getAllByUserId(IdDTO idDTO) {
        log.info("message received: " + idDTO);
        jmsTemplate.convertAndSend("responsequeue",
                parcelService.getAllByUserId(idDTO.getUserId()));
    }

    @JmsListener(destination = "requestqueue")
    public void getAll(String message) {
        log.info("message received: " + message);
        jmsTemplate.convertAndSend("responsequeue", parcelService.getAll());
    }

    @JmsListener(destination = "requestqueue")
    public void getChangeParcelStatus(ChangeParcelStatusRequest changeParcelStatusRequest) {
        log.info("message received: " + changeParcelStatusRequest);
        jmsTemplate.convertAndSend("responsequeue",
                parcelService.changeParcelStatus(changeParcelStatusRequest));
    }

    @JmsListener(destination = "requestqueue")
    public void getAssignToCour(AssignToCourRequest assignToCourRequest) {
        log.info("message received: " + assignToCourRequest);
        jmsTemplate.convertAndSend("responsequeue",
                parcelService.assingToCour(assignToCourRequest));
    }

    @JmsListener(destination = "requestqueue")
    public void getAllAssignToCour(IdDTO idDTO) {
        log.info("message received: " + idDTO);
        jmsTemplate.convertAndSend("responsequeue",
                parcelService.getAllAssignToCour(idDTO.getCourId()));
    }

    @JmsListener(destination = "requestqueue")
    public void getChangeParcelStatusOfCour(ChangeCourParcelStatusRequest changeCourParcelStatusRequest) {
        log.info("message received: " + changeCourParcelStatusRequest);
        jmsTemplate.convertAndSend("responsequeue",
                parcelService.changeParcelStatusOfCour(changeCourParcelStatusRequest));
    }

    @JmsListener(destination = "requestqueue")
    public void getParcelInfoOfCOur(IdDTO idDTO) {
        log.info("message received: " + idDTO);
        jmsTemplate.convertAndSend("responsequeue",
                parcelService.getParcelInfoOfCour(idDTO.getParcelId(), idDTO.getCourId()));
    }


}
