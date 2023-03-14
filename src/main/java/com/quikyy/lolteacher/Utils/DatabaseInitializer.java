package com.quikyy.lolteacher.Utils;

import com.quikyy.lolteacher.Service.Helper.ChampionsDAOHelperService;
import com.quikyy.lolteacher.Service.Helper.ParTypesDAOHelperService;
import com.quikyy.lolteacher.Service.Helper.TagsDAOHelperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer implements CommandLineRunner {

	@Autowired
	private ChampionsDAOHelperService championsHelperService;

	@Autowired
	private TagsDAOHelperService tagsDAOHelperService;

	@Autowired
	private ParTypesDAOHelperService parTypesDAOHelperService;


	private final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

	@Override
	public void run(String... args) throws Exception {
		tagsDAOHelperService.saveAllTags();;
		Thread.sleep(1000);

		parTypesDAOHelperService.saveAllParTypes();
		Thread.sleep(1000);

		championsHelperService.saveAllChampions();
		Thread.sleep(1000);
	}
}
