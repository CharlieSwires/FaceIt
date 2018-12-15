package Competition;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CompetitionService {
	private List<User> users = new ArrayList<User>();
	public Users getUsers() {
		Users users = new Users();
		users.setUsers(this.users);
		return users;
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

}
