package pl.cup.russia.api.Russia2018Api.enums;

public enum DBCollections {

    LEAGUES("leagues"),
    MATCHES("matches"),
    BETS("bets"),
    USERS("users");

    private String value;

    DBCollections(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
