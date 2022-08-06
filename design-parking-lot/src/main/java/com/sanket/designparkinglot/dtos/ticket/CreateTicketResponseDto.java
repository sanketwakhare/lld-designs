package com.sanket.designparkinglot.dtos.ticket;

import com.sanket.designparkinglot.dtos.base.response.ResponseDto;
import com.sanket.designparkinglot.models.ticket.Ticket;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTicketResponseDto extends ResponseDto {
    private Ticket ticket;
}
