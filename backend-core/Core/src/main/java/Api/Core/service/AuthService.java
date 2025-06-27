package Api.Core.service;

import Api.Core.dto.RegisterRequest;
import Api.Core.model.Client;
import Api.Core.repository.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AuthService {

    @Autowired
    private ClientRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Client register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username já existe");
        }

        if (userRepository.findAll().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(request.getEmail()))) {
            throw new RuntimeException("Email já cadastrado");
        }

        Client user = new Client();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ROLE_USER");

        return userRepository.save(user);
    }
}
