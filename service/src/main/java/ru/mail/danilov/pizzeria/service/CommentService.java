package ru.mail.danilov.pizzeria.service;

import ru.mail.danilov.pizzeria.service.dto.CommentDto;

public interface CommentService {
    CommentDto save(CommentDto comment, Long news);

    void delete(Long id);
}
