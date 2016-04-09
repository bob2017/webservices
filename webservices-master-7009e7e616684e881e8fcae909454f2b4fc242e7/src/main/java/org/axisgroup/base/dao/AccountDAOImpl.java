package org.axisgroup.base.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class AccountDAOImpl implements AccountDAO{


	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Map<String, Object> returnMultipleResultSet() {
		
		// TO use SimpleJdbcCall please un-comment below
		//return useSimpleJdbcCall();
		// TO use CallableStatement please un-comment below
		//useCallableStatement();
		
		
		// TO use CallableStatementCreator below is the code
		SqlParameter email = new SqlParameter(Types.VARCHAR);
		SqlOutParameter userName = new SqlOutParameter("userid", Types.VARCHAR);
		SqlOutParameter firstName = new SqlOutParameter("firstname", Types.VARCHAR);
		SqlOutParameter lastName = new SqlOutParameter("lastname", Types.VARCHAR);

		
		
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(email);
		paramList.add(userName);	
		paramList.add(firstName);
		paramList.add(lastName);

		final String procedureCall = "{call getAccountByEmail(?, ?, ?, ?)}";
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

					@Override
					public CallableStatement createCallableStatement(Connection connection) throws SQLException {

						CallableStatement callableStatement = connection.prepareCall(procedureCall);
						callableStatement.setString(1, "acid@yourdomain.com");
						callableStatement.registerOutParameter(2, Types.VARCHAR);
						callableStatement.registerOutParameter(3, Types.VARCHAR);
						callableStatement.registerOutParameter(4, Types.VARCHAR);
						return callableStatement;

					}
				}, paramList);
		//System.out.println("Return out value:"+resultMap.get("firstName"));
		return resultMap;
	}

	private void useCallableStatement() {
		final String procedureCall = "{call SampleProcedure(?, ?)}";
		Connection connection = null;
		Map<String, Object> result = new HashMap<String, Object>();
		try {

			// Get Connection from dataSource
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Java");
			callableSt.registerOutParameter(2, Types.VARCHAR);
			
			//Call Stored Procedure
			callableSt.executeUpdate();
			System.out.println("Out paramter value: "+callableSt.getString(2));					

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	private Map<String, Object> useSimpleJdbcCall() {
		
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("SampleProcedure");

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("firstName", "Java");
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		
		
		 Iterator<Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
		        String key = (String) entry.getKey();
		        Object value = (Object) entry.getValue();
		        System.out.println("Key: "+key);
		        System.out.println("Value: "+value);
		    }
		return simpleJdbcCallResult;
	}



}
