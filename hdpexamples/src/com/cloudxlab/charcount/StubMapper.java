package com.cloudxlab.charcount;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StubMapper extends Mapper<Object, Text, IntWritable, LongWritable> {

  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {

	  String line = value.toString();

	  for(int i=0; i < line.length(); i++)
	  {
		  char ch = line.charAt(i);
		  context.write(new IntWritable(ch), new LongWritable(1));
	  }
  }
}
