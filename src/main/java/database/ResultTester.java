package database;
import java.io.IOException;
import java.util.LinkedList;

public class ResultTester {
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ResultManager manager = new ResultManager();
		
		//LinkedList<String[]> results = manager.getResults(1);
		
		LinkedList<String[]> arr = new LinkedList<String[]>();
		String[] row = {"item1", "item2", "item3"};
		arr.add(row);
		
		System.out.println(arr);
	}
}
