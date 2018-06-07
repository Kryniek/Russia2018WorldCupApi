package pl.cup.russia.api.Russia2018Api.external.api.model;

import lombok.Data;

import java.util.List;

@Data
public class ApiHomeAway {

    private List<ApiLineupPerson> startingLineups;

    private List<ApiLineupPerson> substitutes;

    private List<ApiLineupPerson> coach; // dunno why they return is as array but we can go over it

    private List<ApiLineupSubstitution> substitutions;

}
