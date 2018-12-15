package Search;


import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import com.owlike.genson.ext.jaxrs.GensonJsonConverter;



@Service
public class SearchService {

	private List<User> users = new ArrayList<User>();
	

	public Users getUsers() {
		Users users = new Users();
		users.setUsers(this.users);
		return users;
	}
	public boolean createUsers(Users users) {
		for (User user : users.getUsers()) {
			this.users.add(user);
		}
		return true;
	}

	public User updateUserById(User user, Integer id) {
		boolean empty = true;
		for (int i = 0; i < this.users.size();i++) {
			User u = this.users.get(i);
			if (u.getId()==user.getId()) {
				this.users.remove(i);
				this.users.add(user);
				empty = false;
				break;
			}
		}
		if (empty) {
			this.users.add(user);
		}
		return user;

	}

	public Integer deleteById(Integer id) {
		for (int i = 0; i < this.users.size();i++) {
			User u = this.users.get(i);
			if (u.getId()==id) {
				this.users.remove(i);
				break;
			}
		}
		return id;
	}
	public Users getUsersByName(String name) {
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < this.users.size();i++) {
			User u = this.users.get(i);
			if (u.getName().equals(name)) {
				users.add(u);
			}
		}
		Users result = new Users();
		result.setUsers(users);
		return result;
	}

}
