package com.quikyy.lolteacher.Controller;

import com.quikyy.lolteacher.Service.Implementation.ChampionsDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionsController {

	@Autowired
	private ChampionsDAOService championsDAOService;


}
