
public class MyPStack {

	private int maxSize = 10;
	private int[] stackArray;
	private int top;

	public MyPStack() {
		stackArray = new int[maxSize];
		top = -1;
	}

	public MyPStack(int capacity) {
		maxSize = capacity;
		stackArray = new int[maxSize];
		top = -1;
	}

	public void push(int j) throws OverflowException {
		if (isFull()) throw new OverflowException(); 
		stackArray[++top] = j;
	}

	public int pop() throws InvalidOperationException {
		if (isEmpty()) throw new InvalidOperationException(); 
		return stackArray[top--];
	}

	public int peek() throws InvalidOperationException {
		if (isEmpty()) throw new InvalidOperationException(); 
		return stackArray[top];
	}

	public int size() {
		return top + 1;
	}

	public boolean isEmpty() {
		return (top == -1);
	}
	
	public boolean isFull() {
	      return (top == maxSize - 1);
	}
	
	public int maxSize() { // added for visibility to test overflow
		return maxSize;
	}

}