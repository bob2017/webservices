package org.axisgroup.base.controller;


import java.util.Map;

import org.axisgroup.base.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class AccountController {
	@Autowired
	 AccountService accountService;
	
	@RequestMapping("/person")
	public Map<String, Object> getPersonDetail(@RequestParam(value = "id",required = false,
	                                                    defaultValue = "0") Integer id) {
		 
		return accountService.returnMultipleResultSet();
	}
}