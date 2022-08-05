package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.vehicle.RegisterVehicleRequestDto;
import com.sanket.designparkinglot.dtos.vehicle.RegisterVehicleResponseDto;
import com.sanket.designparkinglot.exceptions.EntityAlreadyExistsException;
import com.sanket.designparkinglot.models.vehicle.Vehicle;
import com.sanket.designparkinglot.models.vehicle.VehicleType;
import com.sanket.designparkinglot.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class VehicleController {

    public final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public RegisterVehicleResponseDto registerVehicle(RegisterVehicleRequestDto registerVehicleRequestDto) {

        RegisterVehicleResponseDto registerVehicleResponseDto = new RegisterVehicleResponseDto();
        String vehicleNumber = registerVehicleRequestDto.getVehicleNumber();
        VehicleType vehicleType = registerVehicleRequestDto.getVehicleType();

        try {
            // call service method
            Vehicle vehicle = vehicleService.registerVehicle(vehicleNumber, vehicleType);
            registerVehicleResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            registerVehicleResponseDto.setVehicle(vehicle);
        } catch (Exception | EntityAlreadyExistsException e) {
            registerVehicleResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            registerVehicleResponseDto.setMessage(e.getMessage());
        }

        return registerVehicleResponseDto;
    }
}
