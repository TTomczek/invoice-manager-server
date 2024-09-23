package net.tomczek.invoice.manager.server.api.delegates;

import net.tomczek.invoice.manager.api.server.api.InvoicesApiDelegate;
import net.tomczek.invoice.manager.api.server.model.Invoice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceApiDelegate implements InvoicesApiDelegate {

    @Override
    public ResponseEntity<List<Invoice>> invoicesGet() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
}
