import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MyStackTest {

	MyStack ms;
	@Before
	public void setUp() throws Exception {
		ms = new MyStack();
	}

	@After
	public void tearDown() throws Exception {
	
	}

	@Test 
	public void aTrivialTest() {
     	assert(true);
	}
	
	@Test 
	public void checkEmpty() {
		assertTrue(ms.isEmpty());
	}
	
	@Test 
	public void size0OnCreation() {
		assertEquals(0,ms.size());
	}
	
	@Test
	public void nPushes()  throws OverflowException{
		ms.push(1);
		ms.push(1);
		ms.push(1);
		ms.push(1);
		assertEquals(4,ms.size());
		assertFalse(ms.isEmpty());
	}
	
	@Test
	public void pushX()  throws OverflowException{
		int old_size = ms.size();
		ms.push(2);
		int res = ms.pop();
		assertEquals(2,res);
		assertEquals(old_size,ms.size());
	}
	
	@Test
	public void peekX()  throws OverflowException{
		ms.push(3);
		int old_size = ms.size();
		int res = ms.peek();
		assertEquals(3,res);
		assertEquals(old_size,ms.size());
	}
	
	@Test 
	public void nPops() throws OverflowException{
		ms.push(1);
		ms.pop();
		assertEquals(0,ms.size());
		assertTrue(ms.isEmpty());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void popNoSuchElement() {
		ms.pop();
	}
	
	
	@Test(expected = NoSuchElementException.class)
	public void peekNoSuchElement() {
		ms.peek();
	}
	
	@Test(expected = OverflowException.class)
	public void tooManyElements() throws OverflowException {
		for(int i=0;i<ms.getMaxSize();i++) {
			ms.push(0);
		}
		ms.push(1);
	}
	
	
	
	
	
	
	
	

}

