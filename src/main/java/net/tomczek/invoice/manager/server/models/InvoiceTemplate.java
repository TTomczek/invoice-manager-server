package net.tomczek.invoice.manager.server.models;

/**
 * Internal model for InvoiceTemplateDAO/InvoiceTemplateDTO
 */
public class InvoiceTemplate {

    private Integer id;
    private String name;
    private float marginTopFirstPage;
    private float marginBottomFirstPage;
    private float marginTopOtherPages;
    private float marginBottomOtherPages;
    private Integer backgroundPdfId;

    public InvoiceTemplate(Integer id, String name, float marginTopFirstPage, float marginBottomFirstPage, float marginTopOtherPages, float marginBottomOtherPages, Integer backgroundPdfId) {
        this.id = id;
        this.name = name;
        this.marginTopFirstPage = marginTopFirstPage;
        this.marginBottomFirstPage = marginBottomFirstPage;
        this.marginTopOtherPages = marginTopOtherPages;
        this.marginBottomOtherPages = marginBottomOtherPages;
        this.backgroundPdfId = backgroundPdfId;
    }

    public InvoiceTemplate() {}

    public Integer getId() {
        return id;
    }

    public InvoiceTemplate setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public InvoiceTemplate setName(String name) {
        this.name = name;
        return this;
    }

    public float getMarginTopFirstPage() {
        return marginTopFirstPage;
    }

    public InvoiceTemplate setMarginTopFirstPage(float marginTopFirstPage) {
        this.marginTopFirstPage = marginTopFirstPage;
        return this;
    }

    public float getMarginBottomFirstPage() {
        return marginBottomFirstPage;
    }

    public InvoiceTemplate setMarginBottomFirstPage(float marginBottomFirstPage) {
        this.marginBottomFirstPage = marginBottomFirstPage;
        return this;
    }

    public float getMarginTopOtherPages() {
        return marginTopOtherPages;
    }

    public InvoiceTemplate setMarginTopOtherPages(float marginTopOtherPages) {
        this.marginTopOtherPages = marginTopOtherPages;
        return this;
    }

    public float getMarginBottomOtherPages() {
        return marginBottomOtherPages;
    }

    public InvoiceTemplate setMarginBottomOtherPages(float marginBottomOtherPages) {
        this.marginBottomOtherPages = marginBottomOtherPages;
        return this;
    }

    public Integer getBackgroundPdfId() {
        return backgroundPdfId;
    }

    public InvoiceTemplate setBackgroundPdfId(Integer backgroundPdfId) {
        this.backgroundPdfId = backgroundPdfId;
        return this;
    }
}
