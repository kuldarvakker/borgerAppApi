package com.qminder.borger.app;

import com.qminder.borger.app.dto.BurgerJointDto;
import com.qminder.borger.app.mapper.BurgerJointsMapper;
import com.qminder.borger.app.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    private final BurgerJointsMapper burgerJointsMapper = new BurgerJointsMapper();

    @CrossOrigin
    @GetMapping("/burgerjoints")
    @ResponseBody
    public ResponseEntity<List<BurgerJointDto>> findAllBurgerJoints() {
        return ok(burgerJointsMapper.toDtos(appService.findAllBurgerJoints()));
    }

    @CrossOrigin
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<List<BurgerJointDto>> updateBurgerJoints() {
        return ok(burgerJointsMapper.toDtos(appService.findAndSaveNewBurgerJoints()));
    }
}
