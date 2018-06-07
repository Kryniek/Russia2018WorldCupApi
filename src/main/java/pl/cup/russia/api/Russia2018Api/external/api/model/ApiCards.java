package pl.cup.russia.api.Russia2018Api.external.api.model;


import lombok.Data;
import pl.cup.russia.api.Russia2018Api.external.api.enums.ApiCard;

@Data
public class ApiCards {

    private String time;

    private String homeFault;

    private ApiCard card;

    private String awayFault;

}
