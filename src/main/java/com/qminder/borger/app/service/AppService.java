package com.qminder.borger.app.service;

import com.qminder.borger.app.domain.BurgerJoint;
import com.qminder.borger.app.domain.Photo;
import com.qminder.borger.app.repository.BurgerJointRepository;
import com.qminder.borger.app.repository.PhotoRepository;
import com.qminder.borger.foursquare.FoursquareService;
import com.qminder.borger.imageRecognize.BurgerImageRecognizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void checkForNewBurgerJoints() {
        // NB! heavy algorithm

        // get all burgerJoints
        var burgerJoints = foursquareService.getTartuBurgerJoints();
        var repoJoints = burgerJointRepository.findAll();
        // check if any new burgerJoints
        var newJoints = burgerJoints.stream()
                .filter(b -> !repoJoints.contains(b))
                .collect(Collectors.toList());
        for (BurgerJoint newJoint : newJoints) {
            // add new burgerJoints
            var burgerJoint = burgerJointRepository.save(newJoint);
            // save burger photo
            filterAndSavePhoto(burgerJoint);
        }
    }

    private Photo filterAndSavePhoto(BurgerJoint burgerJoint) {
        // filtering included
        var burgerPhoto = burgerImageRecognizeService.findFirstBurgerImage(
                foursquareService.getTartuBurgerJointPhotos(burgerJoint.getFsqId()));
        var photo = new Photo();
        photo.setPhotoUrl(burgerPhoto);
        photo.setCreatedAt(LocalDateTime.now(Clock.systemUTC()));
        photo.setBurgerJoint(burgerJoint);
        return photoRepository.save(photo);
    }
}
