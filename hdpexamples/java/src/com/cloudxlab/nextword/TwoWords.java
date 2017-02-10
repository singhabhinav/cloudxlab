package com.cloudxlab.nextword;

import  java.io.DataInput;
import  java.io.DataOutput;
import  java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

@SuppressWarnings("rawtypes")
public class TwoWords  implements WritableComparable{

	private String FirstWord;
	private String SecondWord;
	@Override
	public void readFields(DataInput in) throws IOException {
		FirstWord = WritableUtils.readString(in);
		SecondWord = WritableUtils.readString(in);
	}
	@Override
	public void write(DataOutput out) throws IOException {;

	WritableUtils.writeString(out,FirstWord);
	WritableUtils.writeString(out,SecondWord);

	}
	public TwoWords(){}

	public TwoWords(String Key1, String Key2)
	{
		this.FirstWord = Key1;
		this.SecondWord = Key2;

	}

	@Override
	public String toString(){
		return (new StringBuilder()).append(FirstWord).append(",").append(SecondWord).toString();

	}

	/** get FirstWord  ***/

	public String getPK()
	{
		return this.FirstWord;

	}

	/*** get secondary key ***/

	public String getSK()
	{
		return this.SecondWord;
	}

	/*** set PK  **/
	public void setPK(TwoWords o)
	{
		this.FirstWord = o.FirstWord;
	}
	/** set secondary key **/
	public void setSK(TwoWords o)
	{
		this.SecondWord = o.SecondWord;

	}
	@Override
	public int compareTo(Object o) {
		int result = this.FirstWord.compareTo(((TwoWords)o).FirstWord );
		if (result == 0){
			result = this.SecondWord.compareTo(((TwoWords)o).SecondWord);
		}
		return result;
	}
}
