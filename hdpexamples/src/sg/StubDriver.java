package sg;

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

import sg.customreader.NLinesInputFormat;

public class StubDriver {

	public static void main(String[] args) throws Exception {
		
		if (args.length != 2) {
			System.out.printf("Usage: StubDriver <input dir> <output dir>\n");
			System.exit(-1);
		}
		
		JobConf conf = new JobConf();
		Job job = Job.getInstance(conf, "wordcount");
		job.setJarByClass(StubDriver.class);
		job.setMapperClass(StubMapper.class);
		job.setReducerClass(StubReducer.class);
		job.setPartitionerClass(StubPartitioner.class);		
//		job.setCombinerClass(StubCombiner.class);
		
		job.setNumReduceTasks(2);
		job.setInputFormatClass(NLinesInputFormat.class);
		conf.set("mapreduce.input.lineinputformat.linespermap", "100");
		
//		job.setInputFormatClass(FixedLengthInputFormat.class);
//		FixedLengthInputFormat.setRecordLength(conf, 100);
		
//		job.setInputFormatClass(NLinesInputFormat.class);
//		conf.set("mapreduce.input.lineinputformat.linespermap", 1000+"");
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
		
		//Let us say this is my string that I want to convert to ArrayWrite
//		String[] myStringArray = new String[]{"this", "that", "is"};
//		
//		//It is can be done by creating a new instance of ArrayWritable and passing
//		//your string array as argument
//		ArrayWritable aw = new ArrayWritable(myStringArray);
//		
//		//To convert it back to string array
//		myStringArray = aw.toStrings();
//		
	}
}
