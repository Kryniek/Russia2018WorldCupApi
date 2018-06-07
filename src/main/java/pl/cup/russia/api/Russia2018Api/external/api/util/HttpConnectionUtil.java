package pl.cup.russia.api.Russia2018Api.external.api.util;

import static java.util.Objects.nonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;

import pl.cup.russia.api.Russia2018Api.external.api.enums.FootballApiAction;
import pl.cup.russia.api.Russia2018Api.external.api.util.builder.FootballApiUrlBuilder;
import pl.cup.russia.api.Russia2018Api.external.api.util.exception.util.httpConnectionUtil.ConnectionStatusCodeNotOKException;
import pl.cup.russia.api.Russia2018Api.external.api.util.exception.util.httpConnectionUtil.JSONParseException;
import pl.cup.russia.api.Russia2018Api.external.api.util.exception.util.httpConnectionUtil.UrlParseException;

public class HttpConnectionUtil {
	public static JSONArray getJSONArrayFromConnectionByUrl(String url) {
		JSONArray jSONArray = null;

		try {
			URL urlObject = new URL(new FootballApiUrlBuilder(FootballApiAction.GET_LEAGUES).withCountryId().build());
			HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

			validateResponseCode(HttpStatus.resolve(connection.getResponseCode()));

			jSONArray = new JSONArray(getResponseFromBuffer(new BufferedReader(new InputStreamReader(connection.getInputStream()))));
		} catch (IOException e) {
			throw new UrlParseException();
		} catch (JSONException e) {
			throw new JSONParseException();
		}

		return jSONArray;
	}

	private static void validateResponseCode(HttpStatus responseCode) {
		if (!HttpStatus.OK.equals(responseCode)) {
			throw new ConnectionStatusCodeNotOKException();
		}
	}

	private static String getResponseFromBuffer(BufferedReader buffer) throws IOException {
		StringBuffer response = new StringBuffer();

		for (String line = buffer.readLine(); nonNull(line); line = buffer.readLine()) {
			response.append(line);
		}

		buffer.close();

		return response.toString();
	}
}
