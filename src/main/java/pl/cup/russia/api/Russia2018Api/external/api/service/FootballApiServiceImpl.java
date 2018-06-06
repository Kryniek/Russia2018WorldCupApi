package pl.cup.russia.api.Russia2018Api.external.api.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import pl.cup.russia.api.Russia2018Api.external.api.definition.FootballApiService;
import pl.cup.russia.api.Russia2018Api.external.api.enums.FootballApiAction;
import pl.cup.russia.api.Russia2018Api.external.api.util.builder.FootballApiUrlBuilder;
import pl.cup.russia.api.Russia2018Api.external.api.util.exception.service.footballApiService.base.UrlParseException;

@Service
public class FootballApiServiceImpl implements FootballApiService {

	@Override
	public JSONObject getLeaguesJSONObject() {
		try {
			URL url = new URL(new FootballApiUrlBuilder(FootballApiAction.GET_LEAGUES).withCountryId().build());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			Integer responseCode = connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
			throw new UrlParseException();
		}
		return null;
	}
}

//http://chillyfacts.com/java-send-http-getpost-request-and-read-json-response/

// public static void call_me() throws Exception {
// String url =
// "http://api.ipinfodb.com/v3/ip-city/?key=d64fcfdfacc213c7ddf4ef911dfe97b55e4696be3532bf8302876c09ebd06b&ip=74.125.45.100&format=json";
// URL obj = new URL(url);
// HttpURLConnection con = (HttpURLConnection) obj.openConnection();
// // optional default is GET
// con.setRequestMethod("GET");
// //add request header
// con.setRequestProperty("User-Agent", "Mozilla/5.0");
// int responseCode = con.getResponseCode();
// System.out.println("\nSending 'GET' request to URL : " + url);
// System.out.println("Response Code : " + responseCode);
// BufferedReader in = new BufferedReader(
// new InputStreamReader(con.getInputStream()));
// String inputLine;
// StringBuffer response = new StringBuffer();
// while ((inputLine = in.readLine()) != null) {
// response.append(inputLine);
// }
// in.close();
// //print in String
// System.out.println(response.toString());
// //Read JSON response and print
// JSONObject myResponse = new JSONObject(response.toString());
// System.out.println("result after Reading JSON Response");
// System.out.println("statusCode- "+myResponse.getString("statusCode"));
// System.out.println("statusMessage- "+myResponse.getString("statusMessage"));
// System.out.println("ipAddress- "+myResponse.getString("ipAddress"));
// System.out.println("countryCode- "+myResponse.getString("countryCode"));
// System.out.println("countryName- "+myResponse.getString("countryName"));
// System.out.println("regionName- "+myResponse.getString("regionName"));
// System.out.println("cityName- "+myResponse.getString("cityName"));
// System.out.println("zipCode- "+myResponse.getString("zipCode"));
// System.out.println("latitude- "+myResponse.getString("latitude"));
// System.out.println("longitude- "+myResponse.getString("longitude"));
// System.out.println("timeZone- "+myResponse.getString("timeZone"));
// }