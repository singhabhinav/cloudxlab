package com.cloudxlab.nextword;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class NextWordGroupingComparator extends WritableComparator {

	protected NextWordGroupingComparator() {

		super(TwoWords.class, true);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {

		TwoWords key1 = (TwoWords) w1;
		TwoWords key2 = (TwoWords) w2;

		return key1.getPK().compareTo(key2.getPK());
	}
}
