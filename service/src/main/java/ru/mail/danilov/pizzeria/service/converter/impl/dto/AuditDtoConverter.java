package ru.mail.danilov.pizzeria.service.converter.impl.dto;

import org.springframework.stereotype.Component;
import ru.mail.danilov.pizzeria.dao.model.Audit;
import ru.mail.danilov.pizzeria.service.converter.ConverterDto;
import ru.mail.danilov.pizzeria.service.dto.AuditDto;

import java.util.ArrayList;
import java.util.List;
@Component("auditDtoConverter")
public class AuditDtoConverter implements ConverterDto<AuditDto, Audit> {

    @Override
    public AuditDto toDto(Audit entity) {
        AuditDto dto = new AuditDto();
        dto.setId(entity.getId());
        dto.setCreated(entity.getCreated());
        dto.setEventType(entity.getEventType());
        return dto;
    }

    @Override
    public List<AuditDto> toDtoList(List<Audit> list) {
        List<AuditDto> dtoList = new ArrayList<>();
        for (Audit audit : list) {
            AuditDto dto = new AuditDto();
            dto.setId(audit.getId());
            dto.setCreated(audit.getCreated());
            dto.setEventType(audit.getEventType());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
