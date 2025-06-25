package Api.Core.controller;

import Api.Core.model.Client;
import Api.Core.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<Client> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> searchById(@PathVariable Long id) {
        return ResponseEntity.ok(service.searchById(id));
    }

    @PostMapping
    public ResponseEntity<Client> saveUser(@Valid @RequestBody Client client) {
        Client saved = service.saveUser(client);
        return ResponseEntity.created(URI.create("/api/clients/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateById(@PathVariable Long id, @Valid @RequestBody Client client) {
        return ResponseEntity.ok(service.updateById(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
