package com.quikyy.lolteacher.Service.Helper;

import com.quikyy.lolteacher.Model.DAO.ParTypeDAO;
import com.quikyy.lolteacher.Repository.IParTypesDAORepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParTypesDAOHelperService {
	@Autowired
	private IParTypesDAORepository parTypesDAORepo;

	private final Logger logger = LoggerFactory.getLogger(ParTypesDAOHelperService.class);
	public void saveAllParTypes() {
		List<String> parTypes = new ArrayList<>();
		parTypes.add("Blood Well");
		parTypes.add("Mana");
		parTypes.add("Shield");
		parTypes.add("Rage");
		parTypes.add("Crimson Rush");
		parTypes.add("Flow");
		parTypes.add("Heat");
		parTypes.add("Energy");
		parTypes.add("Fury");
		parTypes.add("Grit");
		parTypes.add("Ferocity");
		parTypes.add("None");
		parTypes.forEach(parType -> parTypesDAORepo.save(new ParTypeDAO(parType)));
		logger.info("[RESTAPI] ParTypes saved.");
	}
}
