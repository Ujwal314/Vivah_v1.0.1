package com.example.controller;

import com.example.entity.Address;
import com.example.entity.User;
import com.example.service.AddressService;
import com.example.Repositories.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final UserService userService;
    private final UserRepository userRepository;

    // Get all addresses
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }
    @GetMapping("/me")
    public ResponseEntity<Address> getAddress() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email).orElseThrow();
        return addressService.getAddressesByUser(user)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new for a user
    @PostMapping("/user/{userId}")
    public ResponseEntity<Address> createAddress(@PathVariable Long userId, @RequestBody Address address) {
        return addressService.createAddress(userId, address)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/me")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email).orElseThrow();
        return addressService.createAddress(user.getUserId(), address)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/me")
    public ResponseEntity<Address> updateAddress(@RequestBody Address addressDetails) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByEmail(email).orElseThrow();
        return addressService.updateAddress(addressService.getAddressesByUser(user).get().getAddressId(), addressDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        boolean deleted = addressService.deleteAddress(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get addresses by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<Address>> getAddressesByUser(@PathVariable Long userId) {
        return userRepository.findById(userId)
                .map(user -> ResponseEntity.ok(addressService.getAddressesByUser(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
