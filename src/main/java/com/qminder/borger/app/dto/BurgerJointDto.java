package com.qminder.borger.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BurgerJointDto {

    private long id;
    private String fsqId;
    private String name;
    private String photoUrl;
    private Double longitude;
    private Double latitude;

    private LocalDateTime createdAt;
}
