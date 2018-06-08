package pl.cup.russia.api.Russia2018Api.external.api;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiEvent;
import pl.cup.russia.api.Russia2018Api.util.jackson.CustomJsonObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ApiEventMapperTest {

    private CustomJsonObjectMapper mapper = new CustomJsonObjectMapper();

    @Test
    public void map_one_event_to_object_with_empty_nested_collections() throws IOException {
        ApiEvent expectedEvent = getEventForTest(1);
        File file = new File(getClass().getResource("/api/event/oneEvent.json").getFile());
        ApiEvent readedEvent = mapper.readValue(file, ApiEvent.class);

        Assert.assertEquals(expectedEvent, readedEvent);
    }

    @Test
    public void map_many_events_to_list_as_api_returns_with_empty_nested_collections() throws IOException {
        List<ApiEvent> expectedEvents = new ArrayList<>();
        for (int i = 1; i <= 6; i++)
            expectedEvents.add(getEventForTest(i));


        File file = new File(getClass().getResource("/api/event/manyEvents.json").getFile());
        List<ApiEvent> readedEvents = mapper.readValue(file, new TypeReference<List<ApiEvent>>() {
        });

        Assert.assertEquals(expectedEvents, readedEvents);
    }

    private ApiEvent getEventForTest(int iterator) {
        ApiEvent event = new ApiEvent();
        event.setMatchId(iterator);
        event.setCountryId(iterator);
        event.setCountryName("Country " + iterator);
        event.setLeagueId(iterator);
        event.setLeagueName("League " + iterator);
        event.setMatchDate(LocalDate.of(iterator, iterator, iterator));
        event.setMatchStatus("Status " + iterator); // would be enum?
        event.setMatchTime(LocalTime.of(iterator, iterator));
        event.setMatchHometeamName("Hometeam " + iterator);
        event.setMatchHometeamScore(iterator);
        event.setMatchAwayteamName("Awayteam " + iterator);
        event.setMatchAwayteamScore(iterator);
        event.setMatchHometeamHalftimeScore(iterator);
        event.setMatchAwayteamHalftimeScore(iterator);
        event.setMatchHometeamExtraScore(iterator);
        event.setMatchAwayteamExtraScore(iterator);
        event.setMatchHometeamPenaltyScore(iterator);
        event.setMatchAwayteamPenaltyScore(iterator);
        event.setMatchHometeamSystem(iterator + "-" + iterator + "-" + iterator);
        event.setMatchAwayteamSystem(iterator + "-" + iterator + "-" + iterator);
        event.setMatchLive((iterator % 2 == 0) ? FALSE : TRUE);

        return event;
    }

}
