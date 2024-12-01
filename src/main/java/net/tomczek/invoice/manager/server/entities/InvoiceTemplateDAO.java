package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "invoice_templates")
@Entity
public class InvoiceTemplateDAO extends BaseEntity<Integer> {

    public InvoiceTemplateDAO(Integer id, String name, float marginTopFirstPage, float marginBottomFirstPage, float marginTopOtherPages, float marginBottomOtherPages, Integer backgroundPdfId) {
        super(id);
        this.name = name;
        this.marginTopFirstPage = marginTopFirstPage;
        this.marginBottomFirstPage = marginBottomFirstPage;
        this.marginTopOtherPages = marginTopOtherPages;
        this.marginBottomOtherPages = marginBottomOtherPages;
        this.backgroundPdfId = backgroundPdfId;
    }

    public InvoiceTemplateDAO() {
    }

    private String name;

    private float marginTopFirstPage;
    private float marginBottomFirstPage;
    private float marginTopOtherPages;
    private float marginBottomOtherPages;

    private Integer backgroundPdfId;

    public String getName() {
        return name;
    }

    public InvoiceTemplateDAO setName(String name) {
        this.name = name;
        return this;
    }

    public float getMarginTopFirstPage() {
        return marginTopFirstPage;
    }

    public InvoiceTemplateDAO setMarginTopFirstPage(float marginTopFirstPage) {
        this.marginTopFirstPage = marginTopFirstPage;
        return this;
    }

    public float getMarginBottomFirstPage() {
        return marginBottomFirstPage;
    }

    public InvoiceTemplateDAO setMarginBottomFirstPage(float marginBottomFirstPage) {
        this.marginBottomFirstPage = marginBottomFirstPage;
        return this;
    }

    public float getMarginTopOtherPages() {
        return marginTopOtherPages;
    }

    public InvoiceTemplateDAO setMarginTopOtherPages(float marginTopFollowingPages) {
        this.marginTopOtherPages = marginTopFollowingPages;
        return this;
    }

    public float getMarginBottomOtherPages() {
        return marginBottomOtherPages;
    }

    public InvoiceTemplateDAO setMarginBottomOtherPages(float marginBottomFollowingPages) {
        this.marginBottomOtherPages = marginBottomFollowingPages;
        return this;
    }

    public Integer getBackgroundPdfId() {
        return backgroundPdfId;
    }

    public InvoiceTemplateDAO setBackgroundPdfId(Integer backgroundPdfId) {
        this.backgroundPdfId = backgroundPdfId;
        return this;
    }

    @Override
    public String toString() {
        return "InvoiceTemplateDAO{" +
            "name='" + name + '\'' +
            ", marginTopFirstPage=" + marginTopFirstPage +
            ", marginBottomFirstPage=" + marginBottomFirstPage +
            ", marginTopOtherPages=" + marginTopOtherPages +
            ", marginBottomOtherPages=" + marginBottomOtherPages +
            ", backgroundPdfId=" + backgroundPdfId +
            ", id=" + id +
            '}';
    }
}
