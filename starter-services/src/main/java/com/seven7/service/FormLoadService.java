package com.seven7.service;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

import org.springframework.stereotype.Service;

import com.seven7.domain.FormBean;

@Service
public class FormLoadService {

	public String getRemark() {
		return "Used Heap: " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() + " bytes";
	}

	public FormBean getFormData() {
		OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

		FormBean bean = new FormBean();
		bean.setAvailableProcessors(osBean.getAvailableProcessors());
		bean.setOsName(osBean.getName());
		bean.setOsVersion(osBean.getVersion());
		return bean;
	}

}
