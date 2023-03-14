package com.quikyy.lolteacher.Repository;

import com.quikyy.lolteacher.Model.DAO.ChampionDAO;
import java.math.BigInteger;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChampionsDAORepository extends PagingAndSortingRepository<ChampionDAO, BigInteger> {

}
