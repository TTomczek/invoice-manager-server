package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.InvoicePositionDTO;
import net.tomczek.invoice.manager.server.entities.InvoicePositionDAO;
import net.tomczek.invoice.manager.server.models.InvoicePosition;

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

    public static InvoicePosition toEntityFromDTO(InvoicePositionDTO invoicePositionDTO) {
        InvoicePosition invoicePosition = new InvoicePosition();
        invoicePosition.setId(invoicePositionDTO.getId());
        invoicePosition.setDescription(invoicePositionDTO.getDescription());
        invoicePosition.setQuantity(invoicePositionDTO.getQuantity());
        invoicePosition.setUnit(UnitConverter.ToET(invoicePositionDTO.getUnit()));
        invoicePosition.setPricePerUnitInCents(invoicePositionDTO.getPricePerUnitInCents());
        return invoicePosition;
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

    public static InvoicePositionDAO toDAO(InvoicePosition invoicePosition) {
        InvoicePositionDAO invoicePositionDAO = new InvoicePositionDAO();
        invoicePositionDAO.setId(invoicePosition.getId());
        invoicePositionDAO.setDescription(invoicePosition.getDescription());
        invoicePositionDAO.setQuantity(invoicePosition.getQuantity());
        invoicePositionDAO.setUnitEt(invoicePosition.getUnit());
        invoicePositionDAO.setPricePerUnitInCents(invoicePosition.getPricePerUnitInCents());
        return invoicePositionDAO;
    }
}
