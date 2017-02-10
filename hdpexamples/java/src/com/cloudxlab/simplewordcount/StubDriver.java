package com.cloudxlab.simplewordcount;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.lib.NLineInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FixedLengthInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class StubDriver {

	public static void main(String[] args) throws Exception {
		if (args.length != 3) {
			System.out.printf("Usage: StubDriver <input dir> <output dir>\n");
			System.exit(-1);
		}
		JobConf conf = new JobConf();
		Job job = new Job(conf, "wordcount");
		job.setJarByClass(StubDriver.class);

		job.setMapperClass(StubMapper.class);
		job.setReducerClass(StubReducer.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);

		// job.setInputFormatClass(FixedLengthInputFormat.class);
		FixedLengthInputFormat.setRecordLength(conf, 15);
//		conf.set("textinputformat.record.delimiter", ".");

		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));


		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}
