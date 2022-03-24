package com.qminder.borger.app.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class BurgerJoint {

    public BurgerJoint(String fsqId, String name, Double longitude, Double latitude) {
        this.fsqId = fsqId;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fsqId;
    private String name;
    private String photoUrl;
    private Double longitude;
    private Double latitude;

    private LocalDateTime createdAt;
}
