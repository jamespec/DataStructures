package Queue;

public class Queue {
    int front, back;
    int size, capacity;
    int[] data;

    public Queue( int capacity ) {
        this.front = 0;  // Front of the line, next up.  Index into the data array.

        // back of the line, start with -1, see enqueue.  Index into the data array.
        // This implementation adds one to back before using it so an empty queue will have
        // back one position behind front.
        this.back = -1;
        this.size = 0;
        this.capacity = capacity;
        this.data = new int[capacity];

        // This is only to help illustrate what is going one.
        // This code should be removed since uninitialized elements won't be accessed if the code works.
        for( int i=0; i<capacity; i++ ) {
            data[i] = -1;
        }
    }

    public synchronized boolean isEmpty() {
        return size == 0;
    }

    public synchronized boolean isFull() {
        return size == capacity;
    }

    public synchronized void enqueue( int value ) {
        if( isFull() ) {
            throw new ArrayIndexOutOfBoundsException();
        }

        // 1 is added for back of queue, but wrap around to zero if we reach the end of the array
        // the empty queue should have a back set to -1 so that we add the first item to element 0.
        back = (back+1) % capacity;
        data[back] = value;

        size++;
    }

    public synchronized int dequeue() {
        if( isEmpty() ) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int value = data[front];
        data[front] = -1;              // !!! NOTE - illustration only, remove once you understand what is going on.
        front = (front+1) % capacity;
        size--;

        return value;
    }

    public synchronized int front() {
        if( isEmpty() ) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return data[front];
    }

    public synchronized int back() {
        if( isEmpty() ) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return data[back];
    }

    public int size() {
        return size;
    }

    static public void main( String[] args ) {
        Queue q = new Queue(5);

        q.enqueue(1);
        if( q.size() != 1 || q.front() != 1 || q.back() != 1 )
            throw new RuntimeException();

        q.enqueue(2);
        if( q.size() != 2 || q.front() != 1 || q.back() != 2 )
            throw new RuntimeException();

        q.enqueue(3);
        if( q.size() != 3 || q.front() != 1 || q.back() != 3 )
            throw new RuntimeException();

        if( q.dequeue() != 1 )
            throw new RuntimeException();

        if( q.size() != 2 || q.front() != 2 || q.back() != 3 )
            throw new RuntimeException();

        if( q.dequeue() != 2 )
            throw new RuntimeException();

        if( q.size() != 1 || q.front() != 3 || q.back() != 3 )
            throw new RuntimeException();

        if( q.dequeue() != 3 )
            throw new RuntimeException();

        if( q.size() != 0 || !q.isEmpty() )
            throw new RuntimeException();

        // watch the queue wrap around.
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.dequeue();
        q.dequeue();
        q.dequeue();

        System.out.println("No unexpected errors");
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[ ");

        int next = front;
        for(int i=0; i<size; i++) {
            s.append( data[next] + " " );
            next = ((next+1) % capacity);
        }
        s.append("]");
        return s.toString();
    }

}
