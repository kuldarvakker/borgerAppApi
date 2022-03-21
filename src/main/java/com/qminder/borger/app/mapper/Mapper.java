package com.qminder.borger.app.mapper;

import java.util.List;

public interface Mapper<T, E> {

    T toDto(E entity);

    List<T> toDtos(List<E> entities);

}
