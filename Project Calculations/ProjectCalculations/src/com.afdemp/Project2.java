package package1;

import java.util.Scanner;

public class Project2 {

    public static void main(String[] args) {
        double [] a=new double[2];
        double [] b=new double[2];
        String [] cal=new String[2];
        double result []=new double[2];
        for (int i=0; i<2; i++){
            Scanner input= new Scanner(System.in);
            System.out.println("Enter the first number: ");
            while (!input.hasNextDouble()){
                System.out.println("That's not a number! Try again: ");
                input.next();
            }
            a[i]=input.nextDouble();
            Scanner input1= new Scanner(System.in);
            do{
                System.out.println("Enter a symbol for the calculation. For square root, type \"square root\": ");    
                cal[i]=input1.nextLine();
            }while(!(cal[i].equals("+")||cal[i].equals("-")||cal[i].equals("*")||cal[i].equals("/")||cal[i].equals("%")||cal[i].toLowerCase().equals("square root")));
            if(cal[i].toLowerCase().equals("square root")){
                while((a[i]==0)&&(cal[i].toLowerCase().equals("square root"))){
                    Scanner input5= new Scanner(System.in);
                    System.out.print("You can't divide a number by 0. Try again: " );
                    do{
                        System.out.println("Enter a symbol for the calculation except square root: ");    
                        cal[i]=input5.nextLine();
                    }while(!(cal[i].equals("+")||cal[i].equals("-")||cal[i].equals("*")||cal[i].equals("/")||cal[i].equals("%")));
                }
            }
            if(cal[i].equals("+")||cal[i].equals("-")||cal[i].equals("*")||cal[i].equals("/")||cal[i].equals("%")){
                Scanner input2= new Scanner(System.in);
                System.out.println("Enter the second number: ");
                while (!input2.hasNextDouble()){ 
                    System.out.println("That's not a number! Try again: ");
                    input2.next();
                    }
                b[i]=input2.nextDouble();
                while (b[i]==0){
                    Scanner input3= new Scanner(System.in);
                    System.out.print("You can't divide a number by 0. Try again: " );
                    b[i]=input3.nextDouble();
                }
            }
            Calculation calcul=new Calculation(a[i],b[i],cal[i]);
            if (calcul.getCal().equals("square root")){
                System.out.println(a[i]+" "+cal[i]+"="+calcul.getResult(a[i],b[i],cal[i]));
            }else{
                System.out.println(a[i]+cal[i]+b[i]+"="+calcul.getResult(a[i],b[i],cal[i]));
            }
            result[i]=calcul.getResult(a[i],b[i],cal[i]);
        }
        Outcomes outcome=new Outcomes(result[0], result[1]);
        outcome.getGCD();
        if ((result[1] == Math.floor(result[1])) && !Double.isInfinite(result[1]) && result[1]>0)  {
            outcome.getPrime();
            System.out.println("The fibonacci("+result[1]+") is the number "+outcome.getFibonacci(result[1]));
            System.out.println("The hypothesis that "+result[1]+" is a fibonacci number is "+outcome.getIsFib());
        }else{
            System.out.println("The second result can't be prime number or a fibonacci number");
        }
    
    }  
    
}
