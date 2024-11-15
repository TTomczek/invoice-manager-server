package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.BusinessPartnersApiDelegate;
import net.tomczek.invoice.manager.api.server.model.BusinessPartnerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessPartnersApiDelegateImpl implements BusinessPartnersApiDelegate {

    @Override
    public ResponseEntity<BusinessPartnerDTO> createBusinessPartner(BusinessPartnerDTO businessPartnerDTO) {
        return BusinessPartnersApiDelegate.super.createBusinessPartner(businessPartnerDTO);
    }

    @Override
    public ResponseEntity<Void> deleteBusinessPartnerById(Integer id) {
        return BusinessPartnersApiDelegate.super.deleteBusinessPartnerById(id);
    }

    @Override
    public ResponseEntity<List<BusinessPartnerDTO>> getAllBusinessPartners() {
        return BusinessPartnersApiDelegate.super.getAllBusinessPartners();
    }

    @Override
    public ResponseEntity<BusinessPartnerDTO> getBusinessPartnerById(Integer id) {
        return BusinessPartnersApiDelegate.super.getBusinessPartnerById(id);
    }

    @Override
    public ResponseEntity<BusinessPartnerDTO> updateBusinessPartnerById(Integer id, BusinessPartnerDTO businessPartnerDTO) {
        return BusinessPartnersApiDelegate.super.updateBusinessPartnerById(id, businessPartnerDTO);
    }
}
