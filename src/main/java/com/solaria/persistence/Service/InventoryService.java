package com.solaria.persistence.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solaria.persistence.DTO.Request.InventoryRequestDTO;
import com.solaria.persistence.DTO.Response.InventoryResponseDTO;
import com.solaria.persistence.DTO.Response.ModelResponseDTO;
import com.solaria.persistence.Domain.Entity.Inventory;
import com.solaria.persistence.Domain.Entity.Model;
import com.solaria.persistence.Domain.Entity.Supplier;
import com.solaria.persistence.Domain.enums.SupplierStatus;
import com.solaria.persistence.Exception.BusinessRuleException;
import com.solaria.persistence.Exception.DuplicateResourceException;
import com.solaria.persistence.Exception.InvalidFieldException;
import com.solaria.persistence.Exception.ResourceNotFoundException;
import com.solaria.persistence.Repository.InventoryRepository;
import com.solaria.persistence.Repository.ModelRepository;
import com.solaria.persistence.Repository.SupplierRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final SupplierRepository supplierRepository;
    private final ModelRepository modelRepository;
    private final SubscriptionService subscriptionService;

    public InventoryService(InventoryRepository inventoryRepository,
                            SupplierRepository supplierRepository,
                            ModelRepository modelRepository,
                            SubscriptionService subscriptionService) {
        this.inventoryRepository = inventoryRepository;
        this.supplierRepository = supplierRepository;
        this.modelRepository = modelRepository;
        this.subscriptionService = subscriptionService;
    }

    @Transactional
    public InventoryResponseDTO save(InventoryRequestDTO dto) {
        validateQuantity(dto.getQuantity());

        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Fornecedor não encontrado com ID: " + dto.getSupplierId()));

        Model model = modelRepository.findById(dto.getModelId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Modelo não encontrado com ID: " + dto.getModelId()));

        if (inventoryRepository.existsBySupplierIdAndModelId(dto.getSupplierId(), dto.getModelId())) {
            throw new DuplicateResourceException(
                    "Estoque já cadastrado para o fornecedor " + dto.getSupplierId() + " e modelo " + dto.getModelId());
        }

        validateSupplierAndSubscriptionGates(supplier);

        Inventory inventory = new Inventory();
        inventory.setSupplier(supplier);
        inventory.setModel(model);
        inventory.setQuantity(dto.getQuantity());

        return toResponse(inventoryRepository.save(inventory));
    }

    @Transactional
    public InventoryResponseDTO updateQuantity(UUID id, Integer quantity) {
        validateQuantity(quantity);

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Estoque com id:" + id + " não encontrado para atualização"));

        validateSupplierAndSubscriptionGates(inventory.getSupplier());

        inventory.setQuantity(quantity);
        return toResponse(inventoryRepository.save(inventory));
    }

    @Transactional
    public void deleteById(UUID id) {
        if (!inventoryRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Estoque com id:" + id + " não encontrado para exclusão");
        }
        inventoryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public InventoryResponseDTO findById(UUID id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Estoque não encontrado com ID: " + id));
        return toResponse(inventory);
    }

    @Transactional(readOnly = true)
    public InventoryResponseDTO findById(UUID id, UUID companyId) {
        Inventory inventory = inventoryRepository.findByIdAndSupplier_Company_Id(id, companyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Estoque não encontrado com ID: " + id));
        return toResponse(inventory);
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> findBySupplier(UUID supplierId) {
        return inventoryRepository.findBySupplierId(supplierId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> findAllByCompany(UUID companyId) {
        return inventoryRepository.findBySupplier_Company_Id(companyId).stream()
                .map(this::toResponse)
                .toList();
    }

    private void validateQuantity(Integer quantity) {
        if (quantity == null || quantity < 0) {
            throw new InvalidFieldException("Quantidade inválida: " + quantity);
        }
    }

    private void validateSupplierAndSubscriptionGates(Supplier supplier) {
        if (supplier.getStatus() != SupplierStatus.ACTIVE) {
            throw new BusinessRuleException("Fornecedor não está ativo: " + supplier.getId());
        }
        if (!subscriptionService.isSupplierSubscriptionActive(supplier.getId())) {
            throw new BusinessRuleException("Fornecedor não possui assinatura ativa: " + supplier.getId());
        }
    }

    private InventoryResponseDTO toResponse(Inventory inventory) {
        InventoryResponseDTO response = new InventoryResponseDTO();
        response.setId(inventory.getId());
        response.setSupplierId(inventory.getSupplier().getId());
        response.setModel(toModelResponse(inventory.getModel()));
        response.setQuantity(inventory.getQuantity());
        return response;
    }

    private ModelResponseDTO toModelResponse(Model model) {
        if (model == null) {
            return null;
        }
        return ModelResponseDTO.builder()
                .id(model.getId())
                .brand(model.getBrand())
                .model(model.getModel())
                .powerWp(model.getPowerWp())
                .efficiency(model.getEfficiency())
                .dimension(model.getDimension())
                .weight(model.getWeight())
                .status(model.getStatus())
                .build();
    }
}
