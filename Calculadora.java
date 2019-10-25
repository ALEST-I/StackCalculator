import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.*;
import java.nio.charset.Charset;

public class Calculadora {

    public Calculadora() {
    }

    public static void main(String args[]) {
        LinkedStack stack = new LinkedStack();
        
        // Get the file name
        Scanner in = new Scanner(System.in);
        System.out.print("Informe o nome do arquivo de entrada (incluindo a extensão do arquivo): ");
        String fileName = in.nextLine();
        in.close();

        // Read the file and get the numbers and ops
        readFile(stack, fileName);
        stack = reverseStack(stack);        

        int totalStackSize = stack.size();
        LinkedStack numbers = new LinkedStack();
        LinkedStack ops = new LinkedStack();

        int stackSize = stack.size();
        for(int i = 0; i < stackSize; i++) {
            String c = stack.pop();            
            if(c.equals("/") || c.equals("-") || c.equals("+") || c.equals("*") || c.equals("swap") || 
                c.equals("pop") || c.equals("chs") || c.equals("dup") || c.equals("sqrt")) {
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
            if(ops.size() > 0) {            
                String op = ops.pop();
                float res = 0;            
                if(op.equals("pop") || op.equals("swap") || op.equals("chs") || op.equals("dup") || op.equals("sqrt")) {
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
                        case "dup":
                            numbers = dup(numbers);                        
                            break;
                        case "sqrt":
                            numbers = sqrt(numbers);
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
            } else {
                System.out.println("Mais de um resultado na pilha.");
                fim = 1;
            }
            
        }
        
        System.out.println("Resultado armazenado no topo da pilha: " + numbers.top());        
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

    private static LinkedStack chs(LinkedStack stack) {        
        float numb1 = Float.valueOf(stack.pop()) * -1;
        stack.push(Float.toString(numb1));
        return stack;
    }

    private static LinkedStack dup(LinkedStack stack) {
        stack.push(stack.top());
        return stack;
    }

    private static LinkedStack sqrt(LinkedStack stack) {
        double num = Float.valueOf(stack.pop());
        num = Math.sqrt(num);
        stack.push(Double   .toString(num));
        return stack;
    }

    private static LinkedStack readFile(LinkedStack stack, String fileName) {
        Path path1 = Paths.get(fileName);
        try(Scanner sc=new Scanner(Files.newBufferedReader(path1,Charset.defaultCharset()))) {
            sc.useDelimiter("[;\n]"); // separadores: ; e nova linha
            while (sc.hasNext()) {
                String s = sc.next();
                s = s.replaceAll("\n", "");
                s = s.replaceAll("\r", "");
                s = s.replaceAll("\t", "");
                stack.push(s);
            }
        } catch (IOException x) {
            System.err.format("Erro de E/S: %s%n", x);
        }

        return stack;
    }
}