package com.quikyy.lolteacher.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChampionDTO {

	private String avatarUrl;
	private String splashUrl;

	private String name;
	private String slug;
}
