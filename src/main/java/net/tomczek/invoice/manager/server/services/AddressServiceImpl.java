package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.converter.AddressConverter;
import net.tomczek.invoice.manager.server.entities.Address;
import net.tomczek.invoice.manager.server.repositories.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    private final AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        Address savedAddress = addressRepository.save(address);
        logger.debug("Address saved: {}", savedAddress);
        return savedAddress;
    }

    @Override
    public Address deleteAddressById(Integer id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            return null;
        }
        addressRepository.deleteById(id);
        logger.debug("Address deleted: {}", address);
        return address;
    }

    @Override
    public List<Address> getAllAddresss() {
        List<Address> addresses = addressRepository.findAll();
        logger.debug("Fetched addresses: {}", addresses.size());
        return addresses;
    }

    @Override
    public Address getAddressById(Integer id) {
        Address address = addressRepository.findById(id).orElse(null);
        logger.debug("Fetched address: {}", address);
        return address;
    }

    @Override
    public Address updateAddressById(Integer id, Address address) {
        Address addresstoUpdate = addressRepository.findById(id).orElse(null);
        if (addresstoUpdate == null) {
            return null;
        }

        addresstoUpdate.setCity(address.getCity())
                .setCountry(address.getCountry())
                .setHouseNumber(address.getHouseNumber())
                .setStreet(address.getStreet())
                .setZipCode(address.getZipCode());

        Address savedAddress = addressRepository.save(addresstoUpdate);
        logger.debug("Address updated: {}", savedAddress);
        return savedAddress;
    }

    @Override
    public boolean exists(Integer id) {
        return addressRepository.existsById(id);
    }


}
