package net.tomczek.invoice.manager.server.services.invoicegenerator;

import net.tomczek.invoice.manager.server.config.CompanyDetailsProperties;
import net.tomczek.invoice.manager.server.entities.Invoice;
import net.tomczek.invoice.manager.server.entities.InvoiceTemplate;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Profile("standard")
public class StandardInvoiceGenerator implements IInvoiceGenerator {

    private static final Logger logger = LoggerFactory.getLogger(StandardInvoiceGenerator.class);

    private final SpringTemplateEngine templateEngine;
    private final CompanyDetailsProperties companyDetailsProperties;

    @Autowired
    public StandardInvoiceGenerator(SpringTemplateEngine templateEngine, CompanyDetailsProperties companyDetailsProperties) {
        this.templateEngine = templateEngine;
        this.companyDetailsProperties = companyDetailsProperties;
    }

    @Override
    public byte[] generateInvoice(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        BigDecimal totalBeforetax = BigDecimal.ZERO;
        BigDecimal tax = BigDecimal.ZERO;
        if (invoice.getInvoicePositions() != null && !invoice.getInvoicePositions().isEmpty()) {
            totalBeforetax = invoice.getInvoicePositions().stream()
                .map(item -> item.getPricePerUnitInCents().multiply(BigDecimal.valueOf(item.getQuantity())).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);

            tax = invoice.getSalesTax().getRate().multiply(totalBeforetax).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        }

        Context context = new Context();
        context.setVariable("invoice", invoice);
        context.setVariable("sender", companyDetailsProperties);
        context.setVariable("totalBeforetax", totalBeforetax);
        context.setVariable("tax", tax);
        context.setVariable("total", totalBeforetax.add(tax));
        context.setVariable("marginCss", getMarginCssString(invoice.getInvoiceTemplate()));
        String html = templateEngine.process("invoice.html", context);

        Document document = Jsoup.parse(html);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        String xhtml = document.html();

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(xhtml);
        renderer.layout();

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            renderer.createPDF(os);
            return os.toByteArray();
        } catch (Exception e) {
            logger.error("Error while generating invoice", e);
            return null;
        }
    }

    private String getMarginCssString(InvoiceTemplate template) {
        return "@page:first {\n" +
            "    margin-top: " + template.getMarginTopFirstPage() + "mm;\n" +
            "    margin-bottom: " + template.getMarginBottomFirstPage() + "mm;\n" +
            "}\n" +
            "@page {\n" +
            "    margin-top: " + template.getMarginTopOtherPages() + "mm;\n" +
            "    margin-bottom: " + template.getMarginBottomOtherPages() + "mm;\n" +
            "}\n";
    }
}
