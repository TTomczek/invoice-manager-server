package net.tomczek.invoice.manager.server;

import net.tomczek.invoice.manager.api.server.model.*;
import net.tomczek.invoice.manager.server.entities.*;
import net.tomczek.invoice.manager.server.models.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class TestObjects {

    public static Address address;
    public static AddressDTO addressDTO;
    public static BusinessPartner businessPartner;
    public static BusinessPartnerDTO businessPartnerDTO;
    public static ContactPerson contactPerson;
    public static ContactPersonDTO contactPersonDTO;
    public static FileWithContent fileWithContent;
    public static LocalFileStorageFile fileDAO;
    public static Invoice invoice;
    public static InvoiceDTO invoiceDTO;
    public static InvoicePosition invoicePosition;
    public static InvoicePositionDTO invoicePositionDTO;
    public static InvoiceTemplate invoiceTemplate;
    public static InvoiceTemplateDTO invoiceTemplateDTO;
    public static SalesTax salesTax;
    public static SalesTaxDTO salesTaxDTO;


    static {
        TestObjects.resetData();
    }

    public static void resetData() {
        address = new Address(43546, "Musterstraße 12", "12", "50667", "Köln", "Deutschland");
        addressDTO = new AddressDTO();
        addressDTO.setId(43546);
        addressDTO.setStreet("Musterstraße 12");
        addressDTO.setNumber("12");
        addressDTO.setZip("50667");
        addressDTO.setCity("Köln");
        addressDTO.setCountry("Deutschland");

        businessPartner = new BusinessPartner(89456, "Musterfirma GmbH", "Beschreibung der Musterfirma GmbH", address, new ArrayList<>(), new ArrayList<>());
        businessPartnerDTO = new BusinessPartnerDTO();
        businessPartnerDTO.setId(89456);
        businessPartnerDTO.setName("Musterfirma GmbH");
        businessPartnerDTO.setDescription("Beschreibung der Musterfirma GmbH");
        businessPartnerDTO.setAddress(addressDTO);
        businessPartnerDTO.setContactPersons(new ArrayList<>());
        businessPartnerDTO.setInvoices(new ArrayList<>());

        contactPerson = new ContactPerson(3456, "Max", "Mustermann", "max.mustermann@mail.de", address, businessPartner, SalutationET.HERR);
        contactPersonDTO = new ContactPersonDTO();
        contactPersonDTO.setId(3456);
        contactPersonDTO.setFirstName("Max");
        contactPersonDTO.setName("Mustermann");
        contactPersonDTO.setEmail("max.mustermann@mail.de");
        contactPersonDTO.setAddress(addressDTO);
        contactPersonDTO.setBusinessPartner(businessPartnerDTO.getId());

        businessPartner.setContactPersons(Arrays.asList(contactPerson));
        businessPartnerDTO.setContactPersons(Arrays.asList(contactPersonDTO).stream().map(ContactPersonDTO::getId).toList());

        fileWithContent = new FileWithContent(3456, "Rechnung.pdf", new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
        fileDAO = new LocalFileStorageFile(3456, "Rechnung.pdf");

        salesTax = new SalesTax(3456, "MwSt. 19%", new BigDecimal("19.00"));
        salesTaxDTO = new SalesTaxDTO();
        salesTaxDTO.setId(3456);
        salesTaxDTO.setName("MwSt. 19%");
        salesTaxDTO.setRate(new BigDecimal("19.00"));

        invoice = new Invoice(3456, "Rechnung 1", true, "PreText of invoice", "PostText of Invoice", LocalDate.now().minus(5, ChronoUnit.DAYS), LocalDate.now().minus(1, ChronoUnit.DAYS), "0987654", 123, salesTax, new ArrayList<>(), contactPerson, null, businessPartner, false);
        invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(3456);
        invoiceDTO.setDescription("Rechnung 1");
        invoiceDTO.setViaMail(true);
        invoiceDTO.setPreText("PreText of invoice");
        invoiceDTO.setPostText("PostText of Invoice");
        invoiceDTO.setServiceFrom(LocalDate.now().minus(5, ChronoUnit.DAYS));
        invoiceDTO.setServiceTo(LocalDate.now().minus(1, ChronoUnit.DAYS));
        invoiceDTO.setOrderNumber("0987654");
        invoiceDTO.setFile(123);
        invoiceDTO.setSalesTax(salesTaxDTO.getId());
        invoiceDTO.setPositions(new ArrayList<>());
        invoiceDTO.setReceiver(contactPersonDTO.getId());
        invoiceDTO.setInvoiceTemplate(null);
        invoiceDTO.setCustomerNumber(businessPartnerDTO.getId());

        businessPartner.setInvoices(Arrays.asList(invoice));
        businessPartnerDTO.setInvoices(Arrays.asList(3456));

        invoiceTemplate = new InvoiceTemplate(5476574, "Rechnungsvorlage 1", 4.0f, 2.0f, 2.0f, 2.0f, 45);
        invoiceTemplateDTO = new InvoiceTemplateDTO();
        invoiceTemplateDTO.setId(5476574);
        invoiceTemplateDTO.setName("Rechnungsvorlage 1");
        invoiceTemplateDTO.setMarginTopFirstPage(4.0f);
        invoiceTemplateDTO.setMarginBottomFirstPage(2.0f);
        invoiceTemplateDTO.setMarginTopOtherPages(2.0f);
        invoiceTemplateDTO.setMarginBottomOtherPages(2.0f);
        invoiceTemplateDTO.setFileId(45);

        invoice.setInvoiceTemplate(invoiceTemplate);
        invoiceDTO.setInvoiceTemplate(invoiceTemplateDTO.getId());

        invoicePosition = new InvoicePosition(789, "Position 1", 23, UnitET.PIECE, new BigDecimal(1000), invoice);
        invoicePosition = new InvoicePosition(789, "Position 1", 23, UnitET.PIECE, new BigDecimal(1000), invoice);
        invoicePositionDTO = new InvoicePositionDTO();
        invoicePositionDTO.setId(789);
        invoicePositionDTO.setDescription("Position 1");
        invoicePositionDTO.setQuantity(23.0f);
        invoicePositionDTO.setUnit(InvoicePositionDTO.UnitEnum.PIECE);
        invoicePositionDTO.setPricePerUnitInCents(new BigDecimal(1000));
        invoicePositionDTO.setInvoice(invoiceDTO.getId());

        invoice.setInvoicePositions(Arrays.asList(invoicePosition));
        invoiceDTO.setPositions(Arrays.asList(789));
    }
}
