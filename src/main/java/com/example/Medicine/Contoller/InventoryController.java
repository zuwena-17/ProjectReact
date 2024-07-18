package com.example.Medicine.Contoller;

import com.example.Medicine.Model.InventoryModel;
import com.example.Medicine.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

 
    @GetMapping("/all")
    public List<InventoryModel> getAllMedicines() {
        return inventoryRepository.findAll();
    }

  
    @GetMapping("/{id}")
    public Optional<InventoryModel> getMedicineById(@PathVariable Long id) {
        return inventoryRepository.findById(id);
    }

    // POST add a new medicine
    @PostMapping("/adds")
    public InventoryModel addMedicine(@RequestBody InventoryModel newMedicine) {
        return inventoryRepository.save(newMedicine);
    }

   
    @PutMapping("/{id}")
    public InventoryModel updateMedicine(@PathVariable Long id, @RequestBody InventoryModel updatedMedicine) {
        return inventoryRepository.findById(id)
                .map(medicine -> {
                    medicine.setName(updatedMedicine.getName());
                    medicine.setBatch(updatedMedicine.getBatch());
                    medicine.setQuality(updatedMedicine.getQuality());
                    medicine.setPrice(updatedMedicine.getPrice());
                    medicine.setQuantity(updatedMedicine.getQuantity());
                    medicine.setExpiryDate(updatedMedicine.getExpiryDate());
                    return inventoryRepository.save(medicine);
                })
                .orElseGet(() -> {
                    updatedMedicine.setId(id);
                    return inventoryRepository.save(updatedMedicine);
                });
    }

    
    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable Long id) {
        inventoryRepository.deleteById(id);
    }

}
