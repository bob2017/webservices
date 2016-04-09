package org.axisgroup.base.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.axisgroup.base.services.AccountService;
import org.axisgroup.base.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SpringMVCController {
	
	@Autowired 
	AccountService accountService;
	
	private static final Logger logger = Logger.getLogger(SpringMVCController.class);
	
	@RequestMapping(value = "/returnMultipleResultSet")
	public @ResponseBody Map<String, Object> returnMultipleResultSet() {
		
		logger.info("Log4j info is working");
		return accountService.returnMultipleResultSet();
	}


}
