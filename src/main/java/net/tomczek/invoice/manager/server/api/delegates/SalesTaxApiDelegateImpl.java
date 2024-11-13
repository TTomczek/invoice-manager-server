package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.SalesTaxesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.SalesTaxDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SalesTaxApiDelegateImpl implements SalesTaxesApiDelegate {

    @Override
    public Mono<ResponseEntity<Void>> deleteSalesTaxById(Integer id, ServerWebExchange exchange) {
        return SalesTaxesApiDelegate.super.deleteSalesTaxById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<SalesTaxDTO>>> getAllSalesTaxes(ServerWebExchange exchange) {
        return SalesTaxesApiDelegate.super.getAllSalesTaxes(exchange);
    }

    @Override
    public Mono<ResponseEntity<SalesTaxDTO>> getSalesTaxById(Integer id, ServerWebExchange exchange) {
        return SalesTaxesApiDelegate.super.getSalesTaxById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<SalesTaxDTO>> salesTaxesPost(Mono<SalesTaxDTO> salesTax, ServerWebExchange exchange) {
        return SalesTaxesApiDelegate.super.salesTaxesPost(salesTax, exchange);
    }

    @Override
    public Mono<ResponseEntity<SalesTaxDTO>> updateSalesTaxById(Integer id, Mono<SalesTaxDTO> salesTax, ServerWebExchange exchange) {
        return SalesTaxesApiDelegate.super.updateSalesTaxById(id, salesTax, exchange);
    }
}
