package com.cloudxlab.nextword;

import java.io.IOException;
import java.util.PriorityQueue;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class NWReducer extends Reducer<TwoWords, Text, Text, Text> {



	@Override
	public void reduce(TwoWords key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
//		System.out.println("Reducer Key: " + key.getPK());

		String prev = null;
		int runningCount = 0;
		PriorityQueue<WCTuple> pq = new PriorityQueue<WCTuple>();
		for(Text t: values)
		{
			String word = t.toString();
//			System.out.println("Val: " + word);
			if(prev == null)
			{
				prev = word;
				runningCount = 1;
				continue;
			}

			if(prev.equals(word))
			{
				runningCount += 1;
				continue;
			}
			WCTuple wc = new WCTuple(word, runningCount);
			wc.addToList(pq);
			prev = word;
			runningCount = 1;
		}
		if(prev != null)
		{
			WCTuple wc = new WCTuple(prev, runningCount);
			wc.addToList(pq);
		}

		String output = "";

		while(!pq.isEmpty())
		{
			WCTuple wc = pq.remove();
			output = wc.getWord() + "," + output;
//			System.out.println("output: " + output);
		}

		context.write(new Text(key.getPK()), new Text(output));
	}
}
