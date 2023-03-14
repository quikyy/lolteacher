package com.quikyy.lolteacher.Service.Helper;

import com.quikyy.lolteacher.Model.DAO.TagDAO;
import com.quikyy.lolteacher.Repository.ITagDAORepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsDAOHelperService {

	@Autowired
	private ITagDAORepository tagDAORepo;

	private final Logger logger = LoggerFactory.getLogger(TagsDAOHelperService.class);

	public void saveAllTags() {
		List<TagDAO> tags = new ArrayList<>();
		tags.add(new TagDAO("Fighter", "Wojownik"));
		tags.add(new TagDAO("Support", "Wspierający"));
		tags.add(new TagDAO("Mage", "Mag"));
		tags.add(new TagDAO("Assassin", "Zabójca"));
		tags.add(new TagDAO("Tank", "Obrońca"));
		tags.add(new TagDAO("Marksman", "Strzelający"));
		tagDAORepo.saveAll(tags);
		logger.info("[RESTAPI] Tags saved.");
	}
}
