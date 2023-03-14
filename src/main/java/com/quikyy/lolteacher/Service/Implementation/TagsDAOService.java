package com.quikyy.lolteacher.Service.Implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.quikyy.lolteacher.Model.DAO.TagDAO;
import com.quikyy.lolteacher.Repository.ITagDAORepository;
import com.quikyy.lolteacher.Service.Interface.ITagsDAOService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagsDAOService implements ITagsDAOService {

	@Autowired
	private ITagDAORepository tagDAORepo;

	@Override
	public TagDAO getTag(String tagName) {
		TagDAO tag = tagDAORepo.getTag(tagName);
		if (tag == null) {
			return null;
		}
		return tag;
	}

	@Override
	public Set<TagDAO> parseTagsNodeToDAO(JsonNode tagsNode) {
		if(tagsNode == null) {
			return null;
		}
		Set<TagDAO> tags = new HashSet<>();
		tagsNode.forEach(tagNode -> {
			TagDAO tagDAO = tagDAORepo.getTag(tagNode.asText());
			if(tagDAO == null) {
				return;
			}
			tags.add(tagDAO);

		});
		return tags;
	}


}
