package com.quikyy.lolteacher.Repository;

import com.quikyy.lolteacher.Model.DAO.ParTypeDAO;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IParTypesDAORepository extends PagingAndSortingRepository<ParTypeDAO, BigInteger> {

	@Query(value = "SELECT * FROM partypes WHERE name = :parTypeName", nativeQuery = true)
	ParTypeDAO getPartypeByName(@Param("parTypeName") String parTypeName);
}
