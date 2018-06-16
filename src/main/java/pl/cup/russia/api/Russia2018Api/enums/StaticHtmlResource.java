package pl.cup.russia.api.Russia2018Api.enums;

public enum StaticHtmlResource {
    // @formatter:off
    LOGIN("login"),
    REGISTER("register"),
    WORLD_CUP_WINNER("worldCupWinner"),
    GROUPS_WINNERS("groupsWinners"),
    MATCHES("matches"),
    HOME("home"),
    POINTS("points"),
    BET("bet"),
    RESULTS("results");
    //@formatter:on

    private String value;

    private StaticHtmlResource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getKebabCasedRedirectValue() {
        String redirect = "redirect:/";

        if (HOME.equals(this)) {
            return redirect;
        }

        if (WORLD_CUP_WINNER.equals(this)) {
            return redirect.concat("world-cup-winner");
        }

        if (GROUPS_WINNERS.equals(this)) {
            return redirect.concat("groups-winners");
        }

        return redirect.concat(this.value);
    }

    @Override
    public String toString() {
        return value;
    }
}