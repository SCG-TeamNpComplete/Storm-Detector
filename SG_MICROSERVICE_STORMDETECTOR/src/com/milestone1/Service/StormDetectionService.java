package com.milestone1.Service;

import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class StormDetectionService {

	public static int index = 0;

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		StormDetectionService.index = index;
	}

}
