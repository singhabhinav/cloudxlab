package com.cloudxlab.chaining;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.cloudxlab.wordcount.StubMapper;
import com.cloudxlab.wordcount.StubReducer;
import com.cloudxlab.customreader.NLinesInputFormat;

public class ChDriver {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.printf("Usage: StubDriver <input dir> <output dir>\n");
			System.exit(-1);
		}
		JobConf conf = new JobConf();
		JobControl jobCtrl = new JobControl("mygrp");

		Job job1 = Job.getInstance(conf, "wordcount");
		job1.setJarByClass(ChDriver.class);
		job1.setMapperClass(StubMapper.class);
		job1.setReducerClass(StubReducer.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(LongWritable.class);
		job1.setNumReduceTasks(2);

		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(LongWritable.class);

		FileInputFormat.addInputPath(job1, new Path(args[0]));
		Path tmpDir = new Path(args[1] + "-tmp");
		FileOutputFormat.setOutputPath(job1, tmpDir);

		ControlledJob cJob1 = new ControlledJob(conf);
		cJob1.setJob(job1);

		Job job2 = Job.getInstance(conf, "wordcount");
		job2.setJarByClass(ChDriver.class);
		job2.setMapperClass(StubFreqMapper.class);
		job2.setReducerClass(FlattenReducer.class);
		job2.setSortComparatorClass(LongWritable.DecreasingComparator.class);

		FileInputFormat.addInputPath(job2, tmpDir);
		FileOutputFormat.setOutputPath(job2, new Path(args[1]));

		ControlledJob cJob2 = new ControlledJob(conf);
		cJob2.setJob(job2);
		cJob2.addDependingJob(cJob1);

		jobCtrl.addJob(cJob1);
		jobCtrl.addJob(cJob2);

		Thread thread = new Thread(jobCtrl);
		thread.start();

		while (!jobCtrl.allFinished()) {
			System.out.println("Still running...");
			Thread.sleep(5000);
		}

	}
}
