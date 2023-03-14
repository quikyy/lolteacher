package com.quikyy.lolteacher.Model.DTO.Champion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChampionStatsDTO {
	private int hp;
	private int hpPerLevel;
	private int moveSpeed;
	private int armor;
	private double armorPerLevel;
	private int magicResist;
	private double magicResistPerLevel;
	private int attackDamage;
	private double attackDamagePerLevel;
	private double attackSpeed;
	private double attackSpeedPerLevel;
	private int attackRange;
}
