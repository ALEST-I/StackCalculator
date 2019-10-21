public class Calculadora {

    public Calculadora() {

    }

    public static void main(String args[]) {
        LinkedStack stack = new LinkedStack();
        stack.push("+");
        stack.push("10");
        stack.push("*");
        stack.push("/");
        stack.push("5");
        stack.push("6");
        stack.push("3");
        stack.printList();

        LinkedStack numbers = new LinkedStack();
        LinkedStack ops = new LinkedStack();

        int stackSize = stack.size();
        for(int i = 0; i < stackSize; i++) {
            String c = stack.pop();            
            if(c.equals("/") || c.equals("-") || c.equals("+") || c.equals("*")) {
                ops.push(c);
            } else {
                numbers.push(c);
            }
        }

        ops = reverseStack(ops);
        numbers = reverseStack(numbers);        
        stackSize = ops.size();
        for(int i = 0; i < stackSize; i++) {
            int num1 = Integer.valueOf(numbers.pop());
            int num2 = Integer.valueOf(numbers.pop());
            String op = ops.pop();
            System.out.println(num1);
            System.out.println(num2);
            System.out.println(op.charAt(0));
            int res = 0;
            switch (op.charAt(0)) {
                case '/':
                    res = num2 / num1;
                    break;
                case '*':
                    res = num2 * num1;
                    break;
                case '+':
                    res = num2 + num1;
                    break;
                case '-':
                    res = num2 - num1;
                    break;
            }
            numbers.push(Integer.toString(res));                  
        }
        
        numbers.printList();
    }

    private static LinkedStack reverseStack(LinkedStack stack) {
        LinkedStack newStack = new LinkedStack();
        int stackSize = stack.size();
        for(int i = 0; i < stackSize; i++) {
            newStack.push(stack.pop());
        }
        return newStack;
    }
}