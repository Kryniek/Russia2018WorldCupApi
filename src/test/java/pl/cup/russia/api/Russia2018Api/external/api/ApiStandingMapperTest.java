package pl.cup.russia.api.Russia2018Api.external.api;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiStanding;
import pl.cup.russia.api.Russia2018Api.util.jackson.CustomJsonObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiStandingMapperTest {

    private CustomJsonObjectMapper mapper = new CustomJsonObjectMapper();

    @Test
    public void map_one_standing_to_object() throws IOException {
        ApiStanding expectedStanding = getStandingForTest(1);
        File file = new File(getClass().getResource("/api/standing/oneStanding.json").getFile());
        ApiStanding readedStanding = mapper.readValue(file, ApiStanding.class);

        Assert.assertEquals(expectedStanding, readedStanding);
    }

    @Test
    public void map_many_standings_to_list_as_api_returns() throws IOException {
        List<ApiStanding> expectedStandings = new ArrayList<>();
        for (int i = 1; i <= 4; i++)
            expectedStandings.add(getStandingForTest(i));


        File file = new File(getClass().getResource("/api/standing/manyStandings.json").getFile());
        List<ApiStanding> readedStandings = mapper.readValue(file, new TypeReference<List<ApiStanding>>() {
        });

        Assert.assertEquals(expectedStandings, readedStandings);
    }

    private ApiStanding getStandingForTest(Integer iterator) {
        ApiStanding standing = new ApiStanding();
        standing.setCountryName("Country " + iterator);
        standing.setLeagueId(iterator);
        standing.setLeagueName("League " + iterator);
        standing.setTeamName("Team " + iterator);
        standing.setOverallLeaguePosition(iterator);
        standing.setOverallLeaguePayed(iterator);
        standing.setOverallLeagueW(iterator);
        standing.setOverallLeagueD(iterator);
        standing.setOverallLeagueL(iterator);
        standing.setOverallLeagueGF(iterator);
        standing.setOverallLeagueGA(iterator);
        standing.setOverallLeaguePTS(iterator);

        return standing;
    }

}
