/* Download hamcrest-all-1.3.jar and add to build path */
/* Eclipse: add as external jar, making sure it's above Junit in build order */

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


public class MyPStackTest {

    MyPStack s;
    IDataBase db;
    
	@Before
	public void setUp() throws Exception {
		db = mock(IDataBase.class);
		s = new MyPStack(db);
	}
	
	@Test
	public void canInstantiateWithMockIDataBase() {
		assertThat(s,is(notNullValue()));
	}
	
	@Test
	public void initiallyThereIsNoEntryInDB() 
		throws OverflowException, InvalidOperationException{
		verify(db, never()).create(anyString(), anyInt());
	}
	
	@Test
	public void firstPushSavesTopInDBD() 
		throws OverflowException, InvalidOperationException{
		s.push(100);
		verify(db).create(s.getId(), s.peek());
	}
	
	@Test public void pushUpdatesTopInDBInConsecutivePush() 
			throws OverflowException, InvalidOperationException{
		s.push(100);
		s.push(200);
		verify(db).update(s.getId(), s.peek());
	}

	@Test public void popUpdatesTopInDB() 
			throws OverflowException, InvalidOperationException {
		s.push(100);
		s.push(200);
		s.pop();
		verify(db).update(s.getId(), s.peek());
	}
	
	@Test
	public void resetReadsRightValueFromDB()
		throws OverflowException, InvalidOperationException {
		s.push(100);
		when(db.read(s.getId())).thenReturn(100);
		s.reset();
		assertThat(s.peek(),is(equalTo(100)));
	}
	
	@Test 
	public void afterResetStackHasOnlyLastTopElement() 
		throws OverflowException, InvalidOperationException {
		s.push(100);
		s.push(200);
		when(db.read(s.getId())).thenReturn(200);
		s.reset();
		assertThat(s.peek(),is(equalTo(200)));
		assertThat(s.size(),is(equalTo(1)));
	}
	
	@Test
	public void whenStackBecomesEmptyDBEntryIsDeleted()
		throws OverflowException, InvalidOperationException {
		s.push(100);
		s.pop();
		verify(db).delete(s.getId());
		
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
	
	@Test
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
