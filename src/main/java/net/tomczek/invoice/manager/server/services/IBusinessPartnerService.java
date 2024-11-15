package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.models.BusinessPartner;

import java.util.List;

public interface IBusinessPartnerService {

    public BusinessPartner createBusinessPartner(BusinessPartner businessPartner);

    public BusinessPartner deleteBusinessPartnerById(Integer id);

    public List<BusinessPartner> getAllBusinessPartners();

    public BusinessPartner getBusinessPartnerById(Integer id);

    public BusinessPartner updateBusinessPartnerById(Integer id, BusinessPartner businessPartner);
}
