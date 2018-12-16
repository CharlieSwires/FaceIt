package Search;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

	@SuppressWarnings("unchecked")
	private HashMap<Integer,User> users =new HashMap<Integer,User>();
	

	public Users getUsers() {
		Users users = new Users();
		List<User> ul = new ArrayList<>();
		ul.addAll(this.users.values());
		users.setUsers(ul);
		return users;
	}
	public boolean createUsers(Users users) {
		for (User user : users.getUsers()) {
			this.users.put(user.getId(),user);
		}
		return true;
	}

	public User updateUserById(User user, Integer id) {	
		if (this.users.containsKey(id)) {
			user.setId(id);
			this.users.put(id,user);
			return user;
		}
		return null;

	}

	public Integer deleteById(Integer id) {
		this.users.remove(id);
		return id;
	}
	public Users getUsersByName(String name) {
		List<User> users = new ArrayList<User>();
		for (User u : this.users.values()) {
			if (u != null && u.getName().equals(name)) {
				users.add(u);
			}
		}
		Users result = new Users();
		result.setUsers(users);
		return result;
	}

}
