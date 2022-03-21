package com.qminder.borger.app.mapper;

import com.qminder.borger.app.domain.Photo;
import com.qminder.borger.app.dto.PhotoDto;

import java.util.ArrayList;
import java.util.List;

public class PhotoMapper implements Mapper<PhotoDto, Photo> {
    @Override
    public PhotoDto toDto(Photo entity) {
        var dto = new PhotoDto();
        dto.setId(entity.getId());
        dto.setPhotoUrl(entity.getPhotoUrl());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setBurgerJointId(entity.getBurgerJoint().getId());
        return dto;
    }

    @Override
    public List<PhotoDto> toDtos(List<Photo> entities) {
        List<PhotoDto> dtos = new ArrayList<>();
        for (Photo entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
