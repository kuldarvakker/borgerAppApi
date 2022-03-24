package com.qminder.borger.app.mapper;

import com.qminder.borger.app.domain.BurgerJoint;
import com.qminder.borger.app.dto.BurgerJointDto;

import java.util.ArrayList;
import java.util.List;

public class BurgerJointsMapper {

    public BurgerJointDto toDto(BurgerJoint entity) {
        var dto = new BurgerJointDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLongitude(entity.getLongitude());
        dto.setLatitude(entity.getLatitude());
        dto.setFsqId(entity.getFsqId());
        dto.setPhotoUrl(entity.getPhotoUrl());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public List<BurgerJointDto> toDtos(List<BurgerJoint> entities) {
        List<BurgerJointDto> dtos = new ArrayList<>();
        for (BurgerJoint entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
