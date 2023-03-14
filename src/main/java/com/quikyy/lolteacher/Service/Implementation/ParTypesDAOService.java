package com.quikyy.lolteacher.Service.Implementation;

import com.quikyy.lolteacher.Model.DAO.ParTypeDAO;
import com.quikyy.lolteacher.Repository.IParTypesDAORepository;
import com.quikyy.lolteacher.Service.Interface.IParTypesDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParTypesDAOService implements IParTypesDAOService {

	@Autowired
	private IParTypesDAORepository parTypesDAORepo;

	@Override
	public ParTypeDAO getParTypeByName(String name) {
		ParTypeDAO parTypeDAO = parTypesDAORepo.getPartypeByName(name);
		if(parTypeDAO == null) {
			return null;
		}
		return parTypeDAO;
	}
}
