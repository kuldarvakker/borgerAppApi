package com.qminder.borger.app.repository;

import com.qminder.borger.app.domain.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    Page<Photo> findByBurgerJointId(Long burgerJointId, Pageable pageable);
    Optional<Photo> findByIdAndBurgerJointId(Long id, Long burgerJointId);
}
