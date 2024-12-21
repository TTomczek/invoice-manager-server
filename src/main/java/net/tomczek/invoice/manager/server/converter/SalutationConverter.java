package net.tomczek.invoice.manager.server.converter;

import net.tomczek.invoice.manager.api.server.model.ContactPersonDTO;
import net.tomczek.invoice.manager.server.entities.SalutationET;

public class SalutationConverter {

    public static SalutationET toET(ContactPersonDTO.SalutationEnum salutation) {
        if (salutation == null) {
            return SalutationET.DIVERS;
        }
        return switch (salutation) {
            case FRAU -> SalutationET.FRAU;
            case HERR -> SalutationET.HERR;
            case DIVERS -> SalutationET.DIVERS;
        };
    }

    public static ContactPersonDTO.SalutationEnum toDTO(SalutationET salutation) {
        if (salutation == null) {
            return ContactPersonDTO.SalutationEnum.DIVERS;
        }
        return switch (salutation) {
            case FRAU -> ContactPersonDTO.SalutationEnum.FRAU;
            case HERR -> ContactPersonDTO.SalutationEnum.HERR;
            case DIVERS -> ContactPersonDTO.SalutationEnum.DIVERS;
        };
    }
}
