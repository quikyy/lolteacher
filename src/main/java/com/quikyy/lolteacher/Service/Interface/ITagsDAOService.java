package com.quikyy.lolteacher.Service.Interface;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.quikyy.lolteacher.Model.DAO.TagDAO;
import java.util.Set;

public interface ITagsDAOService {
	TagDAO getTag(String tagName);

	Set<TagDAO> parseTagsNodeToDAO(JsonNode tagsNode);
}
