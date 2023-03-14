package com.quikyy.lolteacher.Service.Implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.quikyy.lolteacher.LolHttpClient.Language;
import com.quikyy.lolteacher.LolHttpClient.LolHttpClient;
import com.quikyy.lolteacher.Model.DAO.ChampionDAO;
import com.quikyy.lolteacher.Model.DTO.Champion.ChampionDTO;
import com.quikyy.lolteacher.Model.DTO.Champion.ChampionSkinDTO;
import com.quikyy.lolteacher.Model.DTO.Champion.ChampionStatsDTO;
import com.quikyy.lolteacher.Repository.IChampionsDAORepository;
import com.quikyy.lolteacher.Service.Interface.IChampionsDAOService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionsDAOService implements IChampionsDAOService {

	@Autowired
	private IChampionsDAORepository championsDAORepo;

	@Autowired
	private TagsDAOService tagsDAOService;

	private final Logger logger = LoggerFactory.getLogger(ChampionsDAOService.class);

	@Override
	public ChampionDAO getChampionDAO(String championSlug) {
		return championsDAORepo.getChampionBySlug(championSlug);
	}

	@Override
	public ChampionDTO getChampionDTO(Language language, String championName) throws IOException, InterruptedException {
		ChampionDAO championDAO = getChampionDAO(championName);
		if (championDAO == null) {
			return null;
		}
		return mapChampionDaoToChampionDto(language, championDAO);
	}

	private ChampionDTO mapChampionDaoToChampionDto(Language language, ChampionDAO championDAO) throws IOException, InterruptedException {
		ChampionDTO championDTO = new ChampionDTO();
		championDTO.setName(championDAO.getName());
		championDTO.setSlug(championDAO.getSlug());
		championDTO.setAvatarUrl(championDAO.getAvatarUrl());
		championDTO.setSplashUrl(championDAO.getSplashUrl());
		championDTO.setStats(mapChampionDaoToChampionStatsDto(championDAO));
		championDTO.setTags(tagsDAOService.mapTagsDaoToTagsDto(language, championDAO));

		if (language == Language.en_US) {
			championDTO.setTitle(championDAO.getTitleEnglish());
		} else if (language == Language.pl_PL) {
			championDTO.setTitle(championDAO.getTitlePolish());
		}

		JsonNode championDetailsNode = getChampionDetails(language, championDAO.getSlug());
		if (championDetailsNode == null) {
			return championDTO;
		}

		championDTO.setLore(championDetailsNode.get("lore").asText());
		championDTO.setSkins(mapChampionDetailsNodeToChampionSkinsDto(championDetailsNode, championDAO.getSlug()));
		return championDTO;
	}

	private ChampionStatsDTO mapChampionDaoToChampionStatsDto(ChampionDAO championDAO) {
		ChampionStatsDTO championStatsDTO = new ChampionStatsDTO();
		championStatsDTO.setHp(championDAO.getHp());
		championStatsDTO.setHpPerLevel(championDAO.getHpPerLevel());
		championStatsDTO.setMoveSpeed(championDAO.getMoveSpeed());
		championStatsDTO.setArmor(championDAO.getArmor());
		championStatsDTO.setArmorPerLevel(championDAO.getArmorPerLevel());
		championStatsDTO.setMagicResist(championDAO.getMagicResist());
		championStatsDTO.setMagicResistPerLevel(championDAO.getMagicResistPerLevel());
		championStatsDTO.setAttackDamage(championDAO.getAttackDamage());
		championStatsDTO.setAttackDamagePerLevel(championDAO.getAttackDamagePerLevel());
		championStatsDTO.setAttackSpeed(championDAO.getAttackSpeed());
		championStatsDTO.setAttackSpeedPerLevel(championDAO.getAttackSpeedPerLevel());
		championStatsDTO.setAttackRange(championDAO.getAttackRange());
		return championStatsDTO;
	}

	private List<ChampionSkinDTO> mapChampionDetailsNodeToChampionSkinsDto(JsonNode championDetailsNode, String championSlug) {
		List<ChampionSkinDTO> championSkinDTOs = new ArrayList<>();
		JsonNode skinsNode = championDetailsNode.get("skins");
		for (JsonNode skin : skinsNode) {
			int skinNumber = skin.get("num").asInt();
			ChampionSkinDTO championSkinDTO = new ChampionSkinDTO();
			championSkinDTO.setNumber(skinNumber);
			championSkinDTO.setName(skin.get("name").asText());
			championSkinDTO.setUrl(getSkinUrl(championSlug, skinNumber));
			championSkinDTOs.add(championSkinDTO);
		}
		return championSkinDTOs;
	}

	private String getSkinUrl(String championSlug, int num) {
		return "http://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + championSlug + "_" + num + ".jpg";
	}

	private JsonNode getChampionDetails(Language language, String championSlug) throws IOException, InterruptedException {
		JsonNode champion = LolHttpClient.fetchChampionDetailsByLanguageAndChampionSlug(language, championSlug);
		if(champion == null) {
			logger.warn("[RESTAPI] " + championSlug + ", lang " + language + " is null.");
			return null;
		}
		return champion.get("data").get(championSlug);
	}


}
