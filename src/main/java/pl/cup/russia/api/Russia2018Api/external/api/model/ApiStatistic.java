package pl.cup.russia.api.Russia2018Api.external.api.model;

import lombok.Data;
import pl.cup.russia.api.Russia2018Api.external.api.enums.ApiStatisticType;

@Data
public class ApiStatistic {

    private ApiStatisticType type;

    private Integer home;

    private Integer away;

}
