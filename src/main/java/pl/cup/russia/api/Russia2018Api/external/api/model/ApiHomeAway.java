package pl.cup.russia.api.Russia2018Api.external.api.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiHomeAway {

    private List<ApiLineupPerson> startingLineups = new ArrayList<>();

    private List<ApiLineupPerson> substitutes = new ArrayList<>();

    private List<ApiLineupPerson> coach = new ArrayList<>(); // dunno why they return is as array but we can go over it

    private List<ApiLineupSubstitution> substitutions = new ArrayList<>();

}
