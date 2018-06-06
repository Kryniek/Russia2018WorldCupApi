package pl8.cup.russia.api.Russia2018Api.external.api.model;

import lombok.Data;
import pl8.cup.russia.api.Russia2018Api.external.api.enums.ApiStatisticType;

@Data
public class ApiStatistics {

    private ApiStatisticType type;

    private Integer home;

    private Integer away;

}
