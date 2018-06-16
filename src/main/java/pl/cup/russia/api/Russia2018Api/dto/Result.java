package pl.cup.russia.api.Russia2018Api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.cup.russia.api.Russia2018Api.enums.MatchResult;
import pl.cup.russia.api.Russia2018Api.enums.WinnerSide;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Result {

    private MatchResult result;

    private String winner;

    private WinnerSide side;

    public Result(MatchResult result) {
        this.result = result;
    }

}
