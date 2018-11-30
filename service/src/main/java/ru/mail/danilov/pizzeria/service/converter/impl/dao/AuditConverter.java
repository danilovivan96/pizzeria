package ru.mail.danilov.pizzeria.service.converter.impl.dao;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.service.dto.AuditDto;
import ru.mail.danilov.pizzeria.dao.model.Audit;
import ru.mail.danilov.pizzeria.service.converter.Converter;

import java.util.ArrayList;
import java.util.List;
@Component("auditConverter")
public class AuditConverter implements Converter<AuditDto, Audit> {
    @Override
    public Audit toEntity(AuditDto dto) {
        Audit audit = new Audit();
        audit.setId(dto.getId());
        audit.setCreated(dto.getCreated());
        audit.setEventType(dto.getEventType());
        return audit;
    }

    @Override
    public List<Audit> toEntityList(List<AuditDto> list) {
        List<Audit> auditList = new ArrayList<>();
        for (AuditDto auditDto : list) {
            Audit audit = new Audit();
            audit.setId(auditDto.getId());
            audit.setCreated(auditDto.getCreated());
            audit.setEventType(auditDto.getEventType());
            auditList.add(audit);
        }
        return auditList;
    }
}
