/**
 * 
 */
package org.axisgroup.base.services;

import java.util.Map;

import org.axisgroup.base.dao.AccountDAO;
import org.axisgroup.base.dao.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author deepakpokhrel
 *
 */
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountDAO accountDAO;
	
	@Override
	public Map<String, Object> returnMultipleResultSet() {
		// TODO Auto-generated method stub
		return accountDAO.returnMultipleResultSet();

	}

}
