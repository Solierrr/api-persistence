package com.solaria.persistence.service3;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tools.jackson.databind.ObjectMapper;

import com.solaria.persistence.domain.entity.Contact;
import com.solaria.persistence.dto3.request.ContactRequestDTO;
import com.solaria.persistence.dto3.response.ContactResponseDTO;
import com.solaria.persistence.exception3.ResourceInUseException;
import com.solaria.persistence.exception3.ResourceNotFoundException;
import com.solaria.persistence.repository.ContactRepository;
import com.solaria.persistence.repository.PersonRepository;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final PersonRepository personRepository;
    private final ObjectMapper objectMapper;

    public ContactService(ContactRepository contactRepository,
                          PersonRepository personRepository,
                          ObjectMapper objectMapper) {
        this.contactRepository = contactRepository;
        this.personRepository = personRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public ContactResponseDTO save(ContactRequestDTO dto) {
        Contact contact = new Contact();
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());

        return toResponse(contactRepository.save(contact));
    }

    @Transactional
    public ContactResponseDTO update(UUID id, ContactRequestDTO dto) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contato com id:" + id + " não encontrado(a) para atualização"));

        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());

        return toResponse(contactRepository.save(contact));
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Contato com id:" + id + " não encontrado(a) para exclusão");
        }
        if (personRepository.existsByContactId(id)) {
            throw new ResourceInUseException(
                    "Contato não pode ser excluído(a): possui pessoa vinculada");
        }
        contactRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ContactResponseDTO findById(UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contato não encontrado(a) com ID: " + id));
        return toResponse(contact);
    }

    @Transactional(readOnly = true)
    public List<ContactResponseDTO> findAll() {
        return contactRepository.findAll().stream().map(this::toResponse).toList();
    }

    private ContactResponseDTO toResponse(Contact contact) {
        return objectMapper.convertValue(contact, ContactResponseDTO.class);
    }
}
