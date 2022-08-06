package com.sanket.designparkinglot.controllers;

import com.sanket.designparkinglot.dtos.base.response.ResponseStatus;
import com.sanket.designparkinglot.dtos.bill.CreateBillRequestDto;
import com.sanket.designparkinglot.dtos.bill.CreateBillResponseDto;
import com.sanket.designparkinglot.exceptions.InvalidExitGateException;
import com.sanket.designparkinglot.exceptions.NoGateException;
import com.sanket.designparkinglot.exceptions.NoTicketException;
import com.sanket.designparkinglot.models.bill.Bill;
import com.sanket.designparkinglot.services.BillService;
import com.sanket.designparkinglot.strategies.feescalculation.FeesCalculationStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    public CreateBillResponseDto createBill(CreateBillRequestDto createBillRequestDto) {
        CreateBillResponseDto createBillResponseDto = new CreateBillResponseDto();
        long gateId = createBillRequestDto.getGateId();
        long ticketId = createBillRequestDto.getTicketId();
        FeesCalculationStrategyType feesCalculationStrategyType = createBillRequestDto.getFeesCalculationStrategyType();

        try {
            // call to service method
            Bill bill = billService.createBill(gateId, ticketId, feesCalculationStrategyType);
            createBillResponseDto.setBill(bill);
            createBillResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception | NoGateException | InvalidExitGateException | NoTicketException e) {
            createBillResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            createBillResponseDto.setMessage(e.getMessage());
        }
        return createBillResponseDto;
    }
}
