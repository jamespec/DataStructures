package List;

public class ArrayList {
    int[] data;
    int size;
    int capacity;

    public ArrayList( int capacity ) {
        this.size = 0;
        this.data = new int[capacity];
        this.capacity = capacity;

        // This is only to help illustrate what is going one.
        // This code should be removed since uninitialized elements won't be accessed if the code works.
        for( int i=0; i<capacity; i++ ) {
            data[i] = -1;
        }
    }

    // Append an item to the end, for convenience. Could just use insert at the end.
    // O(1) constant time since we can directly access the end of the array regardless of the size.
    public void append( int value ) {
        if( size < capacity )
            data[size++] = value;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    // O(1) constant time since we can directly access the "index" item of the array regardless the size.
    public void set( int index, int value ) {
        if( index < size )
            data[index] = value;
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    // Insert a value at index shifting later values down.
    // O(n) since we have to move all the current elements, proportional to the the size n.
    public void insert( int index, int value ) {
        if( size < capacity && index <= size ) {
            // start from the end, move last item one slot down.
            // remember, the last item in an array is at index size-1
            // in this loop size is the target location, one more.
            for( int i=size; i>index; i-- )
                data[i] = data[i-1];

            data[index] = value;
            size++;
        }
        else
            throw new ArrayIndexOutOfBoundsException();
    }

    // Remove the value at index, shifting all later values up.
    // O(n) since we have to move all the current elements, proportional to the the size n.
    public void remove( int index ) {
        if( size > 0 && index < size ) {
            // reduce the size first so that we loop one less than the old size.
            // i is the target of the copy.
            size--;
            for( int i=index; i<size; i++ )
                data[i] = data[i+1];  // i+1 is okay since we only loop to 1 less than the old size.

            data[size] = -1; // illustration only, there is no reason to do this.
        }
        else
            throw new ArrayIndexOutOfBoundsException();

    }

    // return the index of the first occurrence of value
    // O(n) fetch, the amount of work is proportional to the size of n.
    public int find( int value ) {
        for( int i=0; i<size; i++ ) {
            if( data[i] == value )
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[ ");

        for(int i=0; i<size; i++) {
            s.append( data[i] + " " );
        }
        s.append("]");
        return s.toString();
    }


    static public void main( String[] args ) {
        ArrayList list = new ArrayList(100);

        list.append(1);
        System.out.println( list.toString() );
        list.append(2);
        System.out.println( list.toString() );
        list.append(3);
        System.out.println( list.toString() );
        list.append(4);
        System.out.println( list.toString() );
        list.append(5);
        System.out.println( list.toString() );

        list.set(3, 99);
        System.out.println( list.toString() );
        list.remove(1);
        System.out.println( list.toString() );
        list.insert(2, -99 );
        System.out.println( list.toString() );
        System.out.println( "99 found at index: " + list.find(99) );

    }
}
