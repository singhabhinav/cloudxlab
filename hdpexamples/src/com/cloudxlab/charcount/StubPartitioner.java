package com.cloudxlab.charcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class StubPartitioner extends Partitioner<IntWritable, LongWritable>
{

	@Override
	public int getPartition(IntWritable key, LongWritable arg1, int numReducer) {

//		return key.get() % arg2;

		String keyS = key.toString();
		if((keyS.length() > 0) && (keyS.charAt(0) < 'k'))
		 {
			 return 0;
		 }
		 return 1;
	}

}
