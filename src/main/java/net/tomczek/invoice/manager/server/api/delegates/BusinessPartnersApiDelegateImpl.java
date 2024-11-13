package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.BusinessPartnersApiDelegate;
import net.tomczek.invoice.manager.api.server.model.BusinessPartnerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class BusinessPartnersApiDelegateImpl implements BusinessPartnersApiDelegate {

    @Override
    public Mono<ResponseEntity<BusinessPartnerDTO>> createBusinessPartner(Mono<BusinessPartnerDTO> businessPartner, ServerWebExchange exchange) {
        return BusinessPartnersApiDelegate.super.createBusinessPartner(businessPartner, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteBusinessPartnerById(Integer id, ServerWebExchange exchange) {
        return BusinessPartnersApiDelegate.super.deleteBusinessPartnerById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<BusinessPartnerDTO>>> getAllBusinessPartners(ServerWebExchange exchange) {
        return BusinessPartnersApiDelegate.super.getAllBusinessPartners(exchange);
    }

    @Override
    public Mono<ResponseEntity<BusinessPartnerDTO>> getBusinessPartnerById(Integer id, ServerWebExchange exchange) {
        return BusinessPartnersApiDelegate.super.getBusinessPartnerById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<BusinessPartnerDTO>> updateBusinessPartnerById(Integer id, Mono<BusinessPartnerDTO> businessPartner, ServerWebExchange exchange) {
        return BusinessPartnersApiDelegate.super.updateBusinessPartnerById(id, businessPartner, exchange);
    }
}
