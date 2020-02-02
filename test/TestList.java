import static org.junit.Assert.*;

import java.util.List;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class TestList{
			
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void example1() {
		List mockedList = mock(List.class);

		 //using mock object
		 mockedList.add("one");
		 mockedList.clear();

		 //verification
		 verify(mockedList).add("one");
		 verify(mockedList).clear();	 
	}
	
	@Test
	public void example2() {
		LinkedList mockedList = mock(LinkedList.class);

		 //stubbing
		 when(mockedList.get(0)).thenReturn("first");
		 when(mockedList.get(1)).thenThrow(new RuntimeException());

		 //following prints "first"
		 assertEquals("first", mockedList.get(0));

		 //following throws runtime exception
		 try {
			 mockedList.get(1);
			 fail();
		 }
		 catch (Exception e){			 
		 }

		 //following prints "null" because get(999) was not stubbed
		 assertEquals(null, mockedList.get(999));

		 //Although it is possible to verify a stubbed invocation, usually it's just redundant
		 //If your code cares what get(0) returns then something else breaks (often before even verify() gets executed).
		 //If your code doesn't care what get(0) returns then it should not be stubbed. Not convinced? See here.
		 verify(mockedList).get(0);	
	}
	
	@Test
	public void example3() {
	List mockedList = mock(List.class);
	
	//stubbing using built-in anyInt() argument matcher
	 when(mockedList.get(anyInt())).thenReturn("element");

	 //following prints "element"
	 assertEquals("element", mockedList.get(999));

	 //you can also verify using an argument matcher
	 verify(mockedList).get(anyInt());	}

}