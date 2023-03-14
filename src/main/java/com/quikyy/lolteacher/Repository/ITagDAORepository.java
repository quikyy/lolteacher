package com.quikyy.lolteacher.Repository;

import com.quikyy.lolteacher.Model.DAO.TagDAO;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagDAORepository extends PagingAndSortingRepository<TagDAO, BigInteger> {

	@Query(value = "SELECT * from tags WHERE name_english = :tagName", nativeQuery = true)
	TagDAO getTag(@Param("tagName") String tagName);
}
