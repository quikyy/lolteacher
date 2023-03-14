package com.quikyy.lolteacher.Service.Interface;

import com.fasterxml.jackson.databind.JsonNode;

import com.quikyy.lolteacher.LolHttpClient.Language;
import com.quikyy.lolteacher.Model.DAO.ChampionDAO;
import com.quikyy.lolteacher.Model.DAO.TagDAO;
import com.quikyy.lolteacher.Model.DTO.Champion.TagDTO;
import java.util.List;
import java.util.Set;

public interface ITagsDAOService {
	TagDAO getTag(String tagName);
	Set<TagDAO> parseTagsNodeToDAO(JsonNode tagsNode);
	List<TagDTO> mapTagsDaoToTagsDto(Language language, ChampionDAO championDAO);
}
