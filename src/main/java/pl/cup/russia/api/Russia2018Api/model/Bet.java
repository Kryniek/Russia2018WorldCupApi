package pl.cup.russia.api.Russia2018Api.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.cup.russia.api.Russia2018Api.dto.rest.BetValue;
import pl.cup.russia.api.Russia2018Api.enums.BetStatus;
import pl.cup.russia.api.Russia2018Api.enums.BetType;

@Data
@Document(collection = "bets")
public class Bet {

    private BetType type;

    private BetStatus status;

    private String username;

    private BetValue value;

}
