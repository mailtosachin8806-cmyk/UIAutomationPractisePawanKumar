package JavaStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;
import org.testng.annotations.Test;

public class javaStreamExercise {
	
	@Test(description = "Count the number of names starting with alphabet A in list without JavaStream")
	public void regular() {
		List<String> name = new ArrayList<String>();
		name.add("Abhijeet");
		name.add("Don");
		name.add("Adam");
		name.add("Alekshya");
		name.add("Ram");
		int count = 0;

		for (int i = 0; i < name.size(); i++) {
			String actual = name.get(i);
			if (actual.startsWith("A")) {
				count++;
			}

		}
		
		System.out.println(count);
	}

	
	@Test (description = "Count the number of names starting with alphabet A in list JavaStream to Apply a filter functionality")
	public void streamFilter() {
		
		List<String> name = new ArrayList<String>();
		name.add("Abhijeet");
		name.add("Don");
		name.add("Adam");
		name.add("Alekshya");
		name.add("Ram");

		name.stream().filter(i ->i.startsWith("A")).forEach(i -> System.out.println(i));
		Long count = name.stream().filter(s -> s.startsWith("A")).count();
		System.out.println("The count is: " + count);
		
		
		Long d = Stream.of("Abhijeet","Don","Adam","Alekshya","Ram").filter(s ->
		{
			s.startsWith("A");
			return true;
		}).count();
				
		System.out.println(d);	
		
		System.out.println("Print the names of ArrayList whose length is greater than four");
		name.stream().filter(s -> s.length()>4).forEach(s -> System.out.println(s));
		
		System.out.println("Print the names of ArrayList whose length is greater than four and give me first result");
		name.stream().filter(s ->s.length()>4).limit(1).forEach(s -> System.out.println(s));
		
	}
	
	@Test(description = "Apply a map functionaly in a stream")
	public void streamMap() {

		List<String> name = new ArrayList<String>();
		name.add("Man");
		name.add("Indian Don");
		name.add("Women");

		// Print the names which have last letter as "a" with uppercase
		Stream.of("Abhijeet", "Don", "Adam", "Alekshya", "Rama").filter(s -> s.endsWith("a")).map(s -> s.toUpperCase())
				.forEach(s -> System.out.println(s));

		System.out.println("Print the name which have first letter as A with uppercase and sorted");
		List<String> names = Arrays.asList("Abhijeet", "Don", "Adam", "Alekshya", "Rama");
		names.stream().filter(s -> s.startsWith("A")).sorted().map(s -> s.toUpperCase())
				.forEach(s -> System.out.println(s));

		System.out.println("Merging two stream into single stream");
		Stream<String> newStream = Stream.concat(name.stream(), names.stream());
		// newStream.sorted().forEach(s -> System.out.println(s));

		System.out.println("To check particular string is present or not or validation");
		boolean flag = newStream.anyMatch(s -> s.equalsIgnoreCase("Don"));
		System.out.println("The flag value is: " + flag);
		Assert.assertTrue(flag);

	}
	
	@Test(description ="Collect method is  basically used to collect a result and return into a any list")
	public void streamCollet()
	{
		//Print the first element of string
		List<String> ls= Stream.of("Abhijeet", "Don", "Adam", "Alekshya", "Rama").filter(s-> s.endsWith("a")).map(s ->s.toUpperCase())
		.collect(Collectors.toList());
		System.out.println(ls);
		System.out.println(ls.get(0));
		
		
		//Print unique number from this array	
		List<Integer> value = Arrays.asList(3,2,1,5,7,9,8,3,2);
		value.stream().distinct().sorted().forEach(s -> System.out.println(s));
		
		//Sort the array - 3rd index 
		value.stream().distinct().sorted().limit(3).forEach(s ->System.out.println(s));
		
		//Use a collection
		List<Integer> ls1 =value.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println(ls1.get(2));
		
		
	}
	
}
