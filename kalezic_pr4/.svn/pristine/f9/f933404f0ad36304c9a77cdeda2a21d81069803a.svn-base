package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import correlator.HashSetBJP;

public class hashSetTest {
	@Test
	public void putTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>(2);
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		set.put("blah", 1);
		
		assertEquals("[world, bye, blah, hello]", set.toString());
	}
	
	@Test
	public void putTest3() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>(4);
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		
		set.put("blah", 1);
		set.put("blank", 1);
		set.put("power", 1);
		set.put("flower", 1);
		
		assertEquals("[power, blah, bye, flower, hello, blank, world]", set.toString());
	}

	@Test
	public void getTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		
		int result = set.get("blah");
		
		
		assertEquals(1, result);
	}
	
	@Test
	public void getTest2() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		
		int result = set.get("blah");
		
		
		assertEquals(1, result);
	}
	
	@Test (expected = NullPointerException.class)
	public void getTestNull() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		
		int result = set.get("blank");
		
	}
	@Test
	public void isEmptyTestFalse() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		
		
		
		assertFalse(set.isEmpty());
	}
	
	@Test
	public void isEmptyTestTrue() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();

		assertTrue(set.isEmpty());
	}
	
	@Test
	public void clearTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		
		set.clear();
		
		assertEquals(0, set.size());
	}
	
	@Test
	public void removeContainsTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		
		set.remove("blah");
		assertEquals("[world, bye, hello]", set.toString());
	}
	@Test
	public void removeContainsTest2() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		
		set.remove("hello");
		assertEquals("[world, bye, blah]", set.toString());
	}
	
	@Test
	public void removeNotContainsTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		
		set.remove("blahblah");
		assertEquals("[world, bye, blah, hello]", set.toString());
	}
	
	
	@Test
	public void sizeTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		
		
		assertEquals(4, set.size());
	}
	
	@Test
	public void sizeTest0() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();

		assertEquals(0, set.size());
	}
	
	@Test
	public void emptyToStringTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>();

		assertEquals("[]", set.toString());
	}
	
	@Test
	public void nextPrimeTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>(3);
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		set.put("blank", 1);
		set.put("time", 1);

		
	}
	
	@Test
	public void intersectionTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>(10);
		HashSetBJP<String, Integer> set2 = new HashSetBJP<String, Integer>(10);
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		set.put("blank", 1);
		set.put("time", 1);
		
		set2.put("blah", 1);
		set2.put("hello", 1);
		set2.put("world", 1);
		set2.put("bye", 1);
		set2.put("blank", 1);
		
		
		int result = set.intersection(set2);
		assertEquals(5 , result);

		
	}
	@Test
	public void intersection0Test() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>(10);
		HashSetBJP<String, Integer> set2 = new HashSetBJP<String, Integer>(10);
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		set.put("blank", 1);
		set.put("time", 1);
		
		set2.put("duh", 1);
		set2.put("stuff", 1);
		set2.put("only", 1);
		set2.put("flower", 1);
		set2.put("power", 1);
		
		
		int result = set.intersection(set2);
		assertEquals(0 , result);

		
	}
	
	@Test
	public void intersectionEmptyTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>(10);
		HashSetBJP<String, Integer> set2 = new HashSetBJP<String, Integer>(10);
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		set.put("blank", 1);
		set.put("time", 1);

		
		
		int result = set.intersection(set2);
		assertEquals(0 , result);

		
	}

	@Test
	public void unionTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>(10);
		HashSetBJP<String, Integer> set2 = new HashSetBJP<String, Integer>(10);
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		set.put("blank", 1);
		set.put("time", 1);
		
		set2.put("duh", 1);
		set2.put("stuff", 1);
		set2.put("only", 1);
		set2.put("flower", 1);
		set2.put("power", 1);
		
		
		int result = set.union(set2);
		assertEquals(11 , result);

		
	}
	
	@Test
	public void unionSameTest() {
		HashSetBJP<String, Integer> set = new HashSetBJP<String, Integer>(10);
		HashSetBJP<String, Integer> set2 = new HashSetBJP<String, Integer>(10);
		
		set.put("blah", 1);
		set.put("hello", 1);
		set.put("world", 1);
		set.put("bye", 1);
		set.put("blank", 1);
		set.put("time", 1);
		
		set2.put("blah", 1);
		set2.put("hello", 1);
		set2.put("world", 1);
		set2.put("blank", 1);
		set2.put("bye", 1);
		set2.put("time", 1);
		
		
		int result = set.intersection(set2);
		assertEquals(6 , result);

		
	}
}
