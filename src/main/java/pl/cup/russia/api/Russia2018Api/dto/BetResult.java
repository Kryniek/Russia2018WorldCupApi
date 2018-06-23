package pl.cup.russia.api.Russia2018Api.dto;

import lombok.Data;
import pl.cup.russia.api.Russia2018Api.model.security.User;

@Data
public class BetResult {

    String _id;

    User user;

    Integer totalPoints;

}
