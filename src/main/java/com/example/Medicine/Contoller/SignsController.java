package com.example.Medicine.Contoller;

import org.springframework.web.bind.annotation.RestController;
import com.example.Medicine.Model.Logins;
import com.example.Medicine.Repository.SignRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class SignsController {

    private final SignRepository signRepository;

    public SignsController(SignRepository signRepository) {
        this.signRepository = signRepository;
    }

    @GetMapping("/logins")
    public ResponseEntity<List<Logins>> getAllLogin() {
        List<Logins> logins = signRepository.findAll();
        return new ResponseEntity<>(logins, HttpStatus.OK);
    }

    @GetMapping("/logins/{id}")
    public ResponseEntity<Logins> getLoginById(@PathVariable Long id) {
        Optional<Logins> login = signRepository.findById(id);
        return login.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/logins")
    public ResponseEntity<Logins> createLogin(@RequestBody Logins login) {
        Logins savedLogin = signRepository.save(login);
        return new ResponseEntity<>(savedLogin, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Logins login) {
        Optional<Logins> user = signRepository.findByUserName(login.getUserName());
        if (user.isPresent() && user.get().getPassword().equals(login.getPassword())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/logins/{id}")
    public ResponseEntity<Logins> updateLogin(@PathVariable Long id, @RequestBody Logins loginDetails) {
        Optional<Logins> loginOptional = signRepository.findById(id);

        if (loginOptional.isPresent()) {
            Logins login = loginOptional.get();
            login.setUserName(loginDetails.getUserName());
            login.setPassword(loginDetails.getPassword());
            Logins updatedLogin = signRepository.save(login);
            return new ResponseEntity<>(updatedLogin, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/logins/{id}")
    public ResponseEntity<Void> deleteLogin(@PathVariable Long id) {
        Optional<Logins> loginOptional = signRepository.findById(id);

        if (loginOptional.isPresent()) {
            signRepository.delete(loginOptional.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
