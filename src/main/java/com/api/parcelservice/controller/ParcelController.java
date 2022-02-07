package com.api.parcelservice.controller;

import com.api.parcelservice.domain.*;
import com.api.parcelservice.dto.IdDTO;
import com.api.parcelservice.entity.ParcelEntity;
import com.api.parcelservice.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parcel/")
public class ParcelController {

    private final JmsTemplate jmsTemplate;


    @Secured("ROLE_USER")
    @PostMapping("create")
    public ResponseEntity<ParcelEntity> createParcel(@RequestBody @Valid AddParcelRequest addParcelRequest) {
//        return parcelService.createParcel(request);

        jmsTemplate.convertAndSend("requestqueue", addParcelRequest);
        return ResponseEntity.ok((ParcelEntity) jmsTemplate
                .receiveAndConvert("responsequeue"));

    }

    @Secured("ROLE_USER")
    @PutMapping("upd/destination")
    public ResponseEntity<ParcelEntity> updateParcelDest(@RequestBody @Valid UpdDestinationRequest updDestinationRequest) {
//        return parcelService.updateParcelDestOfUser(request);

        jmsTemplate.convertAndSend("requestqueue", updDestinationRequest);
        return ResponseEntity.ok((ParcelEntity) jmsTemplate
                .receiveAndConvert("responsequeue"));
    }

    @Secured("ROLE_USER")
    @PutMapping("cancel")
    public ResponseEntity<ParcelEntity> cancelParcel(@RequestBody @Valid CancelRequest cancelRequest) {

        jmsTemplate.convertAndSend("requestqueue", cancelRequest);
        return ResponseEntity.ok((ParcelEntity) jmsTemplate
                .receiveAndConvert("responsequeue"));
//        return parcelService.cancelParcel(request);
    }

    @Secured("ROLE_USER")
    @GetMapping("/user/{parcelId}")
    public ResponseEntity<ParcelEntity> getParcelOfUser(@PathVariable Long parcelId,
                                                        @RequestParam(name = "user_id") Long userId) {
        IdDTO idDTO = IdDTO.builder()
                .parcelId(parcelId)
                .userId(userId)
                .build();


        jmsTemplate.convertAndSend("requestqueue", idDTO);
        return ResponseEntity.ok((ParcelEntity) jmsTemplate
                .receiveAndConvert("responsequeue"));
//        return parcelService.getParcelOfUser(id, userId);
    }

    @Secured("ROLE_USER")
    @GetMapping("/user/all")
    public ResponseEntity<List<ParcelEntity>> getAllByUser(@RequestParam(name = "user_id") Long userId) {
        IdDTO idDTO = IdDTO.builder()
//                .parcelId(parcelId)
                .userId(userId)
                .build();


        jmsTemplate.convertAndSend("requestqueue", idDTO);
        return ResponseEntity.ok((List<ParcelEntity>) jmsTemplate
                .receiveAndConvert("responsequeue"));
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("all")
    public ResponseEntity<List<ParcelEntity>> getAll() {


        jmsTemplate.convertAndSend("requestqueue", "get all parcel");
        return ResponseEntity.ok((List<ParcelEntity>) jmsTemplate
                .receiveAndConvert("responsequeue"));

//        return parcelService.getAll();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("status/change")
    public ResponseEntity<ParcelEntity> changeStatus(@RequestBody @Valid ChangeParcelStatusRequest changeParcelStatusRequest) {

        jmsTemplate.convertAndSend("requestqueue", changeParcelStatusRequest);
        return ResponseEntity.ok((ParcelEntity) jmsTemplate
                .receiveAndConvert("responsequeue"));

//        return parcelService.changeParcelStatus(request);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("courier/assign")
    public ResponseEntity<ParcelEntity> assignToCourier(@RequestBody @Valid AssignToCourRequest assignToCourRequest) {

        jmsTemplate.convertAndSend("requestqueue", assignToCourRequest);
        return ResponseEntity.ok((ParcelEntity) jmsTemplate
                .receiveAndConvert("responsequeue"));
    }

    @Secured("ROLE_COURIER")
    @GetMapping("courier/all")
    public ResponseEntity<List<ParcelEntity>> getAllByCourier( @RequestParam(name = "courier_id") Long courierId) {
        IdDTO idDTO = IdDTO.builder()
                .courId(courierId)
                .build();


        jmsTemplate.convertAndSend("requestqueue", idDTO);
        return ResponseEntity.ok((List<ParcelEntity>) jmsTemplate
                .receiveAndConvert("responsequeue"));
    }

    @Secured("ROLE_COURIER")
    @PutMapping("courier/status/change")
    public ResponseEntity<ParcelEntity> changeParcelStatusOfCourier(@RequestBody
                                                                        @Valid
                                                                                ChangeCourParcelStatusRequest
                                                                                changeCourParcelStatusRequest) {


        jmsTemplate.convertAndSend("requestqueue", changeCourParcelStatusRequest);
        return ResponseEntity.ok((ParcelEntity) jmsTemplate
                .receiveAndConvert("responsequeue"));

        //        return parcelService.changeParcelStatusOfCour(request);
    }

    @Secured("ROLE_COURIER")
    @GetMapping("courier/{parcelId}")
    public ResponseEntity<ParcelEntity> getParcelOfCourier(@PathVariable Long parcelId,
                                                           @RequestParam(name = "courier_id") Long courierId) {
//        return parcelService.getParcelInfoOfCour(id, courierId);
        IdDTO idDTO = IdDTO.builder()
                .parcelId(parcelId)
                .courId(courierId)
                .build();


        jmsTemplate.convertAndSend("requestqueue", idDTO);
        return ResponseEntity.ok((ParcelEntity) jmsTemplate
                .receiveAndConvert("responsequeue"));
    }


}
