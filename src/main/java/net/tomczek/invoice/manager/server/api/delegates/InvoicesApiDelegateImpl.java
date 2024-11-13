package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoicesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoiceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class InvoicesApiDelegateImpl implements InvoicesApiDelegate {

    @Override
    public Mono<ResponseEntity<InvoiceDTO>> createInvoice(Mono<InvoiceDTO> invoice, ServerWebExchange exchange) {
        return InvoicesApiDelegate.super.createInvoice(invoice, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteInvoiceById(Integer id, ServerWebExchange exchange) {
        return InvoicesApiDelegate.super.deleteInvoiceById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<InvoiceDTO>>> getAllInvoices(ServerWebExchange exchange) {
        return InvoicesApiDelegate.super.getAllInvoices(exchange);
    }

    @Override
    public Mono<ResponseEntity<InvoiceDTO>> getInvoiceById(Integer id, ServerWebExchange exchange) {
        return InvoicesApiDelegate.super.getInvoiceById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<InvoiceDTO>> updateInvoiceById(Integer id, Mono<InvoiceDTO> invoice, ServerWebExchange exchange) {
        return InvoicesApiDelegate.super.updateInvoiceById(id, invoice, exchange);
    }
}
