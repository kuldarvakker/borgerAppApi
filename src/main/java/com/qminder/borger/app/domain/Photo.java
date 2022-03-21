package com.qminder.borger.app.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String photoUrl;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private BurgerJoint burgerJoint;

}
