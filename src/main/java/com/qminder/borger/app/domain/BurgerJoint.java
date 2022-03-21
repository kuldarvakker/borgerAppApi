package com.qminder.borger.app.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BurgerJoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fsqId;
    private String name;
    private Double longitude;
    private Double latitude;

    private LocalDateTime createdAt;

}
