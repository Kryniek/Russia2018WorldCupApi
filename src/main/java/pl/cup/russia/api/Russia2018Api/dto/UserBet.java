package pl.cup.russia.api.Russia2018Api.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.cup.russia.api.Russia2018Api.model.Bet;
import pl.cup.russia.api.Russia2018Api.model.Match;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBet {

	private LocalDate date;

	private LocalTime time;

	private Bet bet;

	private Match match;
}
