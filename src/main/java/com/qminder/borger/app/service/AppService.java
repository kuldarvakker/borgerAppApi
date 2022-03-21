package com.qminder.borger.app.service;

import com.qminder.borger.app.domain.BurgerJoint;
import com.qminder.borger.app.repository.BurgerJointRepository;
import com.qminder.borger.app.repository.PhotoRepository;
import com.qminder.borger.foursquare.FoursquareService;
import com.qminder.borger.imageRecognize.BurgerImageRecognizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppService {

    private final BurgerJointRepository burgerJointRepository;
    private final PhotoRepository photoRepository;

    private final FoursquareService foursquareService;
    private final BurgerImageRecognizeService burgerImageRecognizeService;

    public List<BurgerJoint> findAllBurgerJoints() {
        List<BurgerJoint> result = new ArrayList<>();
        // TODO
        // get BurgerJoints
        // get Photos Of BurgerJoints
        // filter photos
        // add valid photos to list
        // pass Full element out

        return result;
    }

    public void updateBurgerJoints() {
        // TODO
        // get all burgerJoints
        // check if any new burgerJoints
        // add new burgerJoints

    }
}
