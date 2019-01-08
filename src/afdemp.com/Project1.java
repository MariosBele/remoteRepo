package Package1;
import java.util.Arrays;
import java.util.Scanner;

public class Project1{
   public static void main(String args[]){
       System.out.println("Welcome to the Family App");
       Person person=new Person();
       //All arrays have 4 items because there are 4 family members.
       String []family_member= {"Father", "Mother", "Son", "Daughter"};
       String first_name_array[]=new String [4];
       String last_name_array[]=new String [4];
       int age_array[]=new int[4];
       Integer[] copy_array = new Integer[4] ;
       String sex_array[]=new String [4];
       String fav_season_array[]=new String [4];
       //For loop helps to get the personal data for each member.
       for (int i=0; i<4; i++){
           System.out.print(family_member[i]+"'s first name> ");
           String first_name= new Scanner(System.in).nextLine();
           //Every input will take its place in the proper array. 
           first_name_array[i]=first_name;
           System.out.print(family_member[i]+"'s last name> ");
           String last_name= new Scanner(System.in).nextLine();
           last_name_array[i]=last_name;
           System.out.print(family_member[i]+"'s age> " );
           int age= new Scanner(System.in).nextInt();
           /*For some inputs the app should check if the input is logically correct.
           While loop helps to avoid a wrong input.*/
           while (age<=0 || age>150){
               System.out.print("Wrong input! Enter " +family_member[i]+"'s age> " );
               age= new Scanner(System.in).nextInt();
           }
           age_array[i]=age;
           System.out.print(family_member[i]+"'s sex> ");
           String sex= new Scanner(System.in).nextLine();
           //In case of sex and favorite season, the "toLowerCase" method helps us 
           //to accept different ways of writting the words we asked for.
           while (!((sex.toLowerCase().equals("female")) || (sex.toLowerCase().equals("male")))) {
               System.out.print("Wrong input! Enter "+ family_member[i]+"'s sex.(Female or Male)> " );
               sex= new Scanner(System.in).nextLine();
           }
           sex_array[i]=sex;
           System.out.print(family_member[i]+"'s favorite season> ");
           String fav_season= new Scanner(System.in).nextLine();
           while (!((fav_season.toLowerCase().equals("winter")) || (fav_season.toLowerCase().equals("spring")) || (fav_season.toLowerCase().equals("autumn")) || (fav_season.toLowerCase().equals("summer")))) {
               System.out.print("Wrong input! Enter "+ family_member[i]+"'s favorite season> " );
               fav_season= new Scanner(System.in).nextLine();
           }
           //We prefer to have the input according the favorite season in lower case letters,
           //for the sake of switch statement.
           fav_season_array[i]=fav_season.toLowerCase();
       }
       //Create manually a copy of age array, so we will not lose the information that this array has given us.
       for (int k=0;k<4;k++){
           copy_array[k]=age_array[k];
       }
       //Sort the age array to have the information we need, about the order we are going to print the data.
       Arrays.sort(age_array);
       //The age array changed. We need to learn where exactly were before each age in the array. 
       //This is the reason why the copy array was created. 
       for (int i=0;i<4;i++){
           person.setFirstName(first_name_array[Arrays.asList(copy_array).indexOf(age_array[4-i-1])]);
           person.setLastName(last_name_array[Arrays.asList(copy_array).indexOf(age_array[4-i-1])]);
           person.setAge(copy_array[Arrays.asList(copy_array).indexOf(age_array[4-i-1])]);
           person.setSex(sex_array[Arrays.asList(copy_array).indexOf(age_array[4-i-1])]);
           person.setFavSeason(fav_season_array[Arrays.asList(copy_array).indexOf(age_array[4-i-1])]);
           System.out.println(person);
       }
       //A method to calculate the age of each family member in 2050.
       long method_array[]= new long [4];
            
       for (int i=0;i<4;i++){
           method_array[i]=age_in_2050(copy_array[i]);
           System.out.println(first_name_array[i]+" will be "+age_in_2050(copy_array[i])+" years old in 2050.");
       }
       for (int i=0;i<4;i++){
            switch(fav_season_array[i]){
                case("winter"):
                    System.out.println(first_name_array[i]+" likes snow");
                    break;
                case("autumn"):
                    System.out.println(first_name_array[i]+" likes rain");
                    break;
                case("spring"):
                    System.out.println(first_name_array[i]+" likes flowers");
                    break;
                case("summer"):
                    System.out.println(first_name_array[i]+" likes swimming");
                    break;
            }
       }   
   } 

    private static long age_in_2050(long age) {
        return age+2050-2018;
    }  
    
    
}