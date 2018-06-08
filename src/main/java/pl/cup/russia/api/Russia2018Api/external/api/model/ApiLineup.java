package pl.cup.russia.api.Russia2018Api.external.api.model;

import lombok.Data;

@Data
public class ApiLineup {

    private ApiHomeAway home = new ApiHomeAway();

    private ApiHomeAway away = new ApiHomeAway();

}
