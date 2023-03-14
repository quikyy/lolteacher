package com.quikyy.lolteacher.Model.DAO;

import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tags")
public class TagDAO {

	@Id
	@Column(name = "id", columnDefinition = "BIGINT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	@Column(name = "name_english")
	private String nameEnglish;

	@Column(name = "name_polish")
	private String namePolish;

	public TagDAO(String nameEnglish, String namePolish) {
		this.nameEnglish = nameEnglish;
		this.namePolish = namePolish;
	}
}
