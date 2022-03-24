package com.qminder.borger.app.service;

import com.qminder.borger.app.domain.BurgerJoint;
import com.qminder.borger.app.repository.BurgerJointRepository;
import com.qminder.borger.foursquare.FoursquareService;
import com.qminder.borger.imageRecognize.BurgerImageRecognizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppService {

    private final BurgerJointRepository burgerJointRepository;

    private final FoursquareService foursquareService;
    private final BurgerImageRecognizeService burgerImageRecognizeService;

    public List<BurgerJoint> findAllBurgerJoints() {
        return burgerJointRepository.findAll();
    }

    public List<BurgerJoint> findAndSaveNewBurgerJoints() {
        var newJoints = getNewBurgerJoints();
        return burgerJointRepository.saveAll(newJoints);
    }

    private List<BurgerJoint> getNewBurgerJoints() {
        var apiJoints = foursquareService.getTartuBurgerJoints();
        var repoJoints = burgerJointRepository.findAll();
        // NB! heavy filter operation
        var operationCreation = LocalDateTime.now(Clock.systemUTC());
        var newJoints =
                apiJoints.stream()
                .filter(b -> repoJoints.stream().noneMatch(o -> b.getFsqId().equals(o.getFsqId())))
                .map(b -> {
                    b.setPhotoUrl(getBurgerPhotoUrlByFsqId(b.getFsqId()));
                    b.setCreatedAt(operationCreation);
                    return b;
                })
                .collect(Collectors.toList());
        return newJoints;
    }

    private String getBurgerPhotoUrlByFsqId(String fsqId) {
        var burgerPhotoUrl = burgerImageRecognizeService.findFirstBurgerImage(
                foursquareService.getTartuBurgerJointPhotos(fsqId));
        if(burgerPhotoUrl.isEmpty() || burgerPhotoUrl.isBlank()) {
            return "";
        }
        return burgerPhotoUrl;
    }
}
