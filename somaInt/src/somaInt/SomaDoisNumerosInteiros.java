package somaInt;

import java.util.*;

public class SomaDoisNumerosInteiros {
	public static void main(String args[]) {
		System.out.println("Soma de dois numeoros inteiros\n");
		boolean inputInteiro = false;
		Scanner sc;
		int num1, num2, soma;
		while(!inputInteiro) {
			try {
				sc = new Scanner(System.in);
				System.out.print("Digite o primeiro inteiro: ");
				num1 = sc.nextInt();
				System.out.print("Digite o segundo inteiro: ");
				num2 = sc.nextInt();
				sc.close();
				soma = num1 + num2;
				System.out.printf("%d + %d = %d",num1,num2,soma);
				
				inputInteiro = true;
			} catch(InputMismatchException e){
				System.out.println("Digite apenas valores inteiros!");
			}		
		}
	}
}
