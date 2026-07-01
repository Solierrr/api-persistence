package com.solaria.persistence.Repository;

import com.solaria.persistence.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
}
