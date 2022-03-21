package com.qminder.borger.app;

import com.qminder.borger.app.dto.BurgerJointDto;
import com.qminder.borger.app.dto.PhotoDto;
import com.qminder.borger.app.mapper.BurgerJointsMapper;
import com.qminder.borger.app.mapper.PhotoMapper;
import com.qminder.borger.app.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    private final BurgerJointsMapper burgerJointsMapper = new BurgerJointsMapper();
    private final PhotoMapper photoMapper = new PhotoMapper();

    @GetMapping("/burgerjoints")
    @ResponseBody
    public ResponseEntity<List<BurgerJointDto>> findAllBurgerJoints() {
        return ok(burgerJointsMapper.toDtos(appService.findAllBurgerJoints()));
    }

    @GetMapping("/photos")
    @ResponseBody
    public ResponseEntity<List<PhotoDto>> findAllBurgerJointsPhotos() {
        return ok(photoMapper.toDtos(appService.findAllBurgerJointsPhotos()));
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateBurgerJoints() {
        appService.checkForNewBurgerJoints();
        return ok().build();
    }
}
