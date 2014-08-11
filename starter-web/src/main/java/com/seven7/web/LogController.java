package com.seven7.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;

@Controller
public class LogController {

	private final static Logger log = LogManager.getLogger(LogController.class);

	@ExtDirectMethod
	public void debug(String msg) {
		log.debug(msg);
	}

	@ExtDirectMethod
	public void info(String msg) {
		log.info(msg);
	}

	@ExtDirectMethod
	public void warn(String msg) {
		log.warn(msg);
	}

	@ExtDirectMethod
	public void error(String msg) {
		log.error(msg);
	}
}
