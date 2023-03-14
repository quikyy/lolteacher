package com.quikyy.lolteacher.LolHttpClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LolHttpClient {

	private static final String apiVersion = "13.5.1";

	private static final Logger logger = LoggerFactory.getLogger(LolHttpClient.class);

	public static JsonNode fetchChampionsByLanguage(Language language) throws IOException, InterruptedException {
		String url = getChampionsByLanguage(language);
		HttpClient httpClient = HttpClient.newBuilder().build();
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.GET()
				.build();

		HttpResponse<String> httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());

		if (httpResponse.statusCode() != 200) {
			logger.warn("[LolHttpClient] Response code: " + httpResponse.statusCode());
			return null;
		}

		ObjectMapper om = new ObjectMapper();
		JsonNode champions = om.readTree(httpResponse.body());

		if (champions == null) {
			logger.warn("[LolHttpClient] CHAMPIONS NODE for lang" + language + " is null.");
			return null;
		}
		return champions;
	}

	private static String getChampionsByLanguage(Language language) {
		return "http://ddragon.leagueoflegends.com/cdn/"+ apiVersion + "/data/" + language + "/champion.json";
	}

	public static JsonNode fetchChampionDetailsByLanguageAndChampionSlug(Language language, String championSlug) throws IOException, InterruptedException {
		String url = getChampionByLanguage(language, championSlug);
		HttpClient httpClient = HttpClient.newBuilder().build();
		HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.GET()
				.build();

		HttpResponse<String> httpResponse = httpClient.send(httpRequest, BodyHandlers.ofString());

		if (httpResponse.statusCode() != 200) {
			logger.warn("[LolHttpClient] Response code: " + httpResponse.statusCode());
			return null;
		}

		ObjectMapper om = new ObjectMapper();
		JsonNode champion = om.readTree(httpResponse.body());

		if (champion == null) {
			logger.warn("[LolHttpClient] CHAMPION NODE for " + championSlug + " ,lang" + language + " is null.");
			return null;
		}
		return champion;
	}

	private static String getChampionByLanguage(Language language, String championSlug) {
		return "http://ddragon.leagueoflegends.com/cdn/"+ apiVersion + "/data/" + language + "/champion/" + championSlug + ".json";
	}
}
