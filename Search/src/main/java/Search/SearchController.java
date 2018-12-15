package Search;
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
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/users")
	public ResponseEntity<Users> getUsers() {
		Users ob = searchService.getUsers();
		return new ResponseEntity<Users>(ob, HttpStatus.OK);
	}

	//Fetches user by id
	@GetMapping("/users/{name}")
	public ResponseEntity<Users> getUserByName(@PathVariable("name") String name) {
		Users ob = searchService.getUsersByName(name);
		return new ResponseEntity<Users>(ob, HttpStatus.OK);
	}

	//Creates a new users
	@PostMapping("/users")
	public ResponseEntity<Void> addUsers(@RequestBody Users users, UriComponentsBuilder builder) {
		boolean flag = searchService.createUsers(users);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//Updates user
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUserById(@PathVariable("id") Integer id, @RequestBody User user) {
		User ob = searchService.updateUserById(user, id);
		return new ResponseEntity<User>(ob, HttpStatus.OK);
	}

	//delete user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Integer> updateChildrenById(@PathVariable("id") Integer id) {
		Integer ob = searchService.deleteById(id);
		return new ResponseEntity<Integer>(ob, HttpStatus.OK);
	}


} 