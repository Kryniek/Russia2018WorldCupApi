package pl.cup.russia.api.Russia2018Api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.cup.russia.api.Russia2018Api.external.api.model.ApiLeague;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "leagues")
public class League {

    @Id
    private String id;

    private Integer leagueApiId;

    private String name;

    private List<Standing> standings = new ArrayList<>();

    // matches collection would be refactored - issue #
    @Transient
    private List<Match> matches = new ArrayList<>();

    private List<Integer> matchesId = new ArrayList<>();

    public League(ApiLeague apiLeague) {
        this.leagueApiId = apiLeague.getLeagueId();
        this.name = apiLeague.getLeagueName();
    }

    public Boolean isGroupStage() {
        if (this.name.contains("Group")) // TODO: const
            return TRUE;

        return FALSE;
    }

}
