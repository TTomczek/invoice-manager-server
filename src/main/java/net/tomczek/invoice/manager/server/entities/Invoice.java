package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.apache.commons.lang3.NotImplementedException;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Rechnung
 */
@Entity
public class Invoice extends BaseEntity<Integer> {

    private String description;

    private boolean perMail;

    private String preText;

    private String postText;

    private Date serviceProvidedFrom;

    private Date serviceProvidedTo;

    private String orderNumber;

    private File generatedInvoice;

    @ManyToOne
    private SalexTax salexTax;

    @OneToMany(mappedBy = "invoice")
    private List<InvoicePosition> invoicePositions;

    @ManyToOne
    private ContactPerson receiver;

    @OneToOne
    private InvoiceTemplate invoiceTemplate;

    @ManyToOne
    private BusinessPartner customer;

    public File printPdf() {
        throw new NotImplementedException();
    }
}
