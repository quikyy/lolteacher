package com.quikyy.lolteacher.Service.Implementation;

import com.quikyy.lolteacher.Model.DAO.ChampionDAO;
import com.quikyy.lolteacher.Repository.IChampionsDAORepository;
import com.quikyy.lolteacher.Service.Interface.IChampionsDAOService;
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
	public ChampionDAO getChampion(String championSlug) {
		return championsDAORepo.getChampionBySlug(championSlug);
	}
}
