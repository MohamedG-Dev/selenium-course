package selenium.learning.java.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StreamsExample {

	@Test
	public void streamsFilterExample() {
		String[] strings = { "apple", "banana", "bread", "butter", "box", "button", "best", "bad", "cucumber", "cat",
				"cute", "dog", "donkey", "elephant", "fox", "goat", "hen", "idly", "joy", "kemp", "lord", "max" };
		List<String> list = Arrays.asList(strings);
		// fetch value starts with 'b' and get the count of strings starting with 'b'
		long count = list.stream().filter(name -> name.startsWith("b")).count();
		System.out.println("Count of names starting with character 'b':" + count);
		// filter names whose size is greater than 4 and print them
		System.out.println("*******filter names whose size is greater than 4 and printing them**********");
		list.stream().filter(name -> name.length() > 4).forEach(System.out::println);
		System.out.println("*****************************************************");
		// filter names whose size is greater than 4 and print only first 3 data
		list.stream().filter(name -> name.length() > 4).limit(3).forEach(System.out::println);
	}

	@Test
	public void streamsMapExample() {
		// print names length > 4 and ends with 'a' with uppercase
		Stream.of("apple", "alpha", "banana", "gauva", "butter", "box", "papaya", "button", "best", "bata", "beta",
				"bad", "cucumber").filter(name -> name.endsWith("a")).map(String::toUpperCase)
				.forEach(System.out::println);
		List<String> list = Arrays.asList("banana", "butter", "box", "button", "best", "bata", "beta", "bad");
		System.out.println("**********Sort the list starts with 'b' to upper case and print them******************");
		list.stream().filter(name -> name.startsWith("b")).sorted().map(String::toUpperCase)
				.forEach(System.out::println);

	}
	
	@Test
	public void mergeTwoLists() {
		List<String> list = Arrays.asList("Apple","banana", "butter", "cat","box", "button", "debt","best", "bata","gamma", "beta", "bad");
		List<String> players = Arrays.asList("gill","jaiswal","iyer","samson","rohit","virat","kuldeep","chahal","shikhar","siraj","bumrah");
		System.out.println("*************merging two lists using streams and printing them************************");
		Stream.concat(list.stream(),players.stream()).sorted().map(String::toUpperCase).forEach(System.out::println);
		System.out.println("*********merging two lists and matching whether a specific data is there or not********");
		boolean flag = Stream.concat(list.stream(),players.stream()).anyMatch(name -> name.equalsIgnoreCase("butter"));
		Assert.assertTrue(flag);
		Assert.assertTrue(list.stream().anyMatch(name -> name.equalsIgnoreCase("bata")));
		
	}
	
	@Test
	public void streamCollect() {
		List<String> list =Stream.of("apple", "alpha", "banana", "gauva", "butter", "box", "papaya", "button", "best", "bata", "beta",
				"bad", "cucumber").filter(name -> name.endsWith("a")).map(String::toUpperCase).collect(Collectors.toList());
		System.out.println(list.get(0));
		list.stream().limit(1).forEach(System.out::println);;
	}
	
	@Test
	public void printUniqueNumberAndSort() {
		List<Integer> list = Arrays.asList(3,2,2,7,5,1,9,7);
		list.stream().distinct().sorted().forEach(num -> System.out.print(num+" "));
		//collect the list in a distinct sorted order and print the 3rd index of the list
		System.out.println("******************collect the list in a distinct sorted order and print the 3rd index of the list************************");
		List<Integer> sortedList = list.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println("Printing the 3rd index of the list"+sortedList.get(2));
	}

}
