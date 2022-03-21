package com.qminder.borger.app.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String photoUrl;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private BurgerJoint burgerJoint;

}
