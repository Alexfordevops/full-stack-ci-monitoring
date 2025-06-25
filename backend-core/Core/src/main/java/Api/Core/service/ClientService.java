package Api.Core.service;

import Api.Core.model.Client;
import Api.Core.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client saveUser(Client user) {
        return repository.save(user);
    }

    public List<Client> listAll() {
        return repository.findAll();
    }

    public Client searchById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    public Client updateById(Long id, @NotNull Client updatedClient) {
        Client userData = searchById(id);
        userData.setNome(updatedClient.getNome());
        userData.setEmail(updatedClient.getEmail());
        return repository.save(userData);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
