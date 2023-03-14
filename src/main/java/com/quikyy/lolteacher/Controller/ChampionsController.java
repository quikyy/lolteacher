package com.quikyy.lolteacher.Controller;

import com.quikyy.lolteacher.LolHttpClient.Language;
import com.quikyy.lolteacher.Model.DTO.Champion.ChampionDTO;
import com.quikyy.lolteacher.Service.Implementation.ChampionsDAOService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionsController {

	@Autowired
	private ChampionsDAOService championsDAOService;

	@GetMapping(path = "/restapi/champions/{championName}")
	@ResponseBody
	@Transactional
	public ResponseEntity<ChampionDTO> getChampion(@PathVariable("championName") String championName,
			                                       @RequestParam(value = "lang", required = false, defaultValue = "en_US") Language lang)
												   throws IOException, InterruptedException {
		if (lang != Language.en_US && lang != Language.pl_PL) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		ChampionDTO response = championsDAOService.getChampionDTO(lang, championName);
		if (response == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
