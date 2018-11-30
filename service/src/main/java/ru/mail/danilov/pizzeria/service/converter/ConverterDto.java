package ru.mail.danilov.pizzeria.service.converter;

import java.util.List;

public interface ConverterDto<D, E> {
    D toDto(E entity);

    default List<D> toDtoList(List<E> list) {
        throw new UnsupportedOperationException();
    }
}
