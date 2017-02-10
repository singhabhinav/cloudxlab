package com.cloudxlab.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class StubCombiner extends Reducer<Text, LongWritable, Text, LongWritable>
{
	@Override
	  public void reduce(Text key, Iterable<LongWritable> values, Context context)
	      throws IOException, InterruptedException {

	}
}
