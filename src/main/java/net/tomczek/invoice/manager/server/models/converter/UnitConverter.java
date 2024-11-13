package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.InvoicePositionDTO;
import net.tomczek.invoice.manager.server.entities.UnitET;

public class UnitConverter {

    public static UnitET ToET(InvoicePositionDTO.UnitEnum unitEnum) {
        return switch (unitEnum) {
            case HOUR -> UnitET.HOUR;
            case PIECE -> UnitET.PIECE;
            case PD -> UnitET.PD;
        };
    }

    public static InvoicePositionDTO.UnitEnum ToDTO(UnitET unitET) {
        return switch (unitET) {
            case HOUR -> InvoicePositionDTO.UnitEnum.HOUR;
            case PIECE -> InvoicePositionDTO.UnitEnum.PIECE;
            case PD -> InvoicePositionDTO.UnitEnum.PD;
        };
    }
}
