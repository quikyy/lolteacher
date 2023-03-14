package com.quikyy.lolteacher.Controller;

import com.quikyy.lolteacher.Service.Implementation.ChampionsDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionsController {

	@Autowired
	private ChampionsDAOService championsDAOService;

	@GetMapping(path = "/restapi/champions/{championSlug}")
	@ResponseBody
	@Transactional
	public ResponseEntity<String> getChampionBySlug(@PathVariable("championSlug") String championSlug) {
		if (championSlug == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
