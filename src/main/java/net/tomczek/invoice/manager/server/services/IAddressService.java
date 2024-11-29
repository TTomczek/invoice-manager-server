package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.models.Address;
import net.tomczek.invoice.manager.server.models.BusinessPartner;

import java.util.List;

public interface IAddressService {

    Address createAddress(Address address);

    Address deleteAddressById(Integer id);

    List<Address> getAllAddresss();

    Address getAddressById(Integer id);

    Address updateAddressById(Integer id, Address address);

    boolean exists(Integer id);
}
