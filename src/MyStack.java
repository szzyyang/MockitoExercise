import java.util.HashSet;
import java.util.Set;
import java.util.NoSuchElementException;


public class MyStack {
   private int maxSize = 10;
   private int[] stackArray;
   private int top;
   
   public MyStack() {
	 stackArray = new int[maxSize];
     top = -1;
   }

  public void push(int j) throws OverflowException {
	  if(size()>= 10) {
		  throw new OverflowException();
	  }
      stackArray[++top] = j;
  }

  public int pop() {
	  if(isEmpty()) throw(new NoSuchElementException());
      return stackArray[top--];
  }

  public int peek() {
	  if(isEmpty()) throw(new NoSuchElementException());
      return stackArray[top];
  }
  
  public int size() {
      return top + 1;
  }

  public boolean isEmpty() {
      return (top == -1);
  }
  
  public int getMaxSize() {
	  return maxSize;
  }

}

