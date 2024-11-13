package net.tomczek.invoice.manager.server.models.converter;

import net.tomczek.invoice.manager.api.server.model.AddressDTO;
import net.tomczek.invoice.manager.server.entities.AddressDAO;
import net.tomczek.invoice.manager.server.models.Address;

public class AddressConverter {

    public static AddressDTO toDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet(address.getStreet());
        addressDTO.setNumber(address.getHouseNumber());
        addressDTO.setZip(address.getZipCode());
        addressDTO.setCity(address.getCity());
        addressDTO.setCountry(address.getCountry());
        return addressDTO;
    }

    public static Address toEntityFromDAO(net.tomczek.invoice.manager.server.entities.AddressDAO addressDAO) {
        Address address = new Address();
        address.setStreet(addressDAO.getStreet());
        address.setHouseNumber(addressDAO.getHouseNumber());
        address.setZipCode(addressDAO.getZipCode());
        address.setCity(addressDAO.getCity());
        address.setCountry(addressDAO.getCountry());
        return address;
    }

    public static Address toEntityFromDTO(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setHouseNumber(addressDTO.getNumber());
        address.setZipCode(addressDTO.getZip());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        return address;
    }

    public static AddressDAO toDAO(Address address) {
        AddressDAO addressDAO = new AddressDAO();
        addressDAO.setStreet(address.getStreet());
        addressDAO.setHouseNumber(address.getHouseNumber());
        addressDAO.setZipCode(address.getZipCode());
        addressDAO.setCity(address.getCity());
        addressDAO.setCountry(address.getCountry());
        return addressDAO;
    }

}
