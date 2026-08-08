package com.solaria.persistence.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solaria.persistence.DTO.Response.AddressResponseDTO;
import com.solaria.persistence.DTO.Response.BusinessContactResponseDTO;
import com.solaria.persistence.DTO.Request.CompanyRequestDTO;
import com.solaria.persistence.DTO.Response.CompanyResponseDTO;
import com.solaria.persistence.Domain.Entity.Address;
import com.solaria.persistence.Domain.Entity.BusinessContact;
import com.solaria.persistence.Domain.Entity.Company;
import com.solaria.persistence.Domain.enums.CompanyStatus;
import com.solaria.persistence.Exception.BusinessRuleException;
import com.solaria.persistence.Exception.DuplicateResourceException;
import com.solaria.persistence.Exception.InvalidFieldException;
import com.solaria.persistence.Exception.ResourceInUseException;
import com.solaria.persistence.Exception.ResourceNotFoundException;
import com.solaria.persistence.Repository.AddressRepository;
import com.solaria.persistence.Repository.BusinessContactRepository;
import com.solaria.persistence.Repository.CompanyPositionsRepository;
import com.solaria.persistence.Repository.CompanyRepository;
import com.solaria.persistence.Repository.RequesterRepository;
import com.solaria.persistence.Repository.SupplierRepository;
import com.solaria.persistence.Repository.TechnicianAffiliationRepository;
import com.solaria.persistence.Repository.UserCompanyRepository;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final BusinessContactRepository businessContactRepository;
    private final UserCompanyRepository userCompanyRepository;
    private final SupplierRepository supplierRepository;
    private final RequesterRepository requesterRepository;
    private final TechnicianAffiliationRepository technicianAffiliationRepository;
    private final CompanyPositionsRepository companyPositionsRepository;

    public CompanyService(CompanyRepository companyRepository,
                          AddressRepository addressRepository,
                          BusinessContactRepository businessContactRepository,
                          UserCompanyRepository userCompanyRepository,
                          SupplierRepository supplierRepository,
                          RequesterRepository requesterRepository,
                          TechnicianAffiliationRepository technicianAffiliationRepository,
                          CompanyPositionsRepository companyPositionsRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
        this.businessContactRepository = businessContactRepository;
        this.userCompanyRepository = userCompanyRepository;
        this.supplierRepository = supplierRepository;
        this.requesterRepository = requesterRepository;
        this.technicianAffiliationRepository = technicianAffiliationRepository;
        this.companyPositionsRepository = companyPositionsRepository;
    }

    @Transactional
    public CompanyResponseDTO save(CompanyRequestDTO dto) {
        String cnpj = dto.getCnpj();

        Address address = resolveAddress(dto.getAddressId());
        BusinessContact businessContact = resolveBusinessContact(dto.getBusinessContactId());

        if (companyRepository.existsByCnpj(cnpj)) {
            throw new DuplicateResourceException(
                    "Empresa já cadastrada para o CNPJ: " + cnpj);
        }

        Company company = new Company();
        company.setAddress(address);
        company.setBusinessContact(businessContact);
        company.setCnpj(cnpj);
        company.setTradeName(dto.getTradeName());
        company.setCorporateName(dto.getCorporateName());

        return toResponse(companyRepository.save(company));
    }

    @Transactional
    public CompanyResponseDTO update(UUID id, CompanyRequestDTO dto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Empresa com id:" + id + " não encontrada para atualização"));

        if (!company.getCnpj().equals(dto.getCnpj())) {
            throw new InvalidFieldException("CNPJ imutável: " + dto.getCnpj());
        }

        company.setAddress(resolveAddress(dto.getAddressId()));
        company.setBusinessContact(resolveBusinessContact(dto.getBusinessContactId()));
        company.setTradeName(dto.getTradeName());
        company.setCorporateName(dto.getCorporateName());

        return toResponse(companyRepository.save(company));
    }

    @Transactional
    public CompanyResponseDTO approve(UUID id) {
        return applyStatus(id, CompanyStatus.APPROVED);
    }

    @Transactional
    public CompanyResponseDTO reject(UUID id) {
        return applyStatus(id, CompanyStatus.REJECTED);
    }


    private CompanyResponseDTO applyStatus(UUID id, CompanyStatus target) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Empresa não encontrada com ID: " + id));
        if (company.getStatus() != CompanyStatus.UNDER_ANALYSIS) {
            throw new BusinessRuleException(
                    "Transição de status inválida: " + company.getStatus() + " → " + target);
        }
        company.setStatus(target);
        return toResponse(companyRepository.save(company));
    }

    @Transactional
    public CompanyResponseDTO attachAddress(UUID companyId, UUID addressId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Empresa não encontrada com ID: " + companyId));

        company.setAddress(addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Endereço não encontrado com ID: " + addressId)));

        return toResponse(companyRepository.save(company));
    }

    @Transactional
    public CompanyResponseDTO attachBusinessContact(UUID companyId, UUID businessContactId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Empresa não encontrada com ID: " + companyId));

        company.setBusinessContact(businessContactRepository.findById(businessContactId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contato Empresarial não encontrado com ID: " + businessContactId)));

        return toResponse(companyRepository.save(company));
    }


    @Transactional
    public void deleteById(UUID id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Empresa com id:" + id + " não encontrada para exclusão");
        }

        if (userCompanyRepository.existsByCompanyId(id)
                || supplierRepository.existsByCompanyId(id)
                || requesterRepository.existsByCompanyId(id)
                || technicianAffiliationRepository.existsByCompanyId(id)
                || companyPositionsRepository.existsByCompanyId(id)) {
            throw new ResourceInUseException(
                    "Empresa não pode ser excluída: possui vínculo(s) associado(s)");
        }

        companyRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CompanyResponseDTO findById(UUID id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Empresa não encontrada com ID: " + id));
        return toResponse(company);
    }

    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> findAll() {
        return companyRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CompanyResponseDTO findByCnpj(String cnpj) {
        String normalized = normalizeCnpj(cnpj);
        Company company = companyRepository.findByCnpj(normalized)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Empresa não encontrada com CNPJ: " + normalized));
        return toResponse(company);
    }


    private String normalizeCnpj(String cnpj) {
        return cnpj == null ? null : cnpj.replaceAll("\\D", "");
    }

    private Address resolveAddress(UUID addressId) {
        if (addressId == null) {
            return null;
        }
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Endereço não encontrado com ID: " + addressId));
    }

    private BusinessContact resolveBusinessContact(UUID businessContactId) {
        if (businessContactId == null) {
            return null;
        }
        return businessContactRepository.findById(businessContactId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Contato Empresarial não encontrado com ID: " + businessContactId));
    }

    private CompanyResponseDTO toResponse(Company company) {
        CompanyResponseDTO dto = new CompanyResponseDTO();
        dto.setId(company.getId());
        dto.setStatus(company.getStatus());
        dto.setCnpj(company.getCnpj());
        dto.setTradeName(company.getTradeName());
        dto.setCorporateName(company.getCorporateName());
        dto.setAddress(toAddressResponse(company.getAddress()));
        dto.setBusinessContact(toBusinessContactResponse(company.getBusinessContact()));
        return dto;
    }

    private AddressResponseDTO toAddressResponse(Address address) {
        if (address == null) {
            return null;
        }
        return AddressResponseDTO.builder()
                .id(address.getId())
                .state(address.getState())
                .city(address.getCity())
                .neighborhood(address.getNeighborhood())
                .zipCode(address.getZipCode())
                .street(address.getStreet())
                .number(address.getNumber())
                .build();
    }

    private BusinessContactResponseDTO toBusinessContactResponse(BusinessContact businessContact) {
        if (businessContact == null) {
            return null;
        }
        BusinessContactResponseDTO dto = new BusinessContactResponseDTO();
        dto.setId(businessContact.getId());
        dto.setCompanyEmail(businessContact.getCompanyEmail());
        dto.setPhone(businessContact.getPhone());
        dto.setWebsite(businessContact.getWebsite());
        return dto;
    }
}
