package com.example.Medicine.Contoller;

import org.springframework.web.bind.annotation.RestController;
import com.example.Medicine.Model.Stock;
import com.example.Medicine.Repository.StockRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class StockController {

    private final StockRepository stockRepository;

    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping("/stocks")
    public ResponseEntity<List<Stock>> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/stocks")
    public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
        Stock savedStock = stockRepository.save(stock);
        return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
    }

    @PutMapping("/stocks/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody Stock stockDetails) {
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if (stockOptional.isPresent()) {
            Stock stock = stockOptional.get();
            stock.setName(stockDetails.getName());
            stock.setQuantity(stockDetails.getQuantity());
            stock.setPrice(stockDetails.getPrice());
            Stock updatedStock = stockRepository.save(stock);
            return new ResponseEntity<>(updatedStock, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        Optional<Stock> stockOptional = stockRepository.findById(id);

        if (stockOptional.isPresent()) {
            stockRepository.delete(stockOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
