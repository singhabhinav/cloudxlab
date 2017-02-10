package com.cloudxlab.chaining;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StubFreqMapper extends Mapper<Object, Text, LongWritable, Text> {

  @Override
  public void map(Object key, Text value, Context context)
      throws IOException, InterruptedException {

	  //Split the line into words with spaces or tabs as separators
	  String[] words = value.toString().split("[ \t]+");
	  if(words.length > 1)
	  {
		  try
		  {
			  long l = Long.parseLong(words[1]);
			  context.write(new LongWritable(l), new Text(words[0]));
		  }
		  catch(NumberFormatException nfe)
		  {
			  System.out.println(nfe);
		  }
	  }
  }
}
