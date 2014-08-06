package com.seven7.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.seven7.domain.dto.FormBean;

@Service
public class FormSubmitService {

	public String handleFormSubmit(FormBean bean, String contentType, String originalFilename, long size, InputStream inputStream) {

		String resultString = "Server received: \n" + bean.toString();
		resultString += "\n";

		resultString += "ContentType: " + contentType + "\n";
		resultString += "Size: " + size + "\n";
		resultString += "Name: " + originalFilename;

		return resultString;
	}
}
