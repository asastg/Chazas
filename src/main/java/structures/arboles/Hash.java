package structures.arboles;
import entities.Chaza;
public class Hash {
    private HashData[] array;
    private int size, collisions;
    public Hash(int size){
        this.array = new HashData[size];
        this.size=size;
        this.collisions=0;
    }
    public void hashChaza(Chaza chaza, BSTnode node){
        int hash=0;
        String string = chaza.getName();
        for (int i=(string.length()-1);i>=0;i--){
            int number = (int) string.charAt(i);
            hash=((hash*43)+ number)%(size);
        }
        
        if(array[hash]==null){
            ;
            array[hash]=new HashData(chaza, node);
        }
        else{
            this.collisions++;
            array[hash].linkHashing(chaza, node);
        }
    }
    public HashNode findChaza(String string){
        int hash = 0;
        for (int i=(string.length()-1);i>=0;i--){
            int number = (int) string.charAt(i);
            hash=((hash*43)+ number)%size;
        }

        return array[hash].returnData(string);
    }
    public int getCollisions(){
        return this.collisions;
    }
}
