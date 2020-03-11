package fr.uca.cdr.skillful_network.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.repositories.UserRepository;
import fr.uca.cdr.skillful_network.request.LoginForm;

/**
 * Cette classe a pour rôle d'identifié les utilisateurs. L'authentification des
 * utilisateurs se fait grâce à l'email ou au numéro de mobile (en tant que nom
 * d'utilisateur) ainsi qu'avec un code temporaire envoyé par le serveur à
 * l'email ou au numéro de mobile. Elle est responsable de notamment du
 * traitement des requêtes /login et /token.
 */
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	public UserRepository userRepository;

	@PostMapping(value = "/login")
	public ResponseEntity<User> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		if (loginRequest != null) {
			Optional<User> userFromDB = userRepository.findByEmail(loginRequest.getEmail());
			if (!userFromDB.isPresent()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouv�");
			} else {
				String passwordFromDB = userFromDB.get().getPassword();
				String passwordRequest = loginRequest.getPassword();
				if (passwordRequest != null && !passwordRequest.equals(passwordFromDB)) {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Les 2 mots de passe ne correspondent pas");
				} else {
					return ResponseEntity.ok().body(userFromDB.get());
				}
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun utilisateur trouv�");
	}
}
