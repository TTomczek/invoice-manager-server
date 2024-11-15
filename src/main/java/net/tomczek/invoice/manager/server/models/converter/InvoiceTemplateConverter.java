package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.InvoiceTemplateDTO;
import net.tomczek.invoice.manager.server.entities.InvoiceTemplateDAO;
import net.tomczek.invoice.manager.server.models.InvoiceTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceTemplateConverter {

    public InvoiceTemplateDTO toDTO(InvoiceTemplate invoiceTemplate) {
        InvoiceTemplateDTO invoiceTemplateDTO = new InvoiceTemplateDTO();
        invoiceTemplateDTO.setId(invoiceTemplate.getId());
        invoiceTemplateDTO.setName(invoiceTemplate.getName());
        invoiceTemplateDTO.setMarginTopFirstPage(invoiceTemplate.getMarginTopFirstPage());
        invoiceTemplateDTO.setMarginBottomFirstPage(invoiceTemplate.getMarginBottomFirstPage());
        invoiceTemplateDTO.setMarginTopOtherPages(invoiceTemplate.getMarginTopOtherPages());
        invoiceTemplateDTO.setMarginBottomOtherPages(invoiceTemplate.getMarginBottomOtherPages());

        return invoiceTemplateDTO;
    }

    public List<InvoiceTemplateDTO> toDTO(List<InvoiceTemplate> invoiceTemplates) {
        return invoiceTemplates.stream().map(this::toDTO).toList();
    }

    public InvoiceTemplate toEntityFromDTO(InvoiceTemplateDTO invoiceTemplateDTO) {
        InvoiceTemplate invoiceTemplate = new InvoiceTemplate();
        invoiceTemplate.setId(invoiceTemplateDTO.getId());
        invoiceTemplate.setName(invoiceTemplateDTO.getName());
        invoiceTemplate.setMarginTopFirstPage(invoiceTemplateDTO.getMarginTopFirstPage());
        invoiceTemplate.setMarginBottomFirstPage(invoiceTemplateDTO.getMarginBottomFirstPage());
        invoiceTemplate.setMarginTopOtherPages(invoiceTemplateDTO.getMarginTopOtherPages());
        invoiceTemplate.setMarginBottomOtherPages(invoiceTemplateDTO.getMarginBottomOtherPages());

        return invoiceTemplate;
    }

    public List<InvoiceTemplate> toEntityFromDTO(List<InvoiceTemplateDTO> invoiceTemplateDTOs) {
        return invoiceTemplateDTOs.stream().map(this::toEntityFromDTO).toList();
    }

    public InvoiceTemplate toEntityFromDAO(InvoiceTemplateDAO invoiceTemplateDAO) {
        InvoiceTemplate invoiceTemplate = new InvoiceTemplate();
        invoiceTemplate.setId(invoiceTemplateDAO.getId());
        invoiceTemplate.setName(invoiceTemplateDAO.getName());
        invoiceTemplate.setMarginTopFirstPage(invoiceTemplateDAO.getMarginTopFirstPage());
        invoiceTemplate.setMarginBottomFirstPage(invoiceTemplateDAO.getMarginBottomFirstPage());
        invoiceTemplate.setMarginTopOtherPages(invoiceTemplateDAO.getMarginTopOtherPages());
        invoiceTemplate.setMarginBottomOtherPages(invoiceTemplateDAO.getMarginBottomOtherPages());

        return invoiceTemplate;
    }

    public List<InvoiceTemplate> toEntityFromDAO(List<InvoiceTemplateDAO> invoiceTemplateDAOs) {
        return invoiceTemplateDAOs.stream().map(this::toEntityFromDAO).toList();
    }

    public InvoiceTemplateDAO toDAO(InvoiceTemplate invoiceTemplate) {
        InvoiceTemplateDAO invoiceTemplateDAO = new InvoiceTemplateDAO();
        invoiceTemplateDAO.setId(invoiceTemplate.getId());
        invoiceTemplateDAO.setName(invoiceTemplate.getName());
        invoiceTemplateDAO.setMarginTopFirstPage(invoiceTemplate.getMarginTopFirstPage());
        invoiceTemplateDAO.setMarginBottomFirstPage(invoiceTemplate.getMarginBottomFirstPage());
        invoiceTemplateDAO.setMarginTopOtherPages(invoiceTemplate.getMarginTopOtherPages());
        invoiceTemplateDAO.setMarginBottomOtherPages(invoiceTemplate.getMarginBottomOtherPages());

        return invoiceTemplateDAO;
    }

    public List<InvoiceTemplateDAO> toDAO(List<InvoiceTemplate> invoiceTemplates) {
        return invoiceTemplates.stream().map(this::toDAO).toList();
    }
}
