package part1.lesson03.task03;
/**
 * Created by Mustaev Takhir
 */
public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer,String> myHashMap=new MyHashMap<>(4,0.75f);
        myHashMap.put(1,"Takhir");
        myHashMap.put(2,"Jack");
        myHashMap.put(4,"Jayson");
        myHashMap.put(5,"Rick");
        System.out.println(myHashMap.get(5));
        System.out.println(myHashMap.getSize());
        System.out.println(myHashMap.remove(5));
        System.out.println(myHashMap.getSize());
        System.out.println(myHashMap);

            }
    }














