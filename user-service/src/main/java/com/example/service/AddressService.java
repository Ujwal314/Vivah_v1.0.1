package com.example.service;

import com.example.entity.Address;
import com.example.entity.User;
import com.example.Repositories.AddressRepository;
import com.example.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    // Get all
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Get by ID
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    // Create new for a user
    public Optional<Address> createAddress(Long userId, Address address) {
        return userRepository.findById(userId).map(user -> {
            address.setUser(user);
            return addressRepository.save(address);
        });
    }

    // Update
    public Optional<Address> updateAddress(Long id, Address updatedDetails) {
        return addressRepository.findById(id).map(address -> {
            address.setCity(updatedDetails.getCity());
            address.setState(updatedDetails.getState());
            address.setPostalCode(updatedDetails.getPostalCode());
            address.setCountry(updatedDetails.getCountry());
            return addressRepository.save(address);
        });
    }

    // Delete
    public boolean deleteAddress(Long id) {
        if (addressRepository.existsById(id)) {
            addressRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get by User
    public Optional<Address> getAddressesByUser(User user) {
        return addressRepository.findByUser(user);
    }
}
