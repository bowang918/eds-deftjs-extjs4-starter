package com.seven7.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;

@Controller
@PreAuthorize("isAuthenticated()")
public class HeartbeatController {

	@ExtDirectMethod(value = ExtDirectMethodType.POLL, event = "heartbeat")
	public void heartBeat() {
		
	}

}
