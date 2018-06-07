package pl.cup.russia.api.Russia2018Api.external.api;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiLeague;
import pl.cup.russia.api.Russia2018Api.util.CustomJsonObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiLeagueMapperTest {

    private CustomJsonObjectMapper mapper = new CustomJsonObjectMapper();

    @Test
    public void map_one_league_to_object() throws IOException {
        ApiLeague expectedApiLeague = getLeagueForTest(1);
        File file = new File(getClass().getResource("/api/oneApiLeague.json").getFile());
        ApiLeague readedApiLeague = mapper.readValue(file, ApiLeague.class);

        Assert.assertEquals(expectedApiLeague, readedApiLeague);
    }

    @Test
    public void map_many_leagues_to_list_as_api_returns() throws IOException {
        List<ApiLeague> expectedApiLeagues = new ArrayList<>();
        for (int i = 1; i < 5; i++)
            expectedApiLeagues.add(getLeagueForTest(i));


        File file = new File(getClass().getResource("/api/manyApiLeagues.json").getFile());
        List<ApiLeague> readedApiLeague = mapper.readValue(file, new TypeReference<List<ApiLeague>>() {});

        Assert.assertEquals(expectedApiLeagues, readedApiLeague);
    }

    private ApiLeague getLeagueForTest(Integer counter) {
        ApiLeague apiLeague = new ApiLeague();
        apiLeague.setCountryId(counter);
        apiLeague.setCountryName("Country " + counter);
        apiLeague.setLeagueId(counter);
        apiLeague.setLeagueName("League " + counter);

        return apiLeague;
    }

}
