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
		List<String> tags = new ArrayList<>();
		tags.add("Fighter");
		tags.add("Support");
		tags.add("Mage");
		tags.add("Assassin");
		tags.add("Tank");
		tags.add("Marksman");
		tags.forEach(tag -> tagDAORepo.save(new TagDAO(tag)));
		logger.info("[RESTAPI] Tags saved.");
	}
}
