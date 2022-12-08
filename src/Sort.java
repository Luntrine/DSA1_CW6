
public class Sort {

	/*
	 * Part 4: complete method
	 */
	/** The following method sorts through an array by adding all elements of the array
	 * to a priority queue. It then adds the values in the priority queue back into the
	 * array in order from the minimum value to the maximum value, hence sorting the array.
	 *
	 * Complexity: O(n)
	 *
	 * @param arr is the array that will be sorted.
	 */
	public static void sort(int[] arr){	
		
		PriorityQueue pq = new PriorityQueue(100);
		
		// The following code inserts all values of the array into the priority queue.
		for(int i = 0; i < arr.length; i++)
		{
			pq.insert(arr[i]);
		}
		
		// The following code adds all values of the priority queue back into the array in order from min to max
		// using the removeMin() method, which removes the minimum value from the priority queue and returns it.
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = pq.removeMin();
		}
	}
	
	public static void main(String[] args){
		int[] arr = {53,3,5,2,4,67};
		Sort.sort(arr);
		// should be printed in order
		System.out.println(arr[0]);
		System.out.println(arr[1]);	
		System.out.println(arr[2]);
		System.out.println(arr[3]);	
		System.out.println(arr[4]);	
		System.out.println(arr[5]);	
	}
	
}
