
public class MyPStack {

	private int maxSize = 10;
	private int[] stackArray;
	private int top;
	private IDataBase db;
	private String id;
	private static int nextId = 1;
	
	public MyPStack(IDataBase stackDB) {
		  stackArray = new int[maxSize];
		  top = -1;
		  db = stackDB;
		  id = new Integer(nextId).toString();
		  nextId++;
	}
		 
	public String getId() {
		  return id;
	}

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
		if(isEmpty()) {
			db.create(id, j);
		}
		else {
			db.update(id, j);
		}
		stackArray[++top] = j;
	}

	public int pop() throws InvalidOperationException {
		if (isEmpty()) 
			throw new InvalidOperationException(); 
		
		if(size()> 1) {
			db.update(id, stackArray[top-1]);
		}
		else if(size()==1) {
			db.delete(id);
		}
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
	
	public void reset() throws OverflowException {
		  if(!isEmpty()) {
		   stackArray = new int[maxSize];
		   top = -1;
		   this.push(db.read(id));
		  }
	}

}