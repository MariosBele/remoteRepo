package package1;

public class Calculation{
    private double a;
    private double b;
    private String cal;
    
    public Calculation(double a, double b, String cal){
        this.a=a;
        this.b=b;
        this.cal=cal;
    }
    
    public String getCal(){
        return cal;
    }
    
    double x=0;    
    public double getResult(double a, double b, String cal){
        switch(cal){
            case("+"):
                x=a+b;
                break;
            case("-"):
                x=a-b;
                break;
            case("*"):
                x=a*b;
                break;
            case("/"):
                x=a/b;
                break;
            case("%"):
                x=a%b;
                break;
            case("square root"):
                x=Math.sqrt(a);
                break;
            }
        return x;
        }
}
    
