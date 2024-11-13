package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoiceTemplatesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.InvoiceTemplateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class InvoiceTemplatesApiDelegateImpl implements InvoiceTemplatesApiDelegate {

    @Override
    public Mono<ResponseEntity<Void>> deleteInvoiceTemplateById(Integer id, ServerWebExchange exchange) {
        return InvoiceTemplatesApiDelegate.super.deleteInvoiceTemplateById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<Flux<InvoiceTemplateDTO>>> getAllInvoiceTemplates(ServerWebExchange exchange) {
        return InvoiceTemplatesApiDelegate.super.getAllInvoiceTemplates(exchange);
    }

    @Override
    public Mono<ResponseEntity<InvoiceTemplateDTO>> getInvoiceTemplateById(Integer id, ServerWebExchange exchange) {
        return InvoiceTemplatesApiDelegate.super.getInvoiceTemplateById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<InvoiceTemplateDTO>> invoiceTemplatesPost(Mono<InvoiceTemplateDTO> invoiceTemplateDTO, ServerWebExchange exchange) {
        return InvoiceTemplatesApiDelegate.super.invoiceTemplatesPost(invoiceTemplateDTO, exchange);
    }

    @Override
    public Mono<ResponseEntity<InvoiceTemplateDTO>> updateInvoiceTemplateById(Integer id, Mono<InvoiceTemplateDTO> invoiceTemplateDTO, ServerWebExchange exchange) {
        return InvoiceTemplatesApiDelegate.super.updateInvoiceTemplateById(id, invoiceTemplateDTO, exchange);
    }
}
