package net.tomczek.invoice.manager.server.services;

import net.tomczek.invoice.manager.server.entities.AddressDAO;
import net.tomczek.invoice.manager.server.models.Address;
import net.tomczek.invoice.manager.server.models.converter.AddressConverter;
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
        AddressDAO addressDAOToSave = AddressConverter.toDAO(address);
        AddressDAO savedAddressDAO = addressRepository.save(addressDAOToSave);
        logger.debug("Address saved: {}", savedAddressDAO);
        Address savedAddress = AddressConverter.toEntityFromDAO(savedAddressDAO);
        return savedAddress;
    }

    @Override
    public Address deleteAddressById(Integer id) {
        AddressDAO addressDAO = addressRepository.findById(id).orElse(null);
        if (addressDAO == null) {
            return null;
        }
        addressRepository.deleteById(id);
        logger.debug("Address deleted: {}", addressDAO);
        Address address = AddressConverter.toEntityFromDAO(addressDAO);
        return address;
    }

    @Override
    public List<Address> getAllAddresss() {
        List<AddressDAO> addressDAOs = addressRepository.findAll();
        List<Address> addresses = AddressConverter.toEntityFromDAO(addressDAOs);
        logger.debug("Fetched addresses: {}", addresses.size());
        return addresses;
    }

    @Override
    public Address getAddressById(Integer id) {
        AddressDAO addressDAO = addressRepository.findById(id).orElse(null);
        Address address = AddressConverter.toEntityFromDAO(addressDAO);
        logger.debug("Fetched address: {}", address);
        return address;
    }

    @Override
    public Address updateAddressById(Integer id, Address address) {
        AddressDAO addressDAO = addressRepository.findById(id).orElse(null);
        if (addressDAO == null) {
            return null;
        }

        addressDAO.setCity(address.getCity())
                .setCountry(address.getCountry())
                .setHouseNumber(address.getHouseNumber())
                .setStreet(address.getStreet())
                .setZipCode(address.getZipCode());

        AddressDAO savedAddressDAO = addressRepository.save(addressDAO);
        logger.debug("Address updated: {}", savedAddressDAO);
        Address savedAddress = AddressConverter.toEntityFromDAO(savedAddressDAO);
        return savedAddress;
    }

    @Override
    public boolean exists(Integer id) {
        return addressRepository.existsById(id);
    }
}
