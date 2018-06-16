package pl.cup.russia.api.Russia2018Api.enums;

public enum  MatchStatus {

    HALF_TIME("HT"),
    FINAL_TIME("FT");

    private String value;

    MatchStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
