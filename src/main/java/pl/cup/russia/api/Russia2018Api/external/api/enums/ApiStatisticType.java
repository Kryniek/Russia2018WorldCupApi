package pl.cup.russia.api.Russia2018Api.external.api.enums;

public enum ApiStatisticType {

    SHOTS_ON_TARGET("shots on target"),
    SHOTS_OFF_TARGET("shots off target"),
    POSSESSION("possession"),
    CORNERS("corners"),
    OFFSIDES("offsides"),
    FOULS("fouls"),
    YELLOW_CARDS("yellow cards"),
    GOAL_KICKS("goal kicks"),
    TREATMENTS("treatments");

    private String value;

    ApiStatisticType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ApiStatisticType parse(String value) {
        for (ApiStatisticType type : ApiStatisticType.values()) {
            if (type.getValue().equals(value))
                return type;
        }

        throw new IllegalArgumentException("There is no constant for " + value + " value.");
    }

}
