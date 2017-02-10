package com.cloudxlab.nextword;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NWDriver {
	public static void main(String[] args) throws IllegalArgumentException,
			IOException {

		if (args.length != 2) {
			System.out.printf("Usage: StubDriver <input dir> <output dir>\n");
			System.exit(-1);
		}
		// JobConf conf = new JobConf();
		// conf.set("textinputformat.record.delimiter","\n\n");
		Job job = Job.getInstance(new Configuration());

		job.setJarByClass(NWDriver.class);
		job.setNumReduceTasks(1);
		job.setGroupingComparatorClass(NextWordGroupingComparator.class);
		// job.setSortComparatorClass(NextWordComparator.class);
		job.setMapOutputKeyClass(TwoWords.class);

		// job.setMapperClass(NextWordMapper.class);
		job.setMapperClass(NWMapper.class);
		job.setReducerClass(NWReducer.class);
		// job.setPartitionerClass(NextWordPartitioner.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setMapOutputKeyClass(TwoWords.class);
		job.setMapOutputValueClass(Text.class);

		// job.setInputFormatClass(FixedLengthInputFormat.class);
		// FixedLengthInputFormat.setRecordLength(conf, 15);

		FileInputFormat.addInputPath(job, new Path(args[1]));
		FileOutputFormat.setOutputPath(job, new Path(args[2]));

		boolean result = true;
		try {
			result = job.waitForCompletion(true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(result ? 0 : 1);
	}
}
