package Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CompetitionController {
	@Autowired
	private CompetitionService competitionService;
	//Fetches users
	@GetMapping("/users")
	public ResponseEntity<Users> getUsers() {
		Users ob = competitionService.getUsers();
		return new ResponseEntity<Users>(ob, HttpStatus.OK);
	}

	//Updates user
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable("id") Integer id, @RequestBody User user) {
		User ob = competitionService.updateUserById(user, id);
		return new ResponseEntity<User>(ob, HttpStatus.OK);
	}

	//delete user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Integer> updateChildrenById(@PathVariable("id") Integer id) {
		Integer ob = competitionService.deleteById(id);
		return new ResponseEntity<Integer>(ob, HttpStatus.OK);
	}


} 