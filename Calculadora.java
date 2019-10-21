public class Calculadora {

    public Calculadora() {
    }

    public static void main(String args[]) {
        LinkedStack stack = new LinkedStack();
        stack.push("+");
        stack.push("10");
        stack.push("chs");
        stack.push("pop");
        stack.push("10");
        stack.push("*");
        stack.push("/");
        stack.push("5");
        stack.push("swap");
        stack.push("3");
        stack.push("6");
       stack.printList();

        int totalStackSize = stack.size();
        
        LinkedStack numbers = new LinkedStack();
        LinkedStack ops = new LinkedStack();

        int stackSize = stack.size();
        for(int i = 0; i < stackSize; i++) {
            String c = stack.pop();            
            if(c.equals("/") || c.equals("-") || c.equals("+") || c.equals("*") || c.equals("swap") || c.equals("pop") || c.equals("chs")) {
                ops.push(c);
            } else {
                numbers.push(c);
            }
        }

        ops = reverseStack(ops);
        numbers = reverseStack(numbers);             
        stackSize = ops.size();
        int fim = 0;
        while(fim != 1) {
            if(totalStackSize < (numbers.size() + ops.size())) {
                totalStackSize = (numbers.size() + ops.size());
            }
            String op = ops.pop();
            float res = 0;            
            if(op.equals("pop") || op.equals("swap") || op.equals("chs")) {
                // numbers.printList();
                switch(op) {
                    case "pop":
                        numbers.pop();
                        break;
                    case "swap":
                        numbers = swapOp(numbers);
                        break;
                    case "chs":
                        numbers = chs(numbers);
                        break;
                }
            }
            else {
                float num1 = Float.valueOf(numbers.pop());
                float num2 = Float.valueOf(numbers.pop());
                switch (op) {
                    case "/":
                        res = num2 / num1;
                        numbers.push(Float.toString(res));
                        break;
                    case "*":
                        res = num2 * num1;
                        numbers.push(Float.toString(res));
                        break;
                    case "+":
                        res = num2 + num1;
                        numbers.push(Float.toString(res));
                        break;
                    case "-":
                        res = num2 - num1;
                        numbers.push(Float.toString(res));
                        break;                    
                } 
            }
            if(numbers.size() == 1) {
                fim = 1;
            }
        }
        
        System.out.print("Resultado armazenado no topo da pilha: ");
        numbers.printList();
        System.out.println("Tamanho máximo da pilha: " +  totalStackSize);

    }
    private static LinkedStack swapOp(LinkedStack stack) {
        String num1 = stack.pop();
        String num2 = stack.pop();
        stack.push(num1);
        stack.push(num2);
        return stack;
    }

    private static LinkedStack reverseStack(LinkedStack stack) {
        LinkedStack newStack = new LinkedStack();
        int stackSize = stack.size();
        for(int i = 0; i < stackSize; i++) {
            newStack.push(stack.pop());
        }
        return newStack;
    }

    private static LinkedStack dup(LinkedStack stack) {
        String num1 = stack.pop();
        stack.push(num1);
        return stack;
    }
    private static LinkedStack chs(LinkedStack stack) {        
        float numb1 = Float.valueOf(stack.pop()) * -1;
        stack.push(Float.toString(numb1));
        return stack;
    }

    // public String get(int i) {
    //     Node aux = header.next;
    //     for(int j = 0; j < i; j++) {
    //         aux = aux.next;
    //     }
    //     return aux.element;
    // }

    // public void printList() {

    //     for(int i = 0; i < this.size(); i++) {
    //         System.out.print(this.get(i) + " ");
    //     }
    //     System.out.println();
    // }
}