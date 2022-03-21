package com.qminder.borger.app.repository;

import com.qminder.borger.app.domain.BurgerJoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BurgerJointRepository extends JpaRepository<BurgerJoint, Long> {

}
