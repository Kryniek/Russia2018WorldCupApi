package pl.cup.russia.api.Russia2018Api.external.api;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiLeague;
import pl.cup.russia.api.Russia2018Api.util.jackson.CustomJsonObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiLeagueMapperTest {

    private CustomJsonObjectMapper mapper = new CustomJsonObjectMapper();

    @Test
    public void map_one_league_to_object() throws IOException {
        ApiLeague expectedLeague = getLeagueForTest(1);
        File file = new File(getClass().getResource("/api/league/oneLeague.json").getFile());
        ApiLeague readedLeague = mapper.readValue(file, ApiLeague.class);

        Assert.assertEquals(expectedLeague, readedLeague);
    }

    @Test
    public void map_many_leagues_to_list_as_api_returns() throws IOException {
        List<ApiLeague> expectedLeagues = new ArrayList<>();
        for (int i = 1; i <= 4; i++)
            expectedLeagues.add(getLeagueForTest(i));

        File file = new File(getClass().getResource("/api/league/manyLeagues.json").getFile());
        List<ApiLeague> readedLeague = mapper.readValue(file, new TypeReference<List<ApiLeague>>() {});

        Assert.assertEquals(expectedLeagues, readedLeague);
    }

    private ApiLeague getLeagueForTest(Integer iterator) {
        ApiLeague league = new ApiLeague();
        league.setCountryId(iterator);
        league.setCountryName("Country " + iterator);
        league.setLeagueId(iterator);
        league.setLeagueName("League " + iterator);

        return league;
    }

}
