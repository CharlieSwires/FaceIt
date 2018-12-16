package main.java.model;


import java.math.BigInteger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

@Service
public class ModelService {
	@Autowired
	public main.java.model.DbHelper db;

	public User getUserById(BigInteger id) {
		return db.getUserById(id);
	}
	public Users getUsers() {
		return db.getUsers();
	}

	public Users getUsersByCountry(String country) {
		return db.getUsersByCountry(country);
	}

	public boolean createUsers(Users users) {
		
		if (db.createUsers(users)) {
			Client client =ClientBuilder.newClient(new ClientConfig().register(GensonJsonConverter.class));
			WebTarget webTarget = client.target("http://localhost:8080/Search").path("users");
	
			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(users, MediaType.APPLICATION_JSON));
			return true;
		}
		return false;
	}

	public User updateUserById(User user, BigInteger id) {
		User u1 = db.getUserById(id);
		User u = db.updateUserById(user, id);
		if (u != null && !u1.getNickname().equals(u.getNickname())) {
			Client client =ClientBuilder.newClient(new ClientConfig().register(GensonJsonConverter.class));
			WebTarget webTarget = client.target("http://localhost:8080/Competition").path("users").path(""+id);
	
			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.put(Entity.entity(u, MediaType.APPLICATION_JSON));
			
		}
		if (u != null ) {
			Client client =ClientBuilder.newClient(new ClientConfig().register(GensonJsonConverter.class));
			WebTarget webTarget = client.target("http://localhost:8080/Search").path("users").path(""+id);

			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.put(Entity.entity(u, MediaType.APPLICATION_JSON));
			
		}
		
		return u;
	}

	public Integer deleteById(BigInteger id) {
		db.delete(id);
		Client client =ClientBuilder.newClient(new ClientConfig().register(GensonJsonConverter.class));
		WebTarget webTarget = client.target("http://localhost:8080/Competition").path("users").path(""+id);

		Invocation.Builder invocationBuilder =  webTarget.request();
		Response response = invocationBuilder.delete();
		Client client2 =ClientBuilder.newClient(new ClientConfig().register(GensonJsonConverter.class));
		WebTarget webTarget2 = client2.target("http://localhost:8080/Search").path("users").path(""+id);
		 
		Invocation.Builder invocationBuilder2 =  webTarget2.request();
		Response response2 = invocationBuilder2.delete();
		 
		return id.intValue();
	}

}
