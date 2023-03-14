package com.quikyy.lolteacher.Repository;

import com.quikyy.lolteacher.Model.DAO.ChampionDAO;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IChampionsDAORepository extends PagingAndSortingRepository<ChampionDAO, BigInteger> {

	@Query(value = "SELECT * FROM champions WHERE slug_name = :championSlug", nativeQuery = true)
	ChampionDAO getChampionBySlug(@Param("championSlug") String championSlug);
}
