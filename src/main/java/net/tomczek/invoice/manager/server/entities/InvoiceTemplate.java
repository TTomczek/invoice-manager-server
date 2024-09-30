package net.tomczek.invoice.manager.server.entities;

import jakarta.persistence.Entity;

import java.io.File;

@Entity
public class InvoiceTemplate extends BaseEntity<Integer> {

    private String name;

    private float marginTopFirstPage;
    private float marginBottomFirstPage;
    private float marginTopFollowingPages;
    private float marginBottomFollowingPages;
    private File backgroundPdf;
}
