package com.cloudxlab.nextword;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

public class StubTest {

	/*
	 * Declare harnesses that let you test a mapper, a reducer, and a mapper and
	 * a reducer working together.
	 */
	MapDriver<Object, Text, TwoWords, Text> mapDriver;
	ReduceDriver<TwoWords, Text, Text, Text> reduceDriver;
	MapReduceDriver<Object, Text, TwoWords, Text, Text, Text> mapReduceDriver;

	/*
	 * Set up the test. This method will be called before every test.
	 */
	@Before
	public void setUp() {

		/*
		 * Set up the mapper test harness.
		 */
		NWMapper mapper = new NWMapper();
		// NWM mapper = new NWM();
		mapDriver = new MapDriver<Object, Text, TwoWords, Text>();
		mapDriver.setMapper(mapper);
		/*
		 * Set up the reducer test harness.
		 */
		NWReducer reducer = new NWReducer();
		reduceDriver = new ReduceDriver<TwoWords, Text, Text, Text>();
		reduceDriver.setReducer(reducer);

		/*
		 * Set up the mapper/reducer test harness.
		 */
		mapReduceDriver = new MapReduceDriver<Object, Text, TwoWords, Text, Text, Text>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);

		mapReduceDriver
				.setKeyGroupingComparator(new NextWordGroupingComparator());
//		mapReduceDriver.setKeyOrderComparator(new NextWordComparator());
	}

	/*
	 * Test the mapper.
	 */
	// @Test
	// public void testMapper() {
	//
	// /*
	// * For this test, the mapper's input will be "1 cat cat dog" TODO:
	// * implement
	// */
	// //* mapDriver.setInputKey("1");
	// //* mapDriver.setInputValue(new Text("this        is")); **/
	//
	// / mapDriver.setInput(1,"this is");
	// // List<Pair<Text, LongWritable>> x;
	// try {
	// // x = mapDriver.run();
	// assertEquals(x.size(), 3);
	// assertEquals(x.get(0).getFirst().toString(), "this");
	// assertEquals(x.get(1).getFirst().toString(), "is");
	// assertEquals(x.get(2).getFirst().toString(), "test");
	//
	// for(Pair<Text, LongWritable> p:x)
	// {
	// System.out.println("MAPPER: " + p.getFirst() + ": " + p.getSecond());
	// }
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	/*
	 * // * Test the reducer. //
	 */
	// @Test
	// public void testReducer() {
	//
	// /*
	// * For this test, the reducer's input will be "cat 1 1". The expected
	// * output is "cat 2". TODO: implement
	// */
	// fail("Please implement test.");
	//
	// }

	/*
	 * Test the mapper and reducer working together.
	 */
	@Test
	public void testMapReduce() throws IOException {

//		mapReduceDriver
//				.addInput(new Pair<Object, Text>(
//						"1",
//						new Text(
//								"abc ZZZZZZ asdasdsdadsad ....adsad....asadad  ddsaasdasa asdd sdd; \n wdadsd ddsssdsadsdad")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"this is a boy. this is a boy. this is a boy. this is a boy")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"sandeep....... giri")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"sandeep     g")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"sandeep   a")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"sandeep            b")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"sandeep c")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"sandeep     g")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"sandeep     g")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"sandeep   a")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"sandeep....... ziri this and")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ this will")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc giri")));
//		mapReduceDriver
//				.addInput(new Pair<Object, Text>("2", new Text("abc g")));
//		mapReduceDriver
//				.addInput(new Pair<Object, Text>("2", new Text("abc a")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"abc    b")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"sandeep c")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc giri")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"abc    b")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"abc    b")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"sandeep c")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZZZZ")));
//
//		mapReduceDriver.addInput(new Pair<Object, Text>("2", new Text(
//				"abc ZZZ g")));
//		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
//				"abc ZZZ ZZZZZZ")));

		mapReduceDriver.addInput(new Pair<Object, Text>("1", new Text(
				"this is a boy. this is a boy. this is a boy. this is a boy")));

		List<Pair<Text, Text>> output = mapReduceDriver.run();
		System.out.println(output);
		assertEquals(4, output.size());
		System.out.println("==");
		for (Pair<Text, Text> p : output) {
			System.out.println(p.getFirst() + " - " + p.getSecond());
		}
		System.out.println("==");
	}
}
