package com.example.Medicine.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Medicine.Model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
