package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoicePositionsApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoicePositionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class InvoicePositionsApiDelegateImpl implements InvoicePositionsApiDelegate {

    @Override
    public Mono<ResponseEntity<InvoicePositionDTO>> createPosition(Mono<InvoicePositionDTO> invoicePositionDTO, ServerWebExchange exchange) {
        return InvoicePositionsApiDelegate.super.createPosition(invoicePositionDTO, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteInvoicePositionById(Integer id, ServerWebExchange exchange) {
        return InvoicePositionsApiDelegate.super.deleteInvoicePositionById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<InvoicePositionDTO>>> getAllInvoicePositions(ServerWebExchange exchange) {
        return InvoicePositionsApiDelegate.super.getAllInvoicePositions(exchange);
    }

    @Override
    public Mono<ResponseEntity<InvoicePositionDTO>> getInvoicePositionById(Integer id, ServerWebExchange exchange) {
        return InvoicePositionsApiDelegate.super.getInvoicePositionById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<InvoicePositionDTO>> updateInvoicePositionById(Integer id, Mono<InvoicePositionDTO> invoicePositionDTO, ServerWebExchange exchange) {
        return InvoicePositionsApiDelegate.super.updateInvoicePositionById(id, invoicePositionDTO, exchange);
    }
}
