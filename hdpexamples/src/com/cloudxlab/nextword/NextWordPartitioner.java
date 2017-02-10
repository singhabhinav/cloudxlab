package com.cloudxlab.nextword;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapred.lib.HashPartitioner;

public class NextWordPartitioner extends Partitioner<TwoWords, Text> {

	HashPartitioner<String, Text> hashPartitioner = new HashPartitioner<String, Text>();

	@Override
	public int getPartition(TwoWords key, Text value, int numReduceTasks) {
		// TODO Auto-generated method stub
		if (numReduceTasks == 0) {
			return 0;
		}
		return key.hashCode() % numReduceTasks;
	}
}
