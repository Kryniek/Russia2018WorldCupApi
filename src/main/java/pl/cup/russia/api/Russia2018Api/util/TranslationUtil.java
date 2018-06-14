package pl.cup.russia.api.Russia2018Api.util;

import java.util.ArrayList;
import java.util.List;

import pl.cup.russia.api.Russia2018Api.model.Match;

import static pl.cup.russia.api.Russia2018Api.constants.CountryConstants.ENGLISH_TO_POLISH_COUNTRY_NAMES;

public class TranslationUtil {

	public static List<String> getPolishCountryNames(List<String> englishCountryNames) {
		List<String> translatedCountryNames = new ArrayList<>();

		for (String name : englishCountryNames)
			translatedCountryNames.add(getPolishCountryName(name));

		return translatedCountryNames;
	}

	public static void translateMatchesCountryNamesToPolish(List<Match> matches) {
		matches.forEach(match -> {
			TranslationUtil.translateMatchCountryNameToPolish(match);
		});
	}

	public static void translateMatchCountryNameToPolish(Match match) {
		match.setHometeamName(getPolishCountryName(match.getHometeamName()));
		match.setAwayteamName(getPolishCountryName(match.getAwayteamName()));
	}

	public static String getPolishCountryName(String englishCountryName) {
		return ENGLISH_TO_POLISH_COUNTRY_NAMES.get(englishCountryName);
	}

}
