package com.quikyy.lolteacher.Service.Interface;

import com.quikyy.lolteacher.Model.DAO.ChampionDAO;

public interface IChampionsDAOService {

	ChampionDAO getChampion(String championSlug);
}
