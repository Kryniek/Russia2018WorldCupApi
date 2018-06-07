package pl.cup.russia.api.Russia2018Api.external.api.model;

import lombok.Data;

@Data
public class ApiScorer {

    private String time; // string cause it is returned as minute and ' sign

    private String homeScorer;

    private String score; // returned as 0 - 1

    private String awayScorer;

}
