import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {

	/*
	 * Part 1: complete
	 */
	
	PriorityQueue pq;
	
	@Before
	public void setup(){
		pq = new PriorityQueue(20);
	}
	
	@Test // inserts a few numbers then tests pq.min()
	public void insertTestMin() {
		pq.insert(5);
		pq.insert(3);
		pq.insert(1);
		pq.insert(2);
		pq.insert(4);
		assertEquals(1, pq.min());
	}
	
	@Test // inserts a few numbers then tests pq.size()
	public void insertTestSize() {
		pq.insert(5);
		pq.insert(3);
		pq.insert(1);
		pq.insert(2);
		pq.insert(4);
		assertEquals(5, pq.size());
	}

	@Test // adds a few numbers to pq then calls pq.removeMin() a few times to check if numbers are returned in ascending order
	public void removeMinTest() {
		pq.insert(5);
		pq.insert(3);
		pq.insert(1);
		pq.insert(2);
		pq.insert(4);
		assertEquals(1, pq.removeMin());
		assertEquals(2, pq.removeMin());
		assertEquals(3, pq.removeMin());
		assertEquals(4, pq.removeMin());
		assertEquals(5, pq.removeMin());
	}
	
	
	@Test(expected=PriorityQueueException.class)
	public void removeMinEmptyTest() {
		pq.removeMin();
	}
}	
