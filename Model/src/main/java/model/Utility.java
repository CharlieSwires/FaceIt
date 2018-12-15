package main.java.model;
import java.math.BigInteger;

public class Utility {
	public static void copyUserDbtoUser(UserDb ud, User u) {
		u.setCountry(ud.getCountry());
		u.setEmail(ud.getEmail());
		u.setId(ud.getId().intValue());
		u.setLastName(ud.getLastName());
		u.setName(ud.getName());
		u.setNickname(ud.getNickname());
		u.setPassword(ud.getPassword());
	}
	public static void copyUsertoUserDb(User u, UserDb ud) {
		ud.setCountry(u.getCountry());
		ud.setEmail(u.getEmail());
		ud.setId(new BigInteger(""+u.getId()));
		ud.setLastName(u.getLastName());
		ud.setName(u.getName());
		ud.setNickname(u.getNickname());
		ud.setPassword(u.getPassword());
	}
}
