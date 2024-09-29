package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoicesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.Invoice;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class InvoiceApiDelegate implements InvoicesApiDelegate {

    @Override
    public Mono<ResponseEntity<Flux<Invoice>>> invoicesGet(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(Flux.empty()));
    }
}
