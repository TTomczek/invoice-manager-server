package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.TestObjects;
import net.tomczek.invoice.manager.server.entities.BusinessPartner;
import net.tomczek.invoice.manager.server.repositories.BusinessPartnersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BusinessPartnerServiceImplTest {

    BusinessPartnerServiceImpl cut;
    BusinessPartnersRepository businessPartnersRepository = mock(BusinessPartnersRepository.class);
    IAddressService addressService = mock(IAddressService.class);

    @BeforeEach
    public void setUp() {
        cut = new BusinessPartnerServiceImpl(businessPartnersRepository, addressService);
        TestObjects.resetData();
    }

    @Test
    void createBusinessPartnerTest() {
        when(businessPartnersRepository.save(TestObjects.businessPartner)).thenReturn(TestObjects.businessPartner);
        BusinessPartner result = cut.createBusinessPartner(TestObjects.businessPartner);
        assertThat(result).isEqualTo(TestObjects.businessPartner);
    }

    @Test
    void createBusinessPartnerWithoutBusinessPartnerTest() {
        BusinessPartner result = cut.createBusinessPartner(null);
        assertThat(result).isNull();
    }

    @Test
    void createBusinessPartnerWithoutAddressTest() {
        BusinessPartner businessPartner = new BusinessPartner(89456, "Musterfirma GmbH", "Beschreibung der Musterfirma GmbH", null, null, null);
        BusinessPartner result = cut.createBusinessPartner(businessPartner);
        assertThat(result).isNull();
    }

    @Test
    void deleteBusinessPartnerByIdTest() {
        when(businessPartnersRepository.findById(TestObjects.businessPartner.getId())).thenReturn(java.util.Optional.of(TestObjects.businessPartner));
        BusinessPartner result = cut.deleteBusinessPartnerById(TestObjects.businessPartner.getId());
        assertThat(result).isEqualTo(TestObjects.businessPartner);
    }

    @Test
    void deleteBusinessPartnerByIdWithoutIdTest() {
        BusinessPartner result = cut.deleteBusinessPartnerById(null);
        assertThat(result).isNull();
    }

    @Test
    void getAllBusinessPartnersTest() {
        when(businessPartnersRepository.findAll()).thenReturn(List.of(TestObjects.businessPartner));
        List<BusinessPartner> result = cut.getAllBusinessPartners();
        assertThat(result).containsExactly(TestObjects.businessPartner);
    }

    @Test
    void getBusinessPartnerByIdTest() {
        when(businessPartnersRepository.findById(TestObjects.businessPartner.getId())).thenReturn(java.util.Optional.of(TestObjects.businessPartner));
        BusinessPartner result = cut.getBusinessPartnerById(TestObjects.businessPartner.getId());
        assertThat(result).isEqualTo(TestObjects.businessPartner);
    }

    @Test
    void getBusinessPartnerByIdWithoutIdTest() {
        BusinessPartner result = cut.getBusinessPartnerById(null);
        assertThat(result).isNull();
    }

    @Test
    void updateBusinessPartnerByIdTest() {
        when(businessPartnersRepository.findById(TestObjects.businessPartner.getId())).thenReturn(java.util.Optional.of(TestObjects.businessPartner));
        when(businessPartnersRepository.save(TestObjects.businessPartner)).thenReturn(TestObjects.businessPartner);
        BusinessPartner result = cut.updateBusinessPartnerById(TestObjects.businessPartner.getId(), TestObjects.businessPartner);
        assertThat(result).isEqualTo(TestObjects.businessPartner);
    }

    @Test
    void updateBusinessPartnerByIdWithoutIdTest() {
        BusinessPartner result = cut.updateBusinessPartnerById(null, null);
        assertThat(result).isNull();
    }

    @Test
    void updateBusinessPartnerByIdWithoutBusinessPartnerTest() {
        BusinessPartner result = cut.updateBusinessPartnerById(TestObjects.businessPartner.getId(), null);
        assertThat(result).isNull();
    }

    @Test
    void updateBusinessPartnerByIdWithDifferentIdTest() {
        BusinessPartner result = cut.updateBusinessPartnerById(5, TestObjects.businessPartner);
        assertThat(result).isNull();
    }
}
