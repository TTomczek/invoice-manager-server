package net.tomczek.invoice.manager.server.services.invoicegenerator;

import net.tomczek.invoice.manager.server.config.CompanyDetailsProperties;
import net.tomczek.invoice.manager.server.entities.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.StaticApplicationContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class StandardInvoiceGeneratorTest {

    private static final String storageDir = "target/test/filestorage/standardinvoicegenerator/";

    CompanyDetailsProperties companyDetailsProperties = new CompanyDetailsProperties()
            .setName("Test company")
            .setStreet("Test street")
            .setHouseNumber("1")
            .setZipCode("12345")
            .setCity("Test city")
            .setCountry("Test country")
            .setPhoneNumber("123456789")
            .setEmail("my@company.com");

    StandardInvoiceGenerator standardInvoiceGenerator;

    Invoice testInvoice = null;

    @BeforeEach
    void setUp() {
        File dir = new File(storageDir);
        if (dir.exists() ) {
            if (dir.isDirectory()) {
                for (File file : dir.listFiles()) {
                    file.delete();
                }
            }
            dir.delete();
        }

        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(new StaticApplicationContext());
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".xml");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        standardInvoiceGenerator = new StandardInvoiceGenerator(templateEngine, companyDetailsProperties);

        Address cpAddress = new Address();
        cpAddress.setId(2);
        cpAddress.setCity("Test city");
        cpAddress.setCountry("Test country");
        cpAddress.setStreet("Test street");
        cpAddress.setZipCode("12345");
        cpAddress.setHouseNumber("1");

        ContactPerson contactPerson = new ContactPerson();
        contactPerson.setId(1);
        contactPerson.setFirstName("Test first name");
        contactPerson.setName("Test name");
        contactPerson.setEmail("contact@persone.de");
        contactPerson.setSalutation(SalutationET.FRAU);
        contactPerson.setAddress(cpAddress);

        Address address = new Address();
        address.setId(1);
        address.setCity("Test city");
        address.setCountry("Test country");
        address.setStreet("Test street");
        address.setZipCode("12345");
        address.setHouseNumber("1");

        BusinessPartner businessPartner = new BusinessPartner();
        contactPerson.setBusinessPartner(businessPartner);
        businessPartner.setId(1);
        businessPartner.setName("Test business partner");
        businessPartner.setDescription("Test business partner description");
        businessPartner.setAddress(address);
        businessPartner.setContactPersons(List.of(contactPerson));

        SalesTax salesTax = new SalesTax();
        salesTax.setId(1);
        salesTax.setRate(new BigDecimal(19));
        salesTax.setName("Test sales tax");

        InvoiceTemplate invoiceTemplate = new InvoiceTemplate();
        invoiceTemplate.setId(1);
        invoiceTemplate.setName("Test invoice template");
        invoiceTemplate.setMarginTopFirstPage(10);
        invoiceTemplate.setMarginTopOtherPages(10);
        invoiceTemplate.setMarginBottomFirstPage(10);
        invoiceTemplate.setMarginBottomOtherPages(10);
        invoiceTemplate.setBackgroundPdfId(1);

        InvoicePosition invoicePosition1 = new InvoicePosition();
        invoicePosition1.setId(1);
        invoicePosition1.setQuantity(1);
        invoicePosition1.setUnitEt(UnitET.PIECE);
        invoicePosition1.setPricePerUnitInCents(new BigDecimal(100));
        invoicePosition1.setDescription("Test invoice position 1");

        InvoicePosition invoicePosition2 = new InvoicePosition();
        invoicePosition2.setId(2);
        invoicePosition2.setQuantity(2);
        invoicePosition2.setUnitEt(UnitET.PIECE);
        invoicePosition2.setPricePerUnitInCents(new BigDecimal(200));
        invoicePosition2.setDescription("Test invoice position 2");

        InvoicePosition invoicePosition3 = new InvoicePosition();
        invoicePosition3.setId(3);
        invoicePosition3.setQuantity(3);
        invoicePosition3.setUnitEt(UnitET.PD);
        invoicePosition3.setPricePerUnitInCents(new BigDecimal(300));
        invoicePosition3.setDescription("Test invoice position 3");

        InvoicePosition invoicePosition4 = new InvoicePosition();
        invoicePosition4.setId(4);
        invoicePosition4.setQuantity(100);
        invoicePosition4.setUnitEt(UnitET.HOUR);
        invoicePosition4.setPricePerUnitInCents(new BigDecimal(15000));
        invoicePosition4.setDescription("Test invoice position 4");

        InvoicePosition invoicePosition5 = new InvoicePosition();
        invoicePosition5.setId(5);
        invoicePosition5.setQuantity(1000);
        invoicePosition5.setUnitEt(UnitET.PD);
        invoicePosition5.setPricePerUnitInCents(new BigDecimal(50));
        invoicePosition5.setDescription("Test invoice position 5");

        InvoicePosition invoicePosition6 = new InvoicePosition();
        invoicePosition6.setId(6);
        invoicePosition6.setQuantity(10);
        invoicePosition6.setUnitEt(UnitET.PIECE);
        invoicePosition6.setPricePerUnitInCents(new BigDecimal(5000));
        invoicePosition6.setDescription("Test invoice position 6");

        InvoicePosition invoicePosition7 = new InvoicePosition();
        invoicePosition7.setId(7);
        invoicePosition7.setQuantity(100);
        invoicePosition7.setUnitEt(UnitET.HOUR);
        invoicePosition7.setPricePerUnitInCents(new BigDecimal(15000));
        invoicePosition7.setDescription("Test invoice position 7");

        InvoicePosition invoicePosition8 = new InvoicePosition();
        invoicePosition8.setId(8);
        invoicePosition8.setQuantity(100);
        invoicePosition8.setUnitEt(UnitET.HOUR);
        invoicePosition8.setPricePerUnitInCents(new BigDecimal(15000));
        invoicePosition8.setDescription("Test invoice position 8");

        InvoicePosition invoicePosition9 = new InvoicePosition();
        invoicePosition9.setId(9);
        invoicePosition9.setQuantity(100);
        invoicePosition9.setUnitEt(UnitET.HOUR);
        invoicePosition9.setPricePerUnitInCents(new BigDecimal(15000));
        invoicePosition9.setDescription("Test invoice position 9");

        Invoice invoice = new Invoice();

        invoice.setId(1);
        invoice.setDescription("Test invoice");
        invoice.setOrderNumber("123");
        invoice.setPerMail(true);
        invoice.setPreText("PRE Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        invoice.setPostText("POST Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        invoice.setServiceProvidedFrom(LocalDate.parse("2021-01-01"));
        invoice.setServiceProvidedTo(LocalDate.parse("2021-01-31"));
        invoice.setCustomer(businessPartner);
        invoice.setReceiver(contactPerson);
        invoicePosition1.setInvoice(invoice);
        invoicePosition2.setInvoice(invoice);
        invoicePosition3.setInvoice(invoice);
        invoicePosition4.setInvoice(invoice);
        invoicePosition5.setInvoice(invoice);
        invoicePosition6.setInvoice(invoice);
        invoicePosition7.setInvoice(invoice);
        invoicePosition8.setInvoice(invoice);
        invoicePosition9.setInvoice(invoice);
        invoice.setInvoicePositions(Arrays.asList(invoicePosition1, invoicePosition2, invoicePosition3, invoicePosition4, invoicePosition5, invoicePosition6, invoicePosition7, invoicePosition8, invoicePosition9));
        invoice.setInvoiceTemplate(invoiceTemplate);
        invoice.setSalesTax(salesTax);
        testInvoice = invoice;
    }

    @Test
    void generateInvoiceWith2PagesAndContactPerson() throws Exception {
        byte[] resultingByteArray = null;
        try {
            resultingByteArray = standardInvoiceGenerator.generateInvoice(testInvoice);
            if (resultingByteArray == null) {
                fail("Invoice generation failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception thrown during invoice generation");
        }

        System.out.println("Generated file with size: " + resultingByteArray.length + " bytes");

        File expectedFile = new File(storageDir + "generateInvoiceWith2PagesAndContactPerson.pdf");
        expectedFile.getParentFile().mkdirs();
        expectedFile.createNewFile();
        Files.write(expectedFile.toPath(), resultingByteArray);

        assertThat(resultingByteArray.length).isGreaterThan(0);
        assertThat(expectedFile.exists()).isTrue();

    }
}
