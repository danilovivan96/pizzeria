package ru.mail.danilov.pizzeria.service.converter;

import java.util.List;

public interface Converter<D, E> {
    E toEntity(D dto);

    default List<E> toEntityList(List<D> list) {
        throw new UnsupportedOperationException();
    }
}
