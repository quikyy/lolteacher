package com.quikyy.lolteacher.Model.DTO.Champion;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChampionDTO {
	private String name;
	private String slug;
	private String avatarUrl;
	private String splashUrl;
	private String title;
	private String lore;
	private List<TagDTO> tags;
	private ChampionStatsDTO stats;
	private List<ChampionSkinDTO> skins;
}
