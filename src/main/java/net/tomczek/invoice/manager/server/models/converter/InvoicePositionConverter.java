package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.InvoicePositionDTO;
import net.tomczek.invoice.manager.server.entities.InvoicePositionDAO;
import net.tomczek.invoice.manager.server.models.InvoicePosition;

import java.util.List;

public class InvoicePositionConverter {

    public static InvoicePositionDTO toDTO(InvoicePosition invoicePosition) {
        InvoicePositionDTO invoicePositionDTO = new InvoicePositionDTO();
        invoicePositionDTO.setId(invoicePosition.getId());
        invoicePositionDTO.setDescription(invoicePosition.getDescription());
        invoicePositionDTO.setQuantity(invoicePosition.getQuantity());
        invoicePositionDTO.setUnit(UnitConverter.ToDTO(invoicePosition.getUnit()));
        invoicePositionDTO.setPricePerUnitInCents(invoicePosition.getPricePerUnitInCents());
        invoicePositionDTO.setInvoice(invoicePosition.getInvoice().getId());
        return invoicePositionDTO;
    }

    public static List<InvoicePositionDTO> toDTO(List<InvoicePosition> invoicePositions) {
        return invoicePositions.stream().map(InvoicePositionConverter::toDTO).toList();
    }

    public static InvoicePosition toEntityFromDTO(InvoicePositionDTO invoicePositionDTO) {
        InvoicePosition invoicePosition = new InvoicePosition();
        invoicePosition.setId(invoicePositionDTO.getId());
        invoicePosition.setDescription(invoicePositionDTO.getDescription());
        invoicePosition.setQuantity(invoicePositionDTO.getQuantity());
        invoicePosition.setUnit(UnitConverter.ToET(invoicePositionDTO.getUnit()));
        invoicePosition.setPricePerUnitInCents(invoicePositionDTO.getPricePerUnitInCents());
        return invoicePosition;
    }

    public static List<InvoicePosition> toEntityFromDTO(List<InvoicePositionDTO> invoicePositionDTOs) {
        return invoicePositionDTOs.stream().map(InvoicePositionConverter::toEntityFromDTO).toList();
    }

    public static InvoicePosition toEntityFromDAO(InvoicePositionDAO invoicePositionDAO) {
        InvoicePosition invoicePosition = new InvoicePosition();
        invoicePosition.setId(invoicePositionDAO.getId());
        invoicePosition.setDescription(invoicePositionDAO.getDescription());
        invoicePosition.setQuantity(invoicePositionDAO.getQuantity());
        invoicePosition.setUnit(invoicePositionDAO.getUnitEt());
        invoicePosition.setPricePerUnitInCents(invoicePositionDAO.getPricePerUnitInCents());
        return invoicePosition;
    }

    public static List<InvoicePosition> toEntityFromDAO(List<InvoicePositionDAO> invoicePositionDAOs) {
        return invoicePositionDAOs.stream().map(InvoicePositionConverter::toEntityFromDAO).toList();
    }

    public static InvoicePositionDAO toDAO(InvoicePosition invoicePosition) {
        InvoicePositionDAO invoicePositionDAO = new InvoicePositionDAO();
        invoicePositionDAO.setId(invoicePosition.getId());
        invoicePositionDAO.setDescription(invoicePosition.getDescription());
        invoicePositionDAO.setQuantity(invoicePosition.getQuantity());
        invoicePositionDAO.setUnitEt(invoicePosition.getUnit());
        invoicePositionDAO.setPricePerUnitInCents(invoicePosition.getPricePerUnitInCents());
        return invoicePositionDAO;
    }

    public static List<InvoicePositionDAO> toDAO(List<InvoicePosition> invoicePositions) {
        return invoicePositions.stream().map(InvoicePositionConverter::toDAO).toList();
    }
}
