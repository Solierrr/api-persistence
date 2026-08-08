package com.solaria.persistence.DTO.Response;

import com.solaria.persistence.Domain.enums.DayWeek;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftResponseDTO {

    private UUID id;
    private UUID technicianId;
    private DayWeek dayWeek;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
