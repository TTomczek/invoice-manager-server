package net.tomczek.invoice.manager.server.converter;

import net.tomczek.invoice.manager.api.server.model.InvoicePositionDTO;
import net.tomczek.invoice.manager.server.entities.InvoicePosition;
import net.tomczek.invoice.manager.server.services.IInvoiceService;

import java.util.List;

public class InvoicePositionConverter {

    public static InvoicePositionDTO toDTO(InvoicePosition invoicePosition) {
        if (invoicePosition == null) {
            return null;
        }
        InvoicePositionDTO invoicePositionDTO = new InvoicePositionDTO();
        invoicePositionDTO.setId(invoicePosition.getId());
        invoicePositionDTO.setDescription(invoicePosition.getDescription());
        invoicePositionDTO.setQuantity(invoicePosition.getQuantity());
        invoicePositionDTO.setUnit(UnitConverter.ToDTO(invoicePosition.getUnitEt()));
        invoicePositionDTO.setPricePerUnitInCents(invoicePosition.getPricePerUnitInCents());
        invoicePositionDTO.setInvoice(invoicePosition.getInvoice().getId());
        return invoicePositionDTO;
    }

    public static List<InvoicePositionDTO> toDTO(List<InvoicePosition> invoicePositions) {
        if (invoicePositions == null) {
            return null;
        }
        return invoicePositions.stream().map(InvoicePositionConverter::toDTO).toList();
    }

    public static InvoicePosition toEntity(InvoicePositionDTO invoicePositionDTO, IInvoiceService is) {
        if (invoicePositionDTO == null) {
            return null;
        }
        InvoicePosition invoicePosition = new InvoicePosition();
        invoicePosition.setId(invoicePositionDTO.getId());
        invoicePosition.setDescription(invoicePositionDTO.getDescription());
        invoicePosition.setQuantity(invoicePositionDTO.getQuantity());
        invoicePosition.setUnitEt(UnitConverter.ToET(invoicePositionDTO.getUnit()));
        invoicePosition.setPricePerUnitInCents(invoicePositionDTO.getPricePerUnitInCents());
        invoicePosition.setInvoice(is.getInvoiceById(invoicePositionDTO.getInvoice()));
        return invoicePosition;
    }

    public static List<InvoicePosition> toEntity(List<InvoicePositionDTO> invoicePositionDTOs, IInvoiceService is) {
        if (invoicePositionDTOs == null) {
            return null;
        }
        return invoicePositionDTOs.stream().map((ip) -> toEntity(ip, is)).toList();
    }
}
