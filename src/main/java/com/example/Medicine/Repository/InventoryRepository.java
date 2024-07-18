package com.example.Medicine.Repository;

import com.example.Medicine.Model.InventoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryModel, Long> {
}
