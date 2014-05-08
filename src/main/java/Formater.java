import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Formater {

	public static void main(String[] args) throws FileNotFoundException {
		Set<Long> set = new HashSet<Long>();
		
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (!line.startsWith("=====")) {
				continue;
			}
			line = line.substring(6, line.length() - 6);
			line = line.split(" ")[1];
			set.add(Long.parseLong(line));
		}
		in.close();
		
		for(Long x : set) {
			System.out.println(x);
		}
	}

}
