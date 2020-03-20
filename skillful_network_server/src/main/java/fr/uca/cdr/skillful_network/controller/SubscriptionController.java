package fr.uca.cdr.skillful_network.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.uca.cdr.skillful_network.model.entities.Subscription;
import fr.uca.cdr.skillful_network.model.entities.User;
import fr.uca.cdr.skillful_network.model.services.SubscriptionService;

@RestController
@CrossOrigin(origins = "*")
@Transactional
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@GetMapping("/subscriptions")
	public ResponseEntity<List<Subscription>> getAllSubscriptions() {
		List<Subscription> listSubscription = this.subscriptionService.getAllSubscription();
		return new ResponseEntity<List<Subscription>>(listSubscription, HttpStatus.OK);
	}

	@GetMapping("/subscriptions/{id}")
	public Optional<Subscription> findById(@PathVariable("id") long id) {
		return subscriptionService.getSubscriptionById(id);
	}

	@GetMapping(value = "/subscriptions/{id}/users")
	public ResponseEntity<Set<User>> getAllUserBySubscription(@PathVariable(value = "id") Long id) throws Throwable {
		Set<User> listUser = this.subscriptionService.getSubscriptionById(id).map((subscription) -> {
			return subscription.getUserList();
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun abonnement trouvé avec l'id : " + id));

		return new ResponseEntity<Set<User>>(listUser, HttpStatus.OK);

	}

	@GetMapping(value = "/subscriptions/{name}")
	public ResponseEntity<Subscription> getSubscriptionByName(@PathVariable(value = "name") String name) {
		Subscription subscriptionFromDb = this.subscriptionService.getSubscriptionByName(name).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun abonnement trouvé avec le nom " + name));

		return new ResponseEntity<Subscription>(HttpStatus.OK);
	}

	@PostMapping(value = "/subscriptions")
	public Subscription save(@Valid @RequestBody Subscription subscription) {
		return subscriptionService.saveOrUpdateSubscription(subscription);

	}

	@DeleteMapping("/subscriptions/{id}")
	public void deleteSubscription(@PathVariable(value = "id") Long id) {
		subscriptionService.deleteSubscription(id);
	}

}