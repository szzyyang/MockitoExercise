/* Download hamcrest-all-1.3.jar and add to build path */
/* Eclipse: add as external jar, making sure it's above Junit in build order */


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


public class MyPStackTest {

    MyPStack s;
    
	@Before
	public void setUp() throws Exception {
		s = new MyPStack(10);
	}

	@Test
	public void stackIsEmptyOnConstruction() {
		assertTrue(s.isEmpty());
	}
	
	@Test
	public void canSetTheCapacityOfStack() {
		MyPStack s = new MyPStack(100);
		assertThat(s.maxSize(), is(equalTo(100)));
	}
	
	@Test
	public void stackHasSizeZeroOnConstruction() {
		assertThat(s.size(), is(equalTo(0)));
	}

	@Test /* testing boundary behavior */
	public void after1PushStackIsNonEmptyAndSizeIs1() throws OverflowException {
		s.push(1);
		assertFalse(s.isEmpty());
		assertThat(s.size(), is(equalTo(1)));
	}
	
	@Test /* testing nominal behavior */
	public void afterNPushesStackSizeIsN() throws OverflowException {
		int n = 3;
		for (int i = 1; i <= n; i++) {
			s.push(i*100);
		}
		assertFalse(s.isEmpty());
		assertThat(s.size(), is(equalTo(n)));
	}
		
	@Test
	public void popAfterPushReturnsPushedValueAndRestoresStackSize()
			throws OverflowException, InvalidOperationException {
		int pushValue = 200;
		int oldSize = s.size();
		s.push(pushValue);
		assertThat(s.pop(), is(equalTo(pushValue)));
		assertThat(s.size(), is(equalTo(oldSize)));
	}
	
	@Test
	public void peekAfterPushReturnsPushedValueAndMaintainsStackSize() 
			throws OverflowException, InvalidOperationException {
		int pushValue = 300;
		s.push(pushValue);
		int size = s.size();
		assertThat(s.peek(), is(equalTo(pushValue)));
		assertThat(s.size(), is(equalTo(size)));
	}
	
	public void poppingAllValuesLeavesAnEmptyStack() 
			throws OverflowException, InvalidOperationException {
		int size = 5;
		for (int v = 1; v <= size; v++) {
			s.push(v);
		}
		for (int v = 1; v <= size; v++) {
			s.pop();
		}
		assertTrue(s.isEmpty());
	}
	
	@Test (expected = InvalidOperationException.class)
	public void poppingFromEmptyStackThrowsException() throws InvalidOperationException {
		/* this will fail... naturally */
		s.pop();
	}
	
	@Test (expected = InvalidOperationException.class)
	public void peekingIntoEmptyStackThrowsException() throws InvalidOperationException {
		/* this will fail... naturally */
		s.peek();
	}
	
	@Test (expected = OverflowException.class)
	public void pushingTooManyElementsToStackThrowsException() throws OverflowException {
		for (int v = 1; v <= s.maxSize() + 1; v++) {
			s.push(v);
		}
	}
	
	@Test 
	public void pushingTooManyElementsToStackThrowsExceptionSaferVersion() throws OverflowException {
		int lastDrop = 10;
		for (int v = 1; v <= s.maxSize(); v++) {
			s.push(v);
		}
		try {
			s.push(lastDrop);
			fail(); // hmmm, this should never happen
		} catch (OverflowException e) {
		 // success: do nothing	
		}
	}
	
}
