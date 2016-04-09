package org.axisgroup.base.services;

import java.util.Map;

import org.axisgroup.base.dao.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImpl implements IPersonService{
	
	@Autowired
	IPersonDAO transformDAO;

	@Override
	public Map<String, Object> returnMultipleResultSet() {
		return transformDAO.returnMultipleResultSet();
	}	

}
