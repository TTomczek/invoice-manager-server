package net.tomczek.invoice.manager.server;

import net.tomczek.invoice.manager.server.entities.*;
import net.tomczek.invoice.manager.server.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class InitDataLoader implements CommandLineRunner {

    private final AddressRepository addressRepository;
    private final SalesTaxRepository salesTaxRepository;
    private final InvoiceTemplateRepository invoiceTemplateRepository;
    private final BusinessPartnersRepository businessPartnersRepository;
    private final ContactPersonsRepository contactPersonsRepository;
    private final InvoicesRepository invoicesRepository;
    private final InvoicePositionsRepository invoicePositionsRepository;

    @Autowired
    public InitDataLoader(AddressRepository addressRepository, SalesTaxRepository salesTaxRepository, InvoiceTemplateRepository invoiceTemplateRepository, BusinessPartnersRepository businessPartnersRepository, ContactPersonsRepository contactPersonsRepository, InvoicesRepository invoicesRepository, InvoicePositionsRepository invoicePositionsRepository) {
        this.addressRepository = addressRepository;
        this.salesTaxRepository = salesTaxRepository;
        this.invoiceTemplateRepository = invoiceTemplateRepository;
        this.businessPartnersRepository = businessPartnersRepository;
        this.contactPersonsRepository = contactPersonsRepository;
        this.invoicesRepository = invoicesRepository;
        this.invoicePositionsRepository = invoicePositionsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        this.invoicePositionsRepository.deleteAll();
        this.invoicesRepository.deleteAll();
        this.contactPersonsRepository.deleteAll();
        this.businessPartnersRepository.deleteAll();
        this.addressRepository.deleteAll();
        this.salesTaxRepository.deleteAll();
        this.invoiceTemplateRepository.deleteAll();
        Address address = this.addressRepository.save(new Address(null, "Musterstraße", "1", "12345", "Musterstadt", "Deutschland"));
        SalesTax salesTax = this.salesTaxRepository.save(new SalesTax(null, "Mehrwertsteuer", new BigDecimal("19.00")));
        InvoiceTemplate invoiceTemplate = this.invoiceTemplateRepository.save(new InvoiceTemplate(null, "Standard", 15f, 10f, 10f, 10f, null));
        BusinessPartner businessPartner = this.businessPartnersRepository.save(new BusinessPartner(null, "Musterfirma", "Musterstraße", address, new ArrayList<ContactPerson>(), new ArrayList<Invoice>()));
        ContactPerson contactPerson = this.contactPersonsRepository.save(new ContactPerson(null, "Max", "Mustermann", "max@mustermann.de", address, businessPartner, SalutationET.DIVERS));
        businessPartner.setContactPersons(List.of(contactPerson));
        this.businessPartnersRepository.save(businessPartner);
        Invoice invoice = this.invoicesRepository.save(new Invoice(null, "Testrechnung", false, "Vorlage", "Nachricht", LocalDate.now().minusYears(1), null, "12345", null, salesTax, new ArrayList<InvoicePosition>(), contactPerson, invoiceTemplate, businessPartner));
        InvoicePosition invoicePosition = this.invoicePositionsRepository.save(new InvoicePosition(null, "Testposition", 1f, UnitET.PIECE, new BigDecimal("100.00"), invoice));
        invoice.setInvoicePositions(List.of(invoicePosition));
        this.invoicesRepository.save(invoice);
    }
}
