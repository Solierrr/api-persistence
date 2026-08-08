package com.solaria.persistence.Repository;

import com.solaria.persistence.Domain.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface PersonRepository extends JpaRepository<Person, UUID> {

    boolean existsByCpf(String cpf);

    boolean existsByCpfAndIdNot(String cpf, UUID id);

    boolean existsByUserId(UUID userId);

    boolean existsByContactId(UUID contactId);
}
