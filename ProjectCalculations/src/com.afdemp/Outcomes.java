package package1;

public class Outcomes {
    private double a;
    private double b;
    public Outcomes(double a, double b){
        this.a=a;
        this.b=b;
    }
    
    public double getA(){
        return a;
    }
    public double getB(){
        return b;
    }
    
    public void getGCD(){
        int gcd=1;
        //I can find the GCD of two Integer numbers. So I check if the 2 results are integers.
        if (((a == Math.floor(a)) && !Double.isInfinite(a) && (b == Math.floor(b)) && !Double.isInfinite(b)) && ((a!=0) && (b!=0))){ 
            for(int i=1; i<Math.min(a, b)+1;i++){
                if((a%i==0)&&(b%i==0)){
                    gcd=i;
                }
            } 
            System.out.println("The Greatest Common Divisor (GCD) of "+a+" and "+b+" is the number "+gcd);
        }else{
            System.out.println("Greatest Common Divisor is defined only for Integers-{0}.");
        }    
    }
    
    public void getPrime(){
        int k=0;
        if (b==1 || b==2){
            k=1;
        }else{
            for(double i=2; i<b;i++){
                if(b%i==0){
                    k=1;
                    break;
                }
            }
        }        
        if (k==0){
            System.out.println(b+" is a prime number."); 
        }else{
            System.out.println(b+" isn't a prime number.");
        }
        
    }
  
    int m3=0;
    
    public int getFibonacci(double b){
        if(b==2 || b==1){
            m3= 1;
        }
        if (b>2){    
           m3= getFibonacci(b - 1)+ getFibonacci(b-2);
        }
        return m3;      
    }

    public boolean getIsFib(){
        if (b<=3 || b==5){
            return true;
        }
        if (b>5){
            int i=5;
            while(getFibonacci(i)<b+1){
                if(getFibonacci(i)==b){
                    return true;
                } 
                i++;
            }
        }    
        return false;
    }
}  
