package com.quikyy.lolteacher.Service.Interface;

import com.quikyy.lolteacher.Model.DAO.ParTypeDAO;

public interface IParTypesDAOService {

	ParTypeDAO getParTypeByName(String name);
}
