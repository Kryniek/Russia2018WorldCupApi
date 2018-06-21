package pl.cup.russia.api.Russia2018Api.dto;

import static java.util.Objects.nonNull;

import java.util.List;

import lombok.Data;
import pl.cup.russia.api.Russia2018Api.model.security.User;

@Data
public class BetResult {

	String _id;

	Integer totalPoints;

	String name;

	String surname;

	public void addUserNameAndSurnameFromUser(List<User> users) {
		User betResultUser = users.stream().filter(user -> user.getUsername().equals(this.get_id())).findFirst()
				.orElse(null);

		if (nonNull(betResultUser)) {
			this.setName(betResultUser.getName());
			this.setSurname(betResultUser.getSurname());
		}
	}
}
