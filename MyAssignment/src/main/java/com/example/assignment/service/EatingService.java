package com.example.assignment.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assignment.component.EatingUtility;
import com.example.assignment.exception.InvalidInputSourceException;

@Service
public class EatingService {
	
	@Autowired
	EatingUtility eatingUtility;
	
	public Integer performWithRefPath(String refFilePath) {
		try {
			return performWithAbsolutePath(getClass().getClassLoader().getResource(refFilePath).getFile());
		} catch (NullPointerException e) {
			throw new InvalidInputSourceException(e);
		}
	}

	public Integer performWithAbsolutePath(String absoluteFilePath) {
		return perform(new File(absoluteFilePath));
	}

	public Integer perform(File file) {
		try (InputStream stream = new FileInputStream(file)) {
			return eatingUtility.perform(stream);
		} catch (IOException e) {
			throw new InvalidInputSourceException(e);
		}
	}
}
