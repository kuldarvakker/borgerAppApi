package com.qminder.borger.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String photoUrl;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private BurgerJoint burgerJoint;

}
