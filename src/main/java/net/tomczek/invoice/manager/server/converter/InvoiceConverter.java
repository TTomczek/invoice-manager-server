package net.tomczek.invoice.manager.server.converter;

import net.tomczek.invoice.manager.api.server.model.InvoiceDTO;
import net.tomczek.invoice.manager.server.entities.Invoice;
import net.tomczek.invoice.manager.server.entities.InvoicePosition;
import net.tomczek.invoice.manager.server.services.*;

import java.util.List;
import java.util.stream.Collectors;

public class InvoiceConverter {

    public static InvoiceDTO toDTO(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setDescription(invoice.getDescription());
        invoiceDTO.setViaMail(invoice.isPerMail());
        invoiceDTO.setPreText(invoice.getPreText());
        invoiceDTO.setPostText(invoice.getPostText());
        invoiceDTO.setServiceFrom(invoice.getServiceProvidedFrom());
        invoiceDTO.setServiceTo(invoice.getServiceProvidedTo());
        invoiceDTO.setOrderNumber(invoice.getOrderNumber());
        invoiceDTO.setCustomerNumber(invoice.getCustomer().getId());
        invoiceDTO.setPositions(invoice.getInvoicePosition().stream().map(InvoicePosition::getId).collect(Collectors.toList()));
        invoiceDTO.setReceiver(invoice.getReceiver().getId());
        invoiceDTO.setSalesTax(invoice.getSalexTax().getId());
        invoiceDTO.setInvoiceTemplate(invoice.getInvoiceTemplate().getId());
        invoiceDTO.setFile(invoice.getGeneratedInvoiceId());

        return invoiceDTO;
    }

    public static List<InvoiceDTO> toDTO(List<Invoice> invoices) {
        return invoices.stream().map(InvoiceConverter::toDTO).collect(Collectors.toList());
    }

    public static Invoice toEntity(InvoiceDTO invoiceDTO, IInvoiceTemplateService its, IContactPersonService cps, IBusinessPartnerService bps, IInvoicePositionService ips, ISalesTaxService sts) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceDTO.getId());
        invoice.setDescription(invoiceDTO.getDescription());
        invoice.setPerMail(invoiceDTO.getViaMail());
        invoice.setPreText(invoiceDTO.getPreText());
        invoice.setPostText(invoiceDTO.getPostText());
        invoice.setServiceProvidedFrom(invoiceDTO.getServiceFrom());
        invoice.setServiceProvidedTo(invoiceDTO.getServiceTo());
        invoice.setOrderNumber(invoiceDTO.getOrderNumber());
        invoice.setGeneratedInvoiceId(invoiceDTO.getFile());
        invoice.setInvoiceTemplate(its.getInvoiceTemplateById(invoiceDTO.getInvoiceTemplate()));
        invoice.setCustomer(bps.getBusinessPartnerById(invoiceDTO.getCustomerNumber()));
        invoice.setReceiver(cps.getContactPersonById(invoiceDTO.getReceiver()));
        invoice.setSalexTax(sts.getSalesTaxById(invoiceDTO.getSalesTax()));
        invoice.setInvoicePosition(ips.getAllInvoicePositionByIds(invoiceDTO.getPositions()));

        return invoice;
    }

    public static List<Invoice> toEntity(List<InvoiceDTO> invoiceDTOs, IInvoiceTemplateService its, IContactPersonService cps, IBusinessPartnerService bps, IInvoicePositionService ips, ISalesTaxService sts) {
        return invoiceDTOs.stream().map((inv) -> toEntity(inv, its, cps, bps, ips, sts)).collect(Collectors.toList());
    }
}
