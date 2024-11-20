package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.*;
import net.tomczek.invoice.manager.server.entities.*;
import net.tomczek.invoice.manager.server.models.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.core.io.ByteArrayResource;

import java.util.ArrayList;
import java.util.Arrays;

public class ConverterObjects {

    public static Address address;
    public static AddressDAO addressDAO;
    public static AddressDTO addressDTO;
    public static BusinessPartner businessPartner;
    public static BusinessPartnerDAO businessPartnerDAO;
    public static BusinessPartnerDTO businessPartnerDTO;
    public static ContactPerson contactPerson;
    public static ContactPersonDAO contactPersonDAO;
    public static ContactPersonDTO contactPersonDTO;
    public static FileWithContent fileWithContent;
    public static LocalFileStorageFileDAO fileDAO;
    public static FileDTO fileDTO;
    public static Invoice invoice;
    public static InvoiceDAO invoiceDAO;
    public static InvoiceDTO invoiceDTO;
    public static InvoicePosition invoicePosition;
    public static InvoicePositionDAO invoicePositionDAO;
    public static InvoicePositionDTO invoicePositionDTO;
    public static InvoiceTemplate invoiceTemplate;
    public static InvoiceTemplateDAO invoiceTemplateDAO;
    public static InvoiceTemplateDTO invoiceTemplateDTO;
    public static SalesTax salesTax;
    public static SalesTaxDAO salesTaxDAO;
    public static SalesTaxDTO salesTaxDTO;


    {
        address = new Address(43546, "Musterstraße 12", "12", "50667", "Köln", "Deutschland");
        addressDAO = new AddressDAO(43546, "Musterstraße 12", "12", "50667", "Köln", "Deutschland");
        addressDTO = new AddressDTO();
        addressDTO.setId(43546);
        addressDTO.setStreet("Musterstraße 12");
        addressDTO.setNumber("12");
        addressDTO.setZip("50667");
        addressDTO.setCity("Köln");
        addressDTO.setCountry("Deutschland");

        businessPartner = new BusinessPartner(89456, "Musterfirma GmbH", "Beschreibung der Musterfirma GmbH", address, new ArrayList<>(), new ArrayList<>());
        businessPartnerDAO = new BusinessPartnerDAO(89456, "Musterfirma GmbH", "Beschreibung der Musterfirma GmbH", addressDAO, new ArrayList<>(), new ArrayList<>());
        businessPartnerDTO = new BusinessPartnerDTO();
        businessPartnerDTO.setId(89456);
        businessPartnerDTO.setName("Musterfirma GmbH");
        businessPartnerDTO.setDescription("Beschreibung der Musterfirma GmbH");
        businessPartnerDTO.setAddress(addressDTO);
        businessPartnerDTO.setContactPersons(new ArrayList<>());
        businessPartnerDTO.setInvoices(new ArrayList<>());

        contactPerson = new ContactPerson(3456, "Max", "Mustermann", "max.mustermann@mail.de", address, businessPartner, SalutationET.HERR);
        contactPersonDAO = new ContactPersonDAO(3456, "Max", "Mustermann", "max.mustermann@mail.de", addressDAO, businessPartnerDAO, SalutationET.HERR);
        contactPersonDTO = new ContactPersonDTO();
        contactPersonDTO.setId(3456);
        contactPersonDTO.setFirstName("Max");
        contactPersonDTO.setName("Mustermann");
        contactPersonDTO.setEmail("max.mustermann@mail.de");
        contactPersonDTO.setAddress(addressDTO);
        contactPersonDTO.setBusinessPartner(businessPartnerDTO.getId());

        businessPartner.setContactPersons(Arrays.asList(contactPerson));
        businessPartnerDAO.setContactPersonDAOS(Arrays.asList(contactPersonDAO));
        businessPartnerDTO.setContactPersons(Arrays.asList(contactPersonDTO).stream().map(ContactPersonDTO::getId).toList());

        fileWithContent = new FileWithContent(3456, "Rechnung.pdf", new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0});
        fileDAO = new LocalFileStorageFileDAO(3456, "Rechnung.pdf");
        fileDTO = new FileDTO();
        fileDTO.setId(3456);
        fileDTO.setFileName("Rechnung.pdf");
        fileDTO.setData(new ByteArrayResource(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}));

        invoice = new Invoice(3456, "Rechnung 1", true, businessPartner, fileWithContent, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());


    }
}
