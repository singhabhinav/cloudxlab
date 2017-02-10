package com.cloudxlab.wordcount;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FixedLengthInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.cloudxlab.customreader.NLinesInputFormat;

public class StubDriver {

	public static void main(String[] args) throws Exception {

		//The arguments provided after the StubDriver are part of args
		//You use this to take the name of input output file from the user.

		// if (args.length != 3) {
		// 	System.out.printf("Usage: StubDriver <input dir> <output dir>\n");
		// 	System.exit(-1);
		// }

		JobConf conf = new JobConf();
		Job job = new Job(conf, "wordcount");
		//or simply:
		// Job job = Job.getInstance();

		//See https://hadoop.apache.org/docs/r2.7.3/api/org/apache/hadoop/mapreduce/Job.html
		//For all other functions and options

		job.setJarByClass(StubDriver.class);
		job.setMapperClass(StubMapper.class);
		job.setReducerClass(StubReducer.class);
		//You can set number of reducers like this:
		// conf.setNumMapTasks(5);

		//To change number of reducers you can use the following:
		//job.setNumReduceTasks(2);

		//TO change the InputFormat class, use the following settings.
		//If you want exactly N number of lines on every inputsplit
		//job.setInputFormatClass(NLinesInputFormat.class);
		//conf.set("mapreduce.input.lineinputformat.linespermap", "100");

		//If you want to process file which is fixed input length format
		//job.setInputFormatClass(FixedLengthInputFormat.class);
		//FixedLengthInputFormat.setRecordLength(conf, 100);

		//You can set partitioner this way or
		//customize the distribution of keys on various reducers
		//job.setPartitionerClass(StubPartitioner.class);

		//You can define a combiner this way
		//This is used for running a local reducer on every mapper node
		//before transferring data to reducer node
		//job.setCombinerClass(StubCombiner.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		FileInputFormat.addInputPath(job, new Path("/data/mr/wordcount/input/big.txt"));
		FileOutputFormat.setOutputPath(job, new Path("javamrout"));

		//To avoid hardcoding, you can take the values from command line args
		// FileInputFormat.addInputPath(job, new Path(args[1]));
		// FileOutputFormat.setOutputPath(job, new Path(args[2]));
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}
