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
        SalesTax salesTax = this.salesTaxRepository.save(new SalesTax(null, "Mehrwertsteuer 19%", new BigDecimal("19.00")));
        SalesTax salesTaxReduced = this.salesTaxRepository.save(new SalesTax(null, "Mehrwertsteuer 7%", new BigDecimal("7.00")));
        InvoiceTemplate invoiceTemplate = this.invoiceTemplateRepository.save(new InvoiceTemplate(null, "Standard", 15f, 10f, 10f, 10f, null));

        // Business Partner Musterfirma with Contact Person Max Mustermann and two Invoices
        Address address = this.addressRepository.save(new Address(null, "Musterstraße", "1", "12345", "Musterstadt", "Deutschland"));
        BusinessPartner businessPartner = this.businessPartnersRepository.save(new BusinessPartner(null, "Musterfirma", "Musterstraße", address, new ArrayList<ContactPerson>(), new ArrayList<Invoice>()));
        ContactPerson contactPerson = this.contactPersonsRepository.save(new ContactPerson(null, "Mustermann", "Max", "max@mustermann.de", address, businessPartner, SalutationET.DIVERS));
        businessPartner.setContactPersons(List.of(contactPerson));
        this.businessPartnersRepository.save(businessPartner);

        Invoice invoice = this.invoicesRepository.save(new Invoice(null, "Testrechnung", false, "Vorlage", "Nachricht", LocalDate.now().minusYears(1), null, "12345", null, salesTax, new ArrayList<InvoicePosition>(), contactPerson, invoiceTemplate, businessPartner, false));
        InvoicePosition invoicePosition = this.invoicePositionsRepository.save(new InvoicePosition(null, "Testposition", 5f, UnitET.PIECE, new BigDecimal("200.00"), invoice));
        InvoicePosition invoicePosition9 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Zusatzposition", 2f, UnitET.HOUR, new BigDecimal("75.00"), invoice));
        invoice.setInvoicePositions(List.of(invoicePosition, invoicePosition9));
        this.invoicesRepository.save(invoice);
        Invoice invoice2 = this.invoicesRepository.save(new Invoice(null, "Testrechnung2", true, "Vorlage", "Nachricht", LocalDate.now().minusMonths(1), LocalDate.now(), "12345", null, salesTax, new ArrayList<InvoicePosition>(), contactPerson, invoiceTemplate, businessPartner, true));
        InvoicePosition invoicePosition2 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Testposition2", 1f, UnitET.PD, new BigDecimal("100.00"), invoice2));
        invoice2.setInvoicePositions(List.of(invoicePosition2));
        this.invoicesRepository.save(invoice2);

        Invoice invoice6 = this.invoicesRepository.save(new Invoice(null, "Testrechnung3", false, "Vorlage", "Nachricht", LocalDate.now().minusDays(20), null, "12345", null, salesTaxReduced, new ArrayList<InvoicePosition>(), contactPerson, invoiceTemplate, businessPartner, false));
        InvoicePosition invoicePosition10 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Holzbalken", 10f, UnitET.PIECE, new BigDecimal("50.00"), invoice6));
        InvoicePosition invoicePosition11 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Arbeitszeit", 4f, UnitET.HOUR, new BigDecimal("80.00"), invoice6));
        InvoicePosition invoicePosition12 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Mitarbeiter", 2f, UnitET.PD, new BigDecimal("1.00"), invoice6));
        invoice6.setInvoicePositions(List.of(invoicePosition10, invoicePosition11, invoicePosition12));
        this.invoicesRepository.save(invoice6);

        // Business Partner Beispiel GmbH with Contact Person Erika Beispiel and three Invoices
        Address address2 = this.addressRepository.save(new Address(null, "Beispielweg", "2", "54321", "Beispielstadt", "Deutschland"));
        BusinessPartner businessPartner2 = this.businessPartnersRepository.save(new BusinessPartner(null, "Beispiel GmbH", "Beispielweg", address2, new ArrayList<ContactPerson>(), new ArrayList<Invoice>()));
        ContactPerson contactPerson2 = this.contactPersonsRepository.save(new ContactPerson(null, "Beispiel", "Erika", "erika.beispiel@beispielgmb.de", address2, businessPartner2, SalutationET.FRAU));
        ContactPerson contactPerson3 = this.contactPersonsRepository.save(new ContactPerson(null, "Beispielmann", "Erik", "erik.beispielmann@beispielgmbh.de", address2, businessPartner2, SalutationET.HERR));
        businessPartner2.setContactPersons(List.of(contactPerson2, contactPerson3));
        this.businessPartnersRepository.save(businessPartner2);

        Invoice invoice3 = this.invoicesRepository.save(new Invoice(null, "Beispielrechnung", false, "Vorlage", "Nachricht", LocalDate.now().minusMonths(2), null, "54321", null, salesTaxReduced, new ArrayList<InvoicePosition>(), contactPerson2, invoiceTemplate, businessPartner2, false));
        InvoicePosition invoicePosition3 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Geleistete Stunden", 3f, UnitET.HOUR, new BigDecimal("150.00"), invoice3));
        InvoicePosition invoicePosition6 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Materialkosten", 1f, UnitET.PIECE, new BigDecimal("500.00"), invoice3));
        invoice3.setInvoicePositions(List.of(invoicePosition3, invoicePosition6));
        this.invoicesRepository.save(invoice3);
        Invoice invoice4 = this.invoicesRepository.save(new Invoice(null, "Beispielrechnung 2", true, "Vorlage", "Nachricht", LocalDate.now().minusMonths(1), LocalDate.now().minusDays(15), "54321", null, salesTax, new ArrayList<InvoicePosition>(), contactPerson3, invoiceTemplate, businessPartner2, true));
        InvoicePosition invoicePosition4 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Geleistete Personentage", 2f, UnitET.PD, new BigDecimal("300.00"), invoice4));
        InvoicePosition invoicePosition7 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Reisekosten", 1f, UnitET.PIECE, new BigDecimal("150.00"), invoice4));
        InvoicePosition invoicePosition8 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Sonstige Kosten", 1f, UnitET.PIECE, new BigDecimal("100.00"), invoice4));
        invoice4.setInvoicePositions(List.of(invoicePosition4, invoicePosition7, invoicePosition8));
        this.invoicesRepository.save(invoice4);
        Invoice invoice5 = this.invoicesRepository.save(new Invoice(null, "Beispielrechnung 3", false, "Vorlage", "Nachricht", LocalDate.now().minusDays(10), null, "54321", null, salesTaxReduced, new ArrayList<InvoicePosition>(), contactPerson3, invoiceTemplate, businessPartner2, false));
        InvoicePosition invoicePosition5 = this.invoicePositionsRepository.save(new InvoicePosition(null, "Stückzahlen", 4f, UnitET.PIECE, new BigDecimal("250.00"), invoice5));
        invoice5.setInvoicePositions(List.of(invoicePosition5));
        this.invoicesRepository.save(invoice5);

        this.invoicesRepository.flush();
    }
}
