package pl.cup.russia.api.Russia2018Api.dto.rest;

import lombok.*;
import pl.cup.russia.api.Russia2018Api.enums.MatchResult;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Result {

    private MatchResult result;

    private String winner;

    public Result(MatchResult result) {
        this.result = result;
    }

}
