package Queue;

public class ProducerConsumer implements Runnable {
    // This is a classic Producer/Consumer model.
    // One thread randomly adds items to the queue, the Producer.  On average one per second.
    // The other thread Consumes these at a different rate, exactly one per second.
    // You'll notice the queue getting longer on when the Producer happens to add a few in quick succession.
    // The Consumer should catch up as the average wait time on the Producer is the same as the average on the consumer.
    // Actually, rounding makes the Producer run slightly faster than 1 per second so the queue will tend to get longer over time.

    // Static Queue because we'll have two instances of this class sharing the Queue.
    // With two different threads accessing the same object we needed to make the Queue class thread safe.
    // Thread safety is a very advanced topic for a later time.
    static Queue queue = new Queue(100 );

    public void consumer() {
        while(true) {
            // First show the queue, then dequeue an item if there are any.
            System.out.println( queue );
            if( !queue.isEmpty() ) {
                int v = queue.dequeue();
                System.out.println("Dequeue: " + v );
            }
            try {
                Thread.sleep(1000);
            } catch( InterruptedException e ) {}
        }
    }


    // Background thread to enqueue integers into the queue, the Producer.
    @Override
    public void run() {
        int next=0;

        while(true) {
            // Wait a random amount of time, 0 to 2 seconds, then enqueue the next number
            try {
                Thread.sleep((int) (2000 * Math.random()));
            } catch( InterruptedException e ) {}

            queue.enqueue(next++);
        }
    }

    static public void main( String[] args ) {
        // Start the Producer thread:
        // Thread creates another execution thread that will run the code in the class proviced.
        // The class must implement the Runnable interface (have a run method)
        // calling start calls the run method on that object.
        Thread background = new Thread( new ProducerConsumer() );
        background.start();

        // Start the consumer
        // We'll use this main thread for the consumer.
        ProducerConsumer pc = new ProducerConsumer();
        pc.consumer();
    }

}
