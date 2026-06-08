package com.manoj.UrlShortner.model.dto;

import java.time.LocalDate;

import lombok.Data;
@Data

public class ClickEventDto {
      private LocalDate clickDate;
    private Long count;
    
}
