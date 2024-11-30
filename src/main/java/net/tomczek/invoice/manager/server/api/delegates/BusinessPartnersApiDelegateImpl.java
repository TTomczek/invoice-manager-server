package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.BusinessPartnersApiDelegate;
import net.tomczek.invoice.manager.api.server.model.BusinessPartnerDTO;
import net.tomczek.invoice.manager.server.models.BusinessPartner;
import net.tomczek.invoice.manager.server.models.converter.BusinessPartnerConverter;
import net.tomczek.invoice.manager.server.services.IBusinessPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessPartnersApiDelegateImpl implements BusinessPartnersApiDelegate {

    @Autowired
    public BusinessPartnersApiDelegateImpl(IBusinessPartnerService businessPartnerService) {
        this.businessPartnerService = businessPartnerService;
    }

    private final IBusinessPartnerService businessPartnerService;

    @Override
    public ResponseEntity<BusinessPartnerDTO> createBusinessPartner(BusinessPartnerDTO businessPartnerDTO) {
        BusinessPartner businessPartner = BusinessPartnerConverter.toEntityFromDTO(businessPartnerDTO);

        BusinessPartner createdBusinessPartner = businessPartnerService.createBusinessPartner(businessPartner);
        BusinessPartnerDTO createdBusinessPartnerDTO = BusinessPartnerConverter.toDTO(createdBusinessPartner);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBusinessPartnerDTO);
    }

    @Override
    public ResponseEntity<BusinessPartnerDTO> deleteBusinessPartnerById(Integer id) {
        BusinessPartner deletedBusinessPartner = businessPartnerService.deleteBusinessPartnerById(id);
        if (deletedBusinessPartner == null) {
            return ResponseEntity.notFound().build();
        } else {
            BusinessPartnerDTO deletedBusinessPartnerDTO = BusinessPartnerConverter.toDTO(deletedBusinessPartner);
            return ResponseEntity.ok(deletedBusinessPartnerDTO);
        }
    }

    @Override
    public ResponseEntity<List<BusinessPartnerDTO>> getAllBusinessPartners() {
        List<BusinessPartner> businessPartners = businessPartnerService.getAllBusinessPartners();
        List<BusinessPartnerDTO> businessPartnerDTOS = BusinessPartnerConverter.toDTO(businessPartners);
        return ResponseEntity.ok().body(businessPartnerDTOS);
    }

    @Override
    public ResponseEntity<BusinessPartnerDTO> getBusinessPartnerById(Integer id) {
        BusinessPartner businessPartner = businessPartnerService.getBusinessPartnerById(id);
        if (businessPartner == null) {
            return ResponseEntity.notFound().build();
        } else {
            BusinessPartnerDTO businessPartnerDTO = BusinessPartnerConverter.toDTO(businessPartner);
            return ResponseEntity.ok().body(businessPartnerDTO);
        }
    }

    @Override
    public ResponseEntity<BusinessPartnerDTO> updateBusinessPartnerById(Integer id, BusinessPartnerDTO businessPartnerDTO) {
        BusinessPartner businessPartner = BusinessPartnerConverter.toEntityFromDTO(businessPartnerDTO);
        BusinessPartner updatedBusinessPartner = businessPartnerService.updateBusinessPartnerById(id, businessPartner);
        BusinessPartnerDTO updatedBusinessPartnerDTO = BusinessPartnerConverter.toDTO(updatedBusinessPartner);
        return ResponseEntity.ok().body(updatedBusinessPartnerDTO);
    }
}
