package com.example.assignment.component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.assignment.exception.InvalidInputFormatException;

@Component
public class EatingUtility {

	private static final Logger logger = LoggerFactory.getLogger(EatingUtility.class);

	public Integer perform(InputStream is) {
		// Total allowable time in seconds
		Integer time = null;

		// Total number of items on menu
		Integer items = null;

		// Time required to eat an item
		Integer eatingTimes[] = null;

		// Degree of satisfaction
		Integer degreeOfsatisfaction[] = null;

		try (Scanner s = new Scanner(is)) {
			time = s.nextInt();
			items = s.nextInt();

			eatingTimes = new Integer[items];
			degreeOfsatisfaction = new Integer[items];

			for (Integer i = 0; i < items; i++) {
				degreeOfsatisfaction[i] = s.nextInt();
				eatingTimes[i] = s.nextInt();
			}
		} catch (Throwable e) {
			throw new InvalidInputFormatException(e);
		}

		return perform(time, eatingTimes, degreeOfsatisfaction, items);
	}
	// Gordan best satisfaction /performance on the act of eating
	private Integer perform(Integer timelimit, Integer[] eatingTimes, Integer[] degreeOfSatisfaction, Integer menuItemCount) {
		
		if(menuItemCount != eatingTimes.length || menuItemCount != degreeOfSatisfaction.length) {
			throw new InvalidInputFormatException();
		}
		
		Integer i , w;
		Integer satisfaction[][] = new Integer[menuItemCount+1][timelimit+1];
		
		for(i=0;i<=menuItemCount;i++) {
			for(w=0;w<=timelimit;w++) {
				if(i==0||w==0) 
					satisfaction[i][w]=0;
				else if(eatingTimes[i-1]<=w)
					satisfaction[i][w] = Math.max(
							degreeOfSatisfaction[i-1]+satisfaction[i-1][w-eatingTimes[i-1]],satisfaction[i-1][w] );
				else
					satisfaction[i][w]=satisfaction[i-1][w];
			}
		}
		
		String text = String.format(
				"\nInput: \n\tTime Limit: %s\n\tEating Times: %s\n\tDegree Of Satisfaction: %s\n\tMenu Item Count:%s\nOutput: \n\tMax Sat. Degree:%s\n",
				timelimit, Arrays.toString(eatingTimes), Arrays.toString(degreeOfSatisfaction), menuItemCount,
				satisfaction[menuItemCount][timelimit]);
		logger.debug(text);
		return satisfaction[menuItemCount][timelimit];
	}

}
