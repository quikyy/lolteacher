package com.quikyy.lolteacher.Service.Interface;

import com.quikyy.lolteacher.LolHttpClient.Language;
import com.quikyy.lolteacher.Model.DAO.ChampionDAO;
import com.quikyy.lolteacher.Model.DTO.Champion.ChampionDTO;
import java.io.IOException;

public interface IChampionsDAOService {

	ChampionDAO getChampionDAO(String championSlug);
	ChampionDTO getChampionDTO(Language language, String championName)
			throws IOException, InterruptedException;
}
