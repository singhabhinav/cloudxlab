package com.cloudxlab.chaining;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlattenReducer extends Reducer<LongWritable, Text, Text, LongWritable> {

  @Override
  public void reduce(LongWritable key, Iterable<Text> values, Context context)
      throws IOException, InterruptedException {

	  long sum = 0;
	  for(Text iw:values)
	  {
		  context.write(iw, key);
	  }
  }
}
