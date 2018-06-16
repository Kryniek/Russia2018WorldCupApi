package pl.cup.russia.api.Russia2018Api.definition;

import pl.cup.russia.api.Russia2018Api.dto.BetResult;

import java.util.List;

public interface ResultService {

    List<BetResult> getResultsForPaidUsers();

    List<BetResult> getResultsForNonPaidUsers();
}
