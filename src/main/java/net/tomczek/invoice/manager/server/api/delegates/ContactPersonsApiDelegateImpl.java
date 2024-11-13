package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.ContactPersonsApiDelegate;
import net.tomczek.invoice.manager.api.server.model.ContactPersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ContactPersonsApiDelegateImpl implements ContactPersonsApiDelegate {

    @Override
    public Mono<ResponseEntity<Flux<ContactPersonDTO>>> contactPersonsGet(ServerWebExchange exchange) {
        return ContactPersonsApiDelegate.super.contactPersonsGet(exchange);
    }

    @Override
    public Mono<ResponseEntity<ContactPersonDTO>> createContactPerson(Mono<ContactPersonDTO> contactPerson, ServerWebExchange exchange) {
        return ContactPersonsApiDelegate.super.createContactPerson(contactPerson, exchange);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteContactPersonById(Integer id, ServerWebExchange exchange) {
        return ContactPersonsApiDelegate.super.deleteContactPersonById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<ContactPersonDTO>> getContactPersonById(Integer id, ServerWebExchange exchange) {
        return ContactPersonsApiDelegate.super.getContactPersonById(id, exchange);
    }

    @Override
    public Mono<ResponseEntity<ContactPersonDTO>> updateContactPersonById(Integer id, Mono<ContactPersonDTO> contactPerson, ServerWebExchange exchange) {
        return ContactPersonsApiDelegate.super.updateContactPersonById(id, contactPerson, exchange);
    }
}
