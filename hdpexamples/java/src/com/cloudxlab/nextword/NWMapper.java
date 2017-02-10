package com.cloudxlab.nextword;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NWMapper extends Mapper<Object, Text, TwoWords, Text> {
	@Override
	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
//		System.out.println("input: " + value.toString());
		String line = value.toString().toLowerCase().replaceAll("[^a-z0-9]+", " ");

		String[] words = line.split("[ ]+");

		String pWord = null;
		for (String word : words) {
			if (pWord == null) {
				pWord = word;
				continue;
			}
			TwoWords emitCouple = new TwoWords(pWord, word);
			context.write(emitCouple, new Text(word));
			pWord = word;
		}
	}
}
