package com.seven7.web;

import org.springframework.stereotype.Controller;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;

@Controller
public class HeartbeatController {

	@ExtDirectMethod(value = ExtDirectMethodType.POLL, event = "heartbeat")
	public void heartBeat() {
		
	}

}
