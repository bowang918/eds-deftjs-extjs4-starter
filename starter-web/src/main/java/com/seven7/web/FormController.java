package com.seven7.web;


import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import ch.ralscha.extdirectspring.annotation.ExtDirectMethod;
import ch.ralscha.extdirectspring.annotation.ExtDirectMethodType;
import ch.ralscha.extdirectspring.bean.ExtDirectFormPostResult;

import com.seven7.domain.FormBean;
import com.seven7.service.FormLoadService;
import com.seven7.service.FormSubmitService;

@Controller
public class FormController {

	@Autowired
	private FormLoadService formLoadService;
	
	@Autowired
	private FormSubmitService formSubmitService;

	@ExtDirectMethod
	public String getRemark() {
		return "Used Heap: " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() + " bytes";
	}

	@ExtDirectMethod(ExtDirectMethodType.FORM_LOAD)
	public FormBean getFormData() {
		OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

		FormBean bean = new FormBean();
		bean.setAvailableProcessors(osBean.getAvailableProcessors());
		bean.setOsName(osBean.getName());
		bean.setOsVersion(osBean.getVersion());
		return bean;
	}
	
	@ExtDirectMethod(ExtDirectMethodType.FORM_POST)
	public ExtDirectFormPostResult handleFormSubmit(FormBean bean, MultipartFile screenshot) throws IOException {
		ExtDirectFormPostResult result = new ExtDirectFormPostResult();
		result.addResultProperty("response", formSubmitService.handleFormSubmit(bean, screenshot.getContentType(),screenshot.getOriginalFilename(),screenshot.getSize(),screenshot.getInputStream()));
		return result;
	}
	
	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder) throws Exception {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		CustomDateEditor editor = new CustomDateEditor(df, true);
		binder.registerCustomEditor(Date.class, editor);
	}
}
