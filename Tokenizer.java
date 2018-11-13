

import java.util.ArrayList;
import java.util.Stack;

public class Tokenizer {

	String[] statement;
	Stack<String> opStack;
	Stack<String> nmStack;
	
	public Tokenizer (String s) {
		this.statement = tokenize(s);
		opStack = new Stack<String>();
		nmStack = new Stack<String>();
	}

	
	
	private String[] tokenize(String s) {
		
		ArrayList<String> s2 = new ArrayList<String>();
		String temp = "";
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '-'
					&& s.charAt(i - 1) != ')'
					&& ( i == 0 || !(s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '9'))) {
				temp += s.charAt(i);
				continue;
			}
			if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				temp += s.charAt(i);
			} else {
				if(temp != null && temp != "") {
					s2.add(temp);
				}
				s2.add("" + s.charAt(i));
				temp = "";
			}
		}
		s2.add(temp);
		if(s2.get(s2.size() - 1).equals(""))
			s2.remove(s2.size() - 1);
		return (String[]) s2.toArray(new String[s2.size()]);
	}
	
	public static boolean isInteger(String s) {
		int temp;
		try {
			temp = Integer.parseInt(s);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public static String lastObject(Stack<String> stack) {
		try {
			return stack.peek();
		} catch(Exception e) {
			return "";
		}
	}
	
}
