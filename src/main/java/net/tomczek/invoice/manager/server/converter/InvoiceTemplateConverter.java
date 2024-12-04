package net.tomczek.invoice.manager.server.converter;

import net.tomczek.invoice.manager.api.server.model.InvoiceTemplateDTO;
import net.tomczek.invoice.manager.server.entities.InvoiceTemplate;

import java.util.List;

public class InvoiceTemplateConverter {

    public static InvoiceTemplateDTO toDTO(InvoiceTemplate invoiceTemplate) {
        InvoiceTemplateDTO invoiceTemplateDTO = new InvoiceTemplateDTO();
        invoiceTemplateDTO.setId(invoiceTemplate.getId());
        invoiceTemplateDTO.setName(invoiceTemplate.getName());
        invoiceTemplateDTO.setMarginTopFirstPage(invoiceTemplate.getMarginTopFirstPage());
        invoiceTemplateDTO.setMarginBottomFirstPage(invoiceTemplate.getMarginBottomFirstPage());
        invoiceTemplateDTO.setMarginTopOtherPages(invoiceTemplate.getMarginTopOtherPages());
        invoiceTemplateDTO.setMarginBottomOtherPages(invoiceTemplate.getMarginBottomOtherPages());
        invoiceTemplateDTO.setFileId(invoiceTemplate.getBackgroundPdfId());

        return invoiceTemplateDTO;
    }

    public static List<InvoiceTemplateDTO> toDTO(List<InvoiceTemplate> invoiceTemplates) {
        return invoiceTemplates.stream().map(InvoiceTemplateConverter::toDTO).toList();
    }

    public static InvoiceTemplate toEntity(InvoiceTemplateDTO invoiceTemplateDTO) {
        InvoiceTemplate invoiceTemplate = new InvoiceTemplate();
        invoiceTemplate.setId(invoiceTemplateDTO.getId());
        invoiceTemplate.setName(invoiceTemplateDTO.getName());
        invoiceTemplate.setMarginTopFirstPage(invoiceTemplateDTO.getMarginTopFirstPage());
        invoiceTemplate.setMarginBottomFirstPage(invoiceTemplateDTO.getMarginBottomFirstPage());
        invoiceTemplate.setMarginTopOtherPages(invoiceTemplateDTO.getMarginTopOtherPages());
        invoiceTemplate.setMarginBottomOtherPages(invoiceTemplateDTO.getMarginBottomOtherPages());
        invoiceTemplate.setBackgroundPdfId(invoiceTemplateDTO.getFileId());

        return invoiceTemplate;
    }

    public static List<InvoiceTemplate> toEntity(List<InvoiceTemplateDTO> invoiceTemplateDTOs) {
        return invoiceTemplateDTOs.stream().map(InvoiceTemplateConverter::toEntity).toList();
    }
}
