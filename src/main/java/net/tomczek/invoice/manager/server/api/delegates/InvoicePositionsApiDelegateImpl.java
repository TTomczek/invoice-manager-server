package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoicePositionsApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoicePositionDTO;
import net.tomczek.invoice.manager.api.server.model.InvoiceTemplateDTO;
import net.tomczek.invoice.manager.server.models.InvoicePosition;
import net.tomczek.invoice.manager.server.models.converter.InvoicePositionConverter;
import net.tomczek.invoice.manager.server.services.IInvoicePositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoicePositionsApiDelegateImpl implements InvoicePositionsApiDelegate {

    @Autowired
    public InvoicePositionsApiDelegateImpl(IInvoicePositionsService invoicePositionsService) {
        this.invoicePositionsService = invoicePositionsService;
    }

    private final IInvoicePositionsService invoicePositionsService;

    @Override
    public ResponseEntity<InvoicePositionDTO> createPosition(InvoicePositionDTO invoicePositionDTO) {
        InvoicePosition invoicePosition = InvoicePositionConverter.toEntityFromDTO(invoicePositionDTO);
        InvoicePosition createdInvoicePosition = invoicePositionsService.createInvoicePosition(invoicePosition);
        InvoicePositionDTO createdInvoicePositionDTO = InvoicePositionConverter.toDTO(createdInvoicePosition);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoicePositionDTO);
    }

    @Override
    public ResponseEntity<InvoicePositionDTO> deleteInvoicePositionById(Integer id) {
        InvoicePosition deletedInvoicePosition = invoicePositionsService.deleteInvoicePositionById(id);
        if (deletedInvoicePosition == null) {
            return ResponseEntity.notFound().build();
        } else {
            InvoicePositionDTO deletedInvoicePositionDTO = InvoicePositionConverter.toDTO(deletedInvoicePosition);
            return ResponseEntity.ok(deletedInvoicePositionDTO);
        }
    }

    @Override
    public ResponseEntity<List<InvoicePositionDTO>> getAllInvoicePositions() {
        List<InvoicePosition> invoicePositions = invoicePositionsService.getAllInvoicePositions();
        List<InvoicePositionDTO> invoicePositionDTOs = InvoicePositionConverter.toDTO(invoicePositions);
        return ResponseEntity.ok(invoicePositionDTOs);
    }

    @Override
    public ResponseEntity<InvoicePositionDTO> getInvoicePositionById(Integer id) {
        InvoicePosition invoicePosition = invoicePositionsService.getInvoicePositionById(id);
        if (invoicePosition == null) {
            return ResponseEntity.notFound().build();
        } else {
            InvoicePositionDTO invoicePositionDTO = InvoicePositionConverter.toDTO(invoicePosition);
            return ResponseEntity.ok(invoicePositionDTO);
        }
    }

    @Override
    public ResponseEntity<InvoicePositionDTO> updateInvoicePositionById(Integer id, InvoicePositionDTO invoicePositionDTO) {
        InvoicePosition invoicePosition = InvoicePositionConverter.toEntityFromDTO(invoicePositionDTO);
        InvoicePosition updatedInvoicePosition = invoicePositionsService.updateInvoicePositionById(id, invoicePosition);
        InvoicePositionDTO updatedInvoicePositionDTO = InvoicePositionConverter.toDTO(updatedInvoicePosition);
        return ResponseEntity.ok(updatedInvoicePositionDTO);
    }
}
