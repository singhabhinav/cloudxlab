package com.cloudxlab.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class StubPartitioner extends Partitioner<Text, LongWritable>
{
	@Override
	public int getPartition(Text key, LongWritable arg1, int numReducers) {

		String keyS = key.toString().toLowerCase();
		if((keyS.length() > 0) && (keyS.charAt(0) < 'k'))
		 {
			 return 0;
		 }
		 return 1;
	}
}
