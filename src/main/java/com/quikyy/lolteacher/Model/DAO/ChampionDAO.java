package com.quikyy.lolteacher.Model.DAO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Table(name = "champions")
public class ChampionDAO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "BIGINT")
	private BigInteger id;

	@Column(name = "avatar_url")
	private String avatarUrl;

	@Column(name = "splash_url")
	private String splashUrl;

	@Column(name = "in_game_name")
	private String name;

	@Column(name = "slug_name")
	private String slug;

	@Column(name = "title_english")
	private String titleEnglish;

	@Column(name = "title_polish")
	private String titlePolish;

	@Column(name = "hp")
	private int hp;

	@Column(name = "hp_pr_level")
	private int hpPerLevel;

	@Column(name = "move_speed")
	private int moveSpeed;

	@Column(name = "armor")
	private int armor;

	@Column(name = "armor_per_level", columnDefinition = "DOUBLE")
	private double armorPerLevel;

	@Column(name = "magic_resist")
	private int magicResist;

	@Column(name = "magic_resist_per_level", columnDefinition = "DOUBLE")
	private double magicResistPerLevel;

	@Column(name = "attack_damage")
	private int attackDamage;

	@Column(name = "attack_damage_per_level", columnDefinition = "DOUBLE")
	private double attackDamagePerLevel;

	@Column(name = "attack_speed", columnDefinition = "DOUBLE")
	private double attackSpeed;

	@Column(name = "attack_speed_per_level", columnDefinition = "DOUBLE")
	private double attackSpeedPerLevel;

	@Column(name = "attack_range")
	private int attackRange;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "partype_id")
	private ParTypeDAO parType;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id")
	private Set<TagDAO> tags = new HashSet<>();
}
