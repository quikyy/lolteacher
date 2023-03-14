package com.quikyy.lolteacher.Service.Helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.quikyy.lolteacher.LolHttpClient.Language;
import com.quikyy.lolteacher.LolHttpClient.LolHttpClient;
import com.quikyy.lolteacher.Model.DAO.ChampionDAO;
import com.quikyy.lolteacher.Repository.IChampionsDAORepository;
import com.quikyy.lolteacher.Service.Implementation.ParTypesDAOService;
import com.quikyy.lolteacher.Service.Implementation.TagsDAOService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionsDAOHelperService {

	@Autowired
	private IChampionsDAORepository championsDAORepo;

	@Autowired
	private TagsDAOService tagsDAOService;

	@Autowired
	private ParTypesDAOService parTypesDAOService;

	private final Logger logger = LoggerFactory.getLogger(ChampionsDAOHelperService.class);

	public void saveAllChampions() throws IOException, InterruptedException {
		List<ObjectNode> championsObjects = fetchAllChampions();
		if (championsObjects == null) {
			return;
		}
		List<ChampionDAO> championDAOs = new ArrayList<>();
		championsObjects.forEach(championNode -> {

			ChampionDAO championDAO = parseObjectNodeToChampionDAO(championNode);
			championDAOs.add(championDAO);
		});
		championsDAORepo.saveAll(championDAOs);
		logger.info("[RESTAPI] Champions saved.");
	}

	private ChampionDAO parseObjectNodeToChampionDAO(ObjectNode championNode) {
		ChampionDAO championDAO = new ChampionDAO();
		championDAO.setName(championNode.get("name").asText());
		championDAO.setSlug(championNode.get("slug").asText());
		championDAO.setAvatarUrl(championNode.get("avatarUrl").asText());
		championDAO.setSplashUrl(championNode.get("splashUrl").asText());

		championDAO.setTitleEnglish(championNode.get("titleEnglish").asText());
		championDAO.setTitlePolish(championNode.get("titlePolish").asText());

		championDAO.setHp(championNode.get("hp").asInt());
		championDAO.setHpPerLevel(championNode.get("hpPerLevel").asInt());

		championDAO.setMoveSpeed(championNode.get("movespeed").asInt());

		championDAO.setArmor(championNode.get("armor").asInt());
		championDAO.setArmorPerLevel(championNode.get("armorPerLevel").asDouble());

		championDAO.setMagicResist(championNode.get("magicResist").asInt());
		championDAO.setMagicResistPerLevel(championNode.get("magicResistPerLevel").asDouble());

		championDAO.setAttackDamage(championNode.get("attackDamage").asInt());
		championDAO.setAttackDamagePerLevel(championNode.get("attackDamagePerLevel").asDouble());

		championDAO.setAttackSpeed(championNode.get("attackSpeed").asDouble());
		championDAO.setAttackSpeedPerLevel(championNode.get("attackSpeedPerLevel").asDouble());

		championDAO.setAttackRange(championNode.get("attackRange").asInt());

		String parTypeName = championNode.get("partype").asText();
		championDAO.setParType(parTypesDAOService.getParTypeByName(parTypeName));

		JsonNode tagsNode = championNode.get("tags");
		championDAO.setTags(tagsDAOService.parseTagsNodeToDAO(tagsNode));
		return championDAO;
	}

	private List<ObjectNode> fetchAllChampions() throws IOException, InterruptedException {
		List<ObjectNode> champions = new ArrayList<>();
		JsonNode championsNodesEnglish = fetchChampionsByLanguage(Language.en_US);
		JsonNode championsNodesPolish = fetchChampionsByLanguage(Language.pl_PL);

		if(championsNodesEnglish == null) {
			return null;
		}



		for(JsonNode championEnglishNode : championsNodesEnglish) {
			String championSlug = championEnglishNode.get("id").asText();

			ObjectNode championNode = new ObjectNode(JsonNodeFactory.instance);
			championNode.put("name", championEnglishNode.get("name").asText());
			championNode.put("slug", championSlug);
			championNode.put("avatarUrl", getChampionAvatarUrl(championSlug));
			championNode.put("splashUrl", getChampionSplashUrl(championSlug));

			championNode.put("titleEnglish", championEnglishNode.get("title").asText());

			championNode.put("hp", championEnglishNode.get("stats").get("hp").asInt());
			championNode.put("hpPerLevel", championEnglishNode.get("stats").get("hpperlevel").asInt());
			championNode.put("movespeed", championEnglishNode.get("stats").get("movespeed").asInt());

			championNode.put("armor", championEnglishNode.get("stats").get("armor").asInt());
			championNode.put("armorPerLevel", championEnglishNode.get("stats").get("armorperlevel").asDouble());

			championNode.put("magicResist", championEnglishNode.get("stats").get("spellblock").asInt());
			championNode.put("magicResistPerLevel", championEnglishNode.get("stats").get("spellblockperlevel").asDouble());

			championNode.put("attackDamage", championEnglishNode.get("stats").get("attackdamage").asInt());
			championNode.put("attackDamagePerLevel", championEnglishNode.get("stats").get("attackdamageperlevel").asDouble());

			championNode.put("attackSpeed", championEnglishNode.get("stats").get("attackspeed").asDouble());
			championNode.put("attackSpeedPerLevel", championEnglishNode.get("stats").get("attackspeedperlevel").asDouble());

			championNode.put("attackRange", championEnglishNode.get("stats").get("attackrange").asInt());

			championNode.put("partype", championEnglishNode.get("partype").asText());
			championNode.putArray("tags").addAll(getChampionTags(championEnglishNode));

			if (championsNodesPolish == null) {
				champions.add(championNode);
				break;
			}
			JsonNode championPolishNode = championsNodesPolish.get(championSlug);
			championNode.put("titlePolish", championPolishNode.get("title").asText());

			champions.add(championNode);
		}
		return champions;
	}

	private JsonNode fetchChampionsByLanguage(Language language) throws IOException, InterruptedException {
		JsonNode champions = LolHttpClient.fetchChampionsByLanguage(language);
		if (champions == null) {
			logger.warn("[RESTAPI] Champions for " + language + " are null.");
			return null;
		}
		return champions.get("data");
	}

	private JsonNode fetchChampionDetails(Language language, String championSlug) throws IOException, InterruptedException {
		JsonNode champion = LolHttpClient.fetchChampionDetailsByLanguageAndChampionSlug(language, championSlug);
		if(champion == null) {
			logger.warn("[RESTAPI] " + championSlug + ", lang " + language + " is null.");
			return null;
		}
		return champion.get("data").get(championSlug);
	}

	private String getChampionAvatarUrl(String championSlug) {
		return "http://ddragon.leagueoflegends.com/cdn/13.5.1/img/champion/" + championSlug + ".png";
	}

	private String getChampionSplashUrl(String championSlug) {
		return "http://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + championSlug + "_0.jpg";
	}

	private ArrayNode getChampionTags(JsonNode championEnglish) {
		JsonNode tags = championEnglish.get("tags");
		if(tags == null) {
			return null;
		}
		ArrayNode tagsArrayNode = new ArrayNode(JsonNodeFactory.instance);
		tags.forEach(tag -> tagsArrayNode.add(tag.asText()));
		return tagsArrayNode;
	}
}
