package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.SalesTax;
import net.tomczek.invoice.manager.server.models.converter.ConverterObjects;
import net.tomczek.invoice.manager.server.repositories.SalesTaxRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class SalesTaxServiceImplTest {

    @Autowired
    SalesTaxRepository salesTaxRepository;

    SalesTaxServiceImpl cut = new SalesTaxServiceImpl(salesTaxRepository);

    @Test
    public void createTest() {
        SalesTax salesTax = ConverterObjects.salesTax;
        cut.createSalesTax(salesTax);
        List<SalesTax> allSalesTaxes = cut.getAllSalesTaxs();
        assertThat(allSalesTaxes.size()).isEqualTo(1);
        assertThat(allSalesTaxes.get(0)).isEqualTo(salesTax);
    }

    @Test
    public void updateTest() {
        SalesTax salesTax = ConverterObjects.salesTax;
        cut.createSalesTax(salesTax);

        salesTax.setRate(new BigDecimal("0.25"));
        SalesTax updatedSalesTax = cut.updateSalesTaxById(salesTax.getId(), salesTax);

        List<SalesTax> allSalesTaxes = cut.getAllSalesTaxs();
        assertThat(allSalesTaxes.size()).isEqualTo(1);
        assertThat(updatedSalesTax).isEqualTo(salesTax);
    }
}
