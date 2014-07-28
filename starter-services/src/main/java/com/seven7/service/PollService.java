package com.seven7.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class PollService {

	private final static Random random = new Random();

	public int[] currentTime() {
		int[] result = new int[12];

		for (int i = 0; i < result.length; i++) {
			result[i] = random.nextInt(200);
		}

		return result;
	}

}