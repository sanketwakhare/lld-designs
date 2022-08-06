package com.sanket.designparkinglot.dtos.ticket;

import com.sanket.designparkinglot.dtos.base.request.CreateRequestDto;
import com.sanket.designparkinglot.strategies.spotassignment.SpotAssignmentStrategyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTicketRequestDto extends CreateRequestDto {
    private long parkingLotId;
    private long gateId;
    private long vehicleId;
    private SpotAssignmentStrategyType spotAssignmentStrategyType;
}
