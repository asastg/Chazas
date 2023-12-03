package entities;
import java.util.Scanner;

public class Admin extends User{
    
    private Chaza[] chazasAdmin;
    private int chazaCounter;

    public Admin(String name, String username, String password) {
        super(name, username, password);
        this.chazasAdmin = new Chaza[1000];
        this.chazaCounter=0;
    }

    public Chaza[] getChazasAdmin() {
        return chazasAdmin;
    }

    public void setChazasAdmin(Chaza[] chazasAdmin) {
        this.chazasAdmin = chazasAdmin;
    }

    public void registerChaza(){

       if(chazaCounter<10001){

           String name = "";
           String foodType = "";
           String location = "";

           this.chazasAdmin[chazaCounter]=new Chaza(name, location,foodType,this);
       }

    }
    public Chaza choseChaza(int n){
        if(this.chazasAdmin[n]!=null){
            return chazasAdmin[n];
        }
        else{
            throw new RuntimeException("No chaza in selected index");
        }
    }

    public void editChazaName(String name){
        for(int i=0;i<3;i++){
            if(chazasAdmin[i]!=null){
                System.out.println("Chaza #"+i+" is: "+chazasAdmin[i].getName());
            }
        }
        int chazaIndex;
        System.out.println("Enter desired chaza for editing");
        Scanner scanner = new Scanner(System.in);
        chazaIndex=scanner.nextInt();

        choseChaza(chazaIndex).setName(name);;
    }

    public void editChaza(String name, String foodType){
        for(int i=0;i<3;i++){
            if(chazasAdmin[i]!=null){
                System.out.println("Chaza #"+i+" is: "+chazasAdmin[i].getName());
            }
        }
        int chazaIndex;
        System.out.println("Enter desired chaza for editing");
        Scanner scanner = new Scanner(System.in);
        chazaIndex=scanner.nextInt();

        choseChaza(chazaIndex).setName(name);
        choseChaza(chazaIndex).setFoodType(foodType);
    }

    public void editChazaType(String foodType){
        for(int i=0;i<3;i++){
            if(chazasAdmin[i]!=null){
                System.out.println("Chaza #"+i+" is: "+chazasAdmin[i].getName());
            }
        }
        int chazaIndex;
        
        System.out.println("Enter desired chaza for editing");
        Scanner scanner = new Scanner(System.in);
        chazaIndex=scanner.nextInt();
        
        choseChaza(chazaIndex).setFoodType(foodType);
    }
}
