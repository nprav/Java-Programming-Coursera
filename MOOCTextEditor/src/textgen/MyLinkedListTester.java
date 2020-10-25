/*

 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
//		System.out.println(longerList);
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
	}

	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove() {
		// test to confirm everything works in first half of list.
		System.out.println(list1);
		int a = list1.remove(0);
		System.out.println(list1);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer) 21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
//		assertEquals("Remove: check node link is correct ", list1.getNode(1).prev, list1.getNode(0));

		// Check Index out of bounds exception tail end
		try {
			String b = shortList.remove(2);
			fail("Check IndexOutOfBounds Error");
		}
		catch (IndexOutOfBoundsException e) {
			assertEquals("Remove: check tail element is correct ", "B", shortList.get(1));
			assertEquals("Remove: check size is correct ", 2, shortList.size());
		}

		// Check Index out of bounds exception head end
		try {
			String b = shortList.remove(-1);
			fail("Check IndexOutOfBounds Error");
		}
		catch (IndexOutOfBoundsException e) {
			assertEquals("Remove: check head element is correct ", "A", shortList.get(0));
			assertEquals("Remove: check size is correct ", 2, shortList.size());
		}

		// test to confirm everything works in second half of list.
		System.out.println(longerList);
		int index = LONG_LIST_LENGTH-3;
		int c = longerList.remove(index);
		System.out.println(longerList);
		assertEquals("Remove: check c is correct ", index, c);
		assertEquals("Remove: check element 0 is correct ", (Integer) 0, longerList.get(0));
		assertEquals("Remove: check size is correct ", LONG_LIST_LENGTH-1, longerList.size());
//		assertEquals("Remove: check node link is correct ",
//				longerList.getNode(index).next, longerList.getNode(index+1));

	}


	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		System.out.println(emptyList);
		emptyList.add(1);
		System.out.println(emptyList);
		assertEquals("Add: check add to empty list ",
				(Integer) 1, emptyList.get(0));

		assertEquals("Add: check add to short list ",
				"B", shortList.get(1));

		try {
			shortList.add(null);
			fail("Check null pointer exception");
		}
		catch (NullPointerException e) {
		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size: Empty list ", 0, emptyList.size());
		assertEquals("Size: Short list ", 2, shortList.size());
	}
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		// test adding outside the bounds of list + 1
		try {
			emptyList.add(emptyList.size()+1, 3);
			fail("Check Index out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		try {
			emptyList.add(-2, 3);
			fail("Check Index out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		// test adding at the start of a list
		shortList.add(0, "C");
		assertEquals("AddAtIndex: check add at beginning", "C", shortList.get(0));
		assertEquals("AddAtIndex: check size update", 3, shortList.size);
//		assertEquals("AddAtIndex: check start node-node link",
//				shortList.getNode(1), shortList.getNode(0).next);

		// test adding in the middle of the list
		longerList.add(3, 10);
		assertEquals("AddAtIndex: check add in the middle", (Integer) 10, longerList.get(3));
		assertEquals("AddAtIndex: check size update", LONG_LIST_LENGTH + 1, longerList.size);
//		assertEquals("AddAtIndex: check end node-node link",
//				longerList.getNode(3).prev, longerList.getNode(2));
//		assertEquals("AddAtIndex: check end node-node link",
//				longerList.getNode(4).prev, longerList.getNode(3));

		// test appending to a list
		list1.add(3, 100);
		assertEquals("AddAtIndex: check append", (Integer) 100, list1.get(3));
		assertEquals("AddAtIndex: check size update", 4, list1.size);
//		assertEquals("AddAtIndex: check end node-node link",
//				list1.getNode(2).next, list1.getNode(3));

		// test adding a null element
		try {
			list1.add(2, null);
			fail("Check Null pointer exception");
		}
		catch (NullPointerException e) {
		}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		// test setting with a null element
		try {
			int a = list1.set(0, null);
			fail("Check null element.");
		}
		catch (NullPointerException e) {
		}

		// test setting an element
		System.out.println(shortList);
		String b = shortList.set(1, "C");
		System.out.println(b);
		System.out.println(shortList);
		assertEquals("Set: Check returned value ", "B", b);
		assertEquals("Set: Check set value ", "C", shortList.get(1));

		// test setting element at an invalid index
		try {
			int c = emptyList.set(0, 1);
			fail("Check index out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}

}
