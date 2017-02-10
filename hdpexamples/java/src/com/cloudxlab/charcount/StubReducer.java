package com.cloudxlab.charcount;
import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StubReducer extends Reducer<IntWritable, LongWritable, Text, LongWritable> {

  @Override
  public void reduce(IntWritable key, Iterable<LongWritable> values, Context context)
      throws IOException, InterruptedException {

	  long sum = 0;
	  for(LongWritable iw:values)
	  {
		  sum += iw.get();
	  }
	  char c = (char)key.get();
	  context.write(new Text(c+""), new LongWritable(sum));
  }
}
