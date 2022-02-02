package com.api.parcelservice.controller;

import com.api.parcelservice.domain.*;
import com.api.parcelservice.entity.ParcelEntity;
import com.api.parcelservice.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parcel/")
public class ParcelController {

    private final ParcelService parcelService;


    @PostMapping("create")
    public ResponseEntity<ParcelEntity> createParcel(@RequestBody @Valid AddParcelRequest request) {
        return parcelService.createParcel(request);
    }

    @PutMapping("upd/destination")
    public ResponseEntity<ParcelEntity> updateParcelDest(@RequestBody @Valid UpdDestinationRequest request) {
        return parcelService.updateParcelDestOfUser(request);
    }

    @PutMapping("cancel")
    public ResponseEntity<ParcelEntity> cancelParcel(@RequestBody @Valid CancelRequest request) {
        return parcelService.cancelParcel(request);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ParcelEntity> getParcelOfUser(@PathVariable Long id,
                                                        @RequestParam(name = "user_id") Long userId) {
        return parcelService.getParcelOfUser(id, userId);
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<ParcelEntity>> getAllByUser(@RequestParam(name = "user_id") Long userId) {
        return parcelService.getAllByUserId(userId);
    }

    @GetMapping("all")
    public ResponseEntity<List<ParcelEntity>> getAll() {
        return parcelService.getAll();
    }

    @PutMapping("status/change")
    public ResponseEntity<ParcelEntity> changeStatus(@RequestBody @Valid ChangeParcelStatusRequest request) {
        return parcelService.changeParcelStatus(request);
    }

    @PutMapping("courier/assign")
    public ResponseEntity<ParcelEntity> assignToCourier(@RequestBody @Valid AssignToCourRequest request) {
        return parcelService.assingToCour(request);
    }

    @GetMapping("courier/all")
    public ResponseEntity<List<ParcelEntity>> getAllByCourier( @RequestParam(name = "courier_id") Long courierId) {
        return parcelService.getAllAssignToCour(courierId);
    }

    @PutMapping("courier/status/change")
    public ResponseEntity<ParcelEntity> changeParcelStatusOfCourier(@RequestBody @Valid ChangeCourParcelStatusRequest request) {
        return parcelService.changeParcelStatusOfCour(request);
    }

    @GetMapping("courier/{id}")
    public ResponseEntity<ParcelEntity> getParcelOfCourier(@PathVariable Long id,
                                                           @RequestParam(name = "courier_id") Long courierId) {
        return parcelService.getParcelInfoOfCour(id, courierId);
    }


}
