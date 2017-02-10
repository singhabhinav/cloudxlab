package com.cloudxlab.nextword;

import java.util.ArrayList;
import java.util.List;

public class Top3 {

	List<String> top3(List<String> sortedStrings)
	{
		List<String> output = new ArrayList<String>();
		int currentCount = 0;
		String currentString = null;
		boolean changed = false;
 		for(int i=0; i < sortedStrings.size(); i++)
 		{
 			if(!sortedStrings.get(i).equals(currentString))
 			{
 				changed = true;
 				for(int j=0; j<output.size(); j++)
	 			{
 					String[] split = output.get(j).split(":");
 					String key = split[0];
 					int count = Integer.parseInt(split[1]);
 					if(count < currentCount)
 					{
 						output.add(j, sortedStrings.get(i) + ":" + currentCount);
 						if(output.size() > 3)
 							output.remove(output.size() - 1);
 					}
	 				//insert the currentString at right position
 					//remove the last one
	 			}
 				currentCount = 0;
 				currentString = sortedStrings.get(i);
 			}
 			else
 				currentCount++;
 		}
 		return output;

	}
	public static void main(String[] args) {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("a");
		arrayList.add("a");
		arrayList.add("b");
		arrayList.add("b");
		arrayList.add("c");
		arrayList.add("d");
		System.out.println(new Top3().top3(arrayList));
	}
}
