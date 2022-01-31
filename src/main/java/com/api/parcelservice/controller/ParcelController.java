package com.api.parcelservice.controller;

import com.api.parcelservice.domain.AddParcelRequest;
import com.api.parcelservice.domain.ChangeParcelStatusRequest;
import com.api.parcelservice.domain.UpdDestinationRequest;
import com.api.parcelservice.entity.ParcelEntity;
import com.api.parcelservice.service.ParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PutMapping("cancel/{id}?user_id={userId}")
    public ResponseEntity<ParcelEntity> cancelParcel(@PathVariable Long id,
                                                     @PathVariable Long userId) {
        return parcelService.cancelParcel(id, userId);
    }

    @GetMapping("{id}?user_id={userId}")
    public ResponseEntity<ParcelEntity> getParcelOfUser(@PathVariable Long id,
                                                        @PathVariable Long userId) {
        return parcelService.getParcelOfUser(id, userId);
    }

    @GetMapping("all?user_id={userId}")
    public ResponseEntity<List<ParcelEntity>> getAllByUser(@PathVariable Long userId) {
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

    @PutMapping("assign/{id}?courier_id={courierId}")
    public ResponseEntity<ParcelEntity> assignToCourier(@PathVariable Long id,
                                                        @PathVariable Long courierId) {
        return parcelService.assingToCour(id, courierId);
    }

    @GetMapping("all?courier_id={courierId}")
    public ResponseEntity<List<ParcelEntity>> getAllByCourier(@PathVariable Long courierId) {
        return parcelService.getAllAssignToCour(courierId);
    }

    @PutMapping("status/change?courier_id={courierId}")
    public ResponseEntity<ParcelEntity> changeParcelStatusOfCourier(@RequestBody @Valid ChangeParcelStatusRequest request,
                                                                    @PathVariable Long courierId) {
        return parcelService.changeParcelStatusOfCour(request, courierId);
    }

    @GetMapping("{id}?courier_id={courierId}")
    public ResponseEntity<ParcelEntity> getParcelOfCourier(@PathVariable Long id,
                                                           @PathVariable Long courierId) {
        return parcelService.getParcelInfoOfCour(id, courierId);
    }


}
