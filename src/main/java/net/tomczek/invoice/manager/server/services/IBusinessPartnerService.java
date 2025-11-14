package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.BusinessPartner;

import java.util.List;

public interface IBusinessPartnerService {

    BusinessPartner createBusinessPartner(BusinessPartner businessPartner);

    BusinessPartner deleteBusinessPartnerById(Integer id);

    List<BusinessPartner> getAllBusinessPartners(String name);

    BusinessPartner getBusinessPartnerById(Integer id);

    BusinessPartner updateBusinessPartnerById(Integer id, BusinessPartner businessPartner);

    List<BusinessPartner> getAllBusinessPartnerByIds(List<Integer> ids);

    boolean exists(Integer id);

}
