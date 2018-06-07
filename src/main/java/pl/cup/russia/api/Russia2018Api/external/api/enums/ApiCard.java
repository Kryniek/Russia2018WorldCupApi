package pl.cup.russia.api.Russia2018Api.external.api.enums;

public enum ApiCard {

    YELLOW_CARD("yellowcard"),
    RED_CARD("redcard");

    String value;

    ApiCard(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ApiCard parse(String value) {
        for(ApiCard card : ApiCard.values()) {
            if(card.getValue().equals(value))
                return card;
        }

        throw new IllegalArgumentException("There is no constant for " + value + " value.");
    }

}
