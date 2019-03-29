package ce_demo_linked_stack;

public class LinkedStackApp{
	//add 3 elements
	//print result with toString
	//try printing elements with a for loop - it won't work
	
    public static void main(String[] args) {
    	
        LinkedStack<String> stack = new LinkedStack<String>();
        stack.push("Biskit");
        stack.push("Gojira");
        stack.push("Kaiju");
        
        System.out.println(stack);
        
        for (String s: stack)
            System.out.println(s);
    }
}
