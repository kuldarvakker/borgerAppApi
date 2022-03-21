package com.qminder.borger.app.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PhotoDto {
    private long id;
    private String photoUrl;
    private LocalDateTime createdAt;
    private long burgerJointId;
}
