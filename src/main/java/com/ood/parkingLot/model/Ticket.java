package com.ood.parkingLot.model;

import com.ood.parkingLot.enums.TicketStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@ToString
public class Ticket {

    private UUID ticketId;
    private String vehicleRegistrationNumber;
    private UUID parkingSpotId;
    private LocalDateTime parkedAt;
    private LocalDateTime unparkedAt;
    private BigDecimal parkingFee;
    private TicketStatus ticketStatus;
}