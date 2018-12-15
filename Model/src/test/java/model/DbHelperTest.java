package test.java.model;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import main.java.model.DbHelper;
import main.java.model.User;
import main.java.model.Users;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class DbHelperTest {

	@Autowired
	private DbHelper helper;
	
	@Test
	public void saveUsers() {
		Assert.assertTrue(helper!=null);

		//generate a user
		//generate another
		//generate users
		//save
		
		asaveUsers();
		fdeleteUser();
	}
	
	public void asaveUsers() {
		Assert.assertTrue(helper!=null);

		//generate a user
		//generate another
		//generate users
		//save
		
		User u1 = new User();
		u1.setName("Charles");
		u1.setLastName("Swires");
		u1.setNickname("Charlie");
		u1.setPassword("Password");
		u1.setEmail("charlieswires@gmail.com");
		u1.setCountry("UK");
		User u2 = new User();
		u2.setName("Alena");
		u2.setLastName("Leonova");
		u2.setNickname("Alena");
		u2.setPassword("Password2");
		u2.setEmail("al@gmail.com");
		u2.setCountry("Ukraine");
		List<User> users = new ArrayList<User>();
		users.add(u1);
		users.add(u2);
		Users us = new Users();
		us.setUsers(users);
		Assert.assertTrue("Not saved users",helper.createUsers(us));
	}
	public void fdeleteUser() {
		Assert.assertTrue(helper!=null);
		//delete last
		//check remaining list
		User us0 = helper.getUsersByCountry("UK").getUsers().get(0);
		helper.delete(new BigInteger(""+us0.getId()));
		Users users = helper.getUsers();
		List<User> us = users.getUsers();
		Assert.assertTrue("!=1",us.size()==1);
		User us1 = helper.getUsersByCountry("Ukraine").getUsers().get(0);
		helper.delete(new BigInteger(""+us1.getId()));
		users = helper.getUsers();
		Assert.assertTrue("!=null",users == null);
	
	}

	@Test
	public void breadUsers() {
		Assert.assertTrue(helper!=null);
		asaveUsers();
		//read above back and compare
		Users users = helper.getUsers();
		List<User> us = users.getUsers();
		User u1 = new User();
		u1.setName("Charles");
		u1.setLastName("Swires");
		u1.setNickname("Charlie");
		u1.setPassword("Password");
		u1.setEmail("charlieswires@gmail.com");
		u1.setCountry("UK");
		User u2 = new User();
		u2.setName("Alena");
		u2.setLastName("Leonova");
		u2.setNickname("Alena");
		u2.setPassword("Password2");
		u2.setEmail("al@gmail.com");
		u2.setCountry("Ukraine");
		Assert.assertTrue("not Charles",u1.getName().equals(us.get(0).getName()));
		Assert.assertTrue("not Alena",u2.getName().equals(us.get(1).getName()));
		Assert.assertTrue("not Swires",u1.getLastName().equals(us.get(0).getLastName()));
		Assert.assertTrue("not Leonova",u2.getLastName().equals(us.get(1).getLastName()));
		Assert.assertTrue("not Charlie",u1.getNickname().equals(us.get(0).getNickname()));
		Assert.assertTrue("not Alena",u2.getNickname().equals(us.get(1).getNickname()));
		Assert.assertTrue("not Password",u1.getPassword().equals(us.get(0).getPassword()));
		Assert.assertTrue("not Password2",u2.getPassword().equals(us.get(1).getPassword()));
		Assert.assertTrue("not email",u1.getEmail().equals(us.get(0).getEmail()));
		Assert.assertTrue("not email",u2.getEmail().equals(us.get(1).getEmail()));
		Assert.assertTrue("not UK",u1.getCountry().equals(us.get(0).getCountry()));
		Assert.assertTrue("not Ukraine",u2.getCountry().equals(us.get(1).getCountry()));
		fdeleteUser();
	}
	
	@Test
	public void creadUser() {
		Assert.assertTrue(helper!=null);
		asaveUsers();
		//read last then compare
		//read first then compare
		Users users = helper.getUsers();
		List<User> us = users.getUsers();
		User u1 = new User();
		u1.setName("Charles");
		u1.setLastName("Swires");
		u1.setNickname("Charlie");
		u1.setPassword("Password");
		u1.setEmail("charlieswires@gmail.com");
		u1.setCountry("UK");
		User u2 = new User();
		u2.setName("Alena");
		u2.setLastName("Leonova");
		u2.setNickname("Alena");
		u2.setPassword("Password2");
		u2.setEmail("al@gmail.com");
		u2.setCountry("Ukraine");
		User us0 = helper.getUserById(new BigInteger(""+us.get(0).getId()));
		User us1 = helper.getUserById(new BigInteger(""+us.get(1).getId()));
		
		Assert.assertTrue("not Charles",u1.getName().equals(us0.getName()));
		Assert.assertTrue("not Alena",u2.getName().equals(us1.getName()));
		Assert.assertTrue("not Swires",u1.getLastName().equals(us0.getLastName()));
		Assert.assertTrue("not Leonova",u2.getLastName().equals(us1.getLastName()));
		Assert.assertTrue("not Charlie",u1.getNickname().equals(us0.getNickname()));
		Assert.assertTrue("not Alena",u2.getNickname().equals(us1.getNickname()));
		Assert.assertTrue("not Password",u1.getPassword().equals(us0.getPassword()));
		Assert.assertTrue("not Password2",u2.getPassword().equals(us1.getPassword()));
		Assert.assertTrue("not email",u1.getEmail().equals(us0.getEmail()));
		Assert.assertTrue("not email",u2.getEmail().equals(us1.getEmail()));
		Assert.assertTrue("not UK",u1.getCountry().equals(us0.getCountry()));
		Assert.assertTrue("not Ukraine",u2.getCountry().equals(us1.getCountry()));
		fdeleteUser();
	}	
	
	@Test
	public void dsearchForUsers() {
		Assert.assertTrue(helper!=null);
		asaveUsers();
		//read one country then the other compare lists and items
		Users users = helper.getUsers();
		List<User> us = users.getUsers();
		User u1 = new User();
		u1.setName("Charles");
		u1.setLastName("Swires");
		u1.setNickname("Charlie");
		u1.setPassword("Password");
		u1.setEmail("charlieswires@gmail.com");
		u1.setCountry("UK");
		User u2 = new User();
		u2.setName("Alena");
		u2.setLastName("Leonova");
		u2.setNickname("Alena");
		u2.setPassword("Password2");
		u2.setEmail("al@gmail.com");
		u2.setCountry("Ukraine");
		Assert.assertTrue("size != 1 "+helper.getUsersByCountry("UK").getUsers().size(),helper.getUsersByCountry("UK").getUsers().size()==1);
		Assert.assertTrue("size != 1 "+helper.getUsersByCountry("Ukraine").getUsers().size(),helper.getUsersByCountry("Ukraine").getUsers().size()==1);
		
		User us0 = helper.getUsersByCountry("UK").getUsers().get(0);
		User us1 = helper.getUsersByCountry("Ukraine").getUsers().get(0);
		System.out.println("us0"+us0.toString());
		System.out.println("us1"+us1.toString());
		Assert.assertTrue("not Charles",u1.getName().equals(us0.getName()));
		Assert.assertTrue("not Alena",u2.getName().equals(us1.getName()));
		Assert.assertTrue("not Swires",u1.getLastName().equals(us0.getLastName()));
		Assert.assertTrue("not Leonova",u2.getLastName().equals(us1.getLastName()));
		Assert.assertTrue("not Charlie",u1.getNickname().equals(us0.getNickname()));
		Assert.assertTrue("not Alena",u2.getNickname().equals(us1.getNickname()));
		Assert.assertTrue("not Password",u1.getPassword().equals(us0.getPassword()));
		Assert.assertTrue("not Password2",u2.getPassword().equals(us1.getPassword()));
		Assert.assertTrue("not email",u1.getEmail().equals(us0.getEmail()));
		Assert.assertTrue("not email",u2.getEmail().equals(us1.getEmail()));
		Assert.assertTrue("not UK",u1.getCountry().equals(us0.getCountry()));
		Assert.assertTrue("not Ukraine",u2.getCountry().equals(us1.getCountry()));
		fdeleteUser();
}
	@Test
	public void emodifyUser() {
		Assert.assertTrue(helper!=null);
		//modify first and check change occured
		//modify last and check change occured
		asaveUsers();
		Users users = helper.getUsers();
		List<User> us = users.getUsers();
		User u1 = new User();
		u1.setName("Charles1");
		u1.setLastName("Swires1");
		u1.setNickname("Charlie1");
		u1.setPassword("Password1");
		u1.setEmail("charlieswires@gmail.com1");
		u1.setCountry("UK1");
		User u2 = new User();
		u2.setName("Alena1");
		u2.setLastName("Leonova1");
		u2.setNickname("Alena1");
		u2.setPassword("Password21");
		u2.setEmail("al@gmail.com1");
		u2.setCountry("Ukraine1");
		Assert.assertTrue("size != 1 "+helper.getUsersByCountry("UK").getUsers().size(),helper.getUsersByCountry("UK").getUsers().size()==1);
		Assert.assertTrue("size != 1 "+helper.getUsersByCountry("Ukraine").getUsers().size(),helper.getUsersByCountry("Ukraine").getUsers().size()==1);
		
		User us0 = helper.getUsersByCountry("UK").getUsers().get(0);
		User us1 = helper.getUsersByCountry("Ukraine").getUsers().get(0);
		helper.updateUserById(u1, new BigInteger(""+us0.getId()));
		helper.updateUserById(u2, new BigInteger(""+us1.getId()));
		us0 = helper.getUsersByCountry("UK1").getUsers().get(0);
		us1 = helper.getUsersByCountry("Ukraine1").getUsers().get(0);
		System.out.println("us0"+us0.toString());
		System.out.println("us1"+us1.toString());
		Assert.assertTrue("not Charles",u1.getName().equals(us0.getName()));
		Assert.assertTrue("not Alena",u2.getName().equals(us1.getName()));
		Assert.assertTrue("not Swires",u1.getLastName().equals(us0.getLastName()));
		Assert.assertTrue("not Leonova",u2.getLastName().equals(us1.getLastName()));
		Assert.assertTrue("not Charlie",u1.getNickname().equals(us0.getNickname()));
		Assert.assertTrue("not Alena",u2.getNickname().equals(us1.getNickname()));
		Assert.assertTrue("not Password",u1.getPassword().equals(us0.getPassword()));
		Assert.assertTrue("not Password2",u2.getPassword().equals(us1.getPassword()));
		Assert.assertTrue("not email",u1.getEmail().equals(us0.getEmail()));
		Assert.assertTrue("not email",u2.getEmail().equals(us1.getEmail()));
		Assert.assertTrue("not UK",u1.getCountry().equals(us0.getCountry()));
		Assert.assertTrue("not Ukraine",u2.getCountry().equals(us1.getCountry()));
		//delete last
		//check remaining list
		us0 = helper.getUsersByCountry("UK1").getUsers().get(0);
		helper.delete(new BigInteger(""+us0.getId()));
		users = helper.getUsers();
		us = users.getUsers();
		Assert.assertTrue("!=1",us.size()==1);
		us1 = helper.getUsersByCountry("Ukraine1").getUsers().get(0);
		helper.delete(new BigInteger(""+us1.getId()));
		users = helper.getUsers();
		Assert.assertTrue("!=null",users == null);

	}

}
