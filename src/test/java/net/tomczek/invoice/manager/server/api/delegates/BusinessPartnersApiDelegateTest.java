package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.model.BusinessPartnerDTO;
import net.tomczek.invoice.manager.server.TestObjects;
import net.tomczek.invoice.manager.server.services.IBusinessPartnerService;
import net.tomczek.invoice.manager.server.services.IContactPersonService;
import net.tomczek.invoice.manager.server.services.IInvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BusinessPartnersApiDelegateTest {

    BusinessPartnersApiDelegateImpl cut;
    IInvoiceService invoiceService = mock(IInvoiceService.class);
    IBusinessPartnerService businessPartnerService = mock(IBusinessPartnerService.class);
    IContactPersonService contactPersonService = mock(IContactPersonService.class);

    @BeforeEach
    public void setUp() {
        cut = new BusinessPartnersApiDelegateImpl(businessPartnerService, contactPersonService, invoiceService);
        TestObjects.resetData();
    }

    @Test
    void createBusinessPartnerTest() {
        when(businessPartnerService.createBusinessPartner(TestObjects.businessPartner)).thenReturn(TestObjects.businessPartner);
        BusinessPartnerDTO businessPartnerDTO = TestObjects.businessPartnerDTO;
        System.out.println(businessPartnerDTO);
        ResponseEntity<BusinessPartnerDTO> result = cut.createBusinessPartner(businessPartnerDTO);
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(businessPartnerDTO));
    }

    @Test
    void createBusinessPartnerWithoutBusinessPartnerTest() {
        ResponseEntity<BusinessPartnerDTO> result = cut.createBusinessPartner(null);
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Test
    void getBusinessPartnerByIdTest() {
        when(businessPartnerService.getBusinessPartnerById(anyInt())).thenReturn(TestObjects.businessPartner);
        BusinessPartnerDTO businessPartnerDTO = TestObjects.businessPartnerDTO;
        ResponseEntity<BusinessPartnerDTO> result = cut.getBusinessPartnerById(businessPartnerDTO.getId());
        assertThat(result).isEqualTo(ResponseEntity.ok(businessPartnerDTO));
    }

    @Test
    void getBusinessPartnerWithoutBusinessPartnerTest() {
        ResponseEntity<BusinessPartnerDTO> result = cut.getBusinessPartnerById(null);
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    void getBusinessPartnerWithoutIdTest() {
        BusinessPartnerDTO businessPartnerDTO = TestObjects.businessPartnerDTO;
        businessPartnerDTO.setId(null);
        ResponseEntity<BusinessPartnerDTO> result = cut.getBusinessPartnerById(businessPartnerDTO.getId());
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    void updateBusinessPartnerTest() {
        when(businessPartnerService.updateBusinessPartnerById(anyInt(), any())).thenReturn(TestObjects.businessPartner);
        BusinessPartnerDTO businessPartnerDTO = TestObjects.businessPartnerDTO;
        ResponseEntity<BusinessPartnerDTO> result = cut.updateBusinessPartnerById(businessPartnerDTO.getId(), businessPartnerDTO);
        assertThat(result).isEqualTo(ResponseEntity.ok(businessPartnerDTO));
    }

    @Test
    void updateBusinessPartnerWithoutBusinessPartnerTest() {
        ResponseEntity<BusinessPartnerDTO> result = cut.updateBusinessPartnerById(null, null);
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Test
    void updateBusinessPartnerWithoutIdTest() {
        BusinessPartnerDTO businessPartnerDTO = TestObjects.businessPartnerDTO;
        businessPartnerDTO.setId(null);
        ResponseEntity<BusinessPartnerDTO> result = cut.updateBusinessPartnerById(businessPartnerDTO.getId(), businessPartnerDTO);
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    void updateBusinessPartnerWithDifferentIdTest() {
        BusinessPartnerDTO businessPartnerDTO = TestObjects.businessPartnerDTO;
        ResponseEntity<BusinessPartnerDTO> result = cut.updateBusinessPartnerById(5, businessPartnerDTO);
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Test
    void deleteBusinessPartnerTest() {
        when(businessPartnerService.deleteBusinessPartnerById(anyInt())).thenReturn(TestObjects.businessPartner);
        BusinessPartnerDTO businessPartnerDTO = TestObjects.businessPartnerDTO;
        ResponseEntity<BusinessPartnerDTO> result = cut.deleteBusinessPartnerById(businessPartnerDTO.getId());
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.OK).body(businessPartnerDTO));
    }

    @Test
    void deleteBusinessPartnerWithoutIdTest() {
        ResponseEntity<BusinessPartnerDTO> result = cut.deleteBusinessPartnerById(null);
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    void deleteBusinessPartnerWithNonExistigIdTest() {
        ResponseEntity<BusinessPartnerDTO> result = cut.deleteBusinessPartnerById(5);
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Test
    void getBusinessPartnersTest() {
        when(businessPartnerService.getAllBusinessPartners()).thenReturn(List.of(TestObjects.businessPartner));
        ResponseEntity<List<BusinessPartnerDTO>> result = cut.getAllBusinessPartners();
        assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.OK).body(List.of(TestObjects.businessPartnerDTO)));
    }
}
