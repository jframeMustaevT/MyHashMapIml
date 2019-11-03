package part1.lesson03.task03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void put() {
        MyHashMap<Integer,String> myHashMap=new MyHashMap<>();
        myHashMap.put(1,"testing");
        myHashMap.put(2,"hash");
        myHashMap.put(3,"map");
        assertEquals(3,myHashMap.getSize());

    }

    @Test
    void get() {
        MyHashMap<Integer,String> myHashMap=new MyHashMap<>();
        myHashMap.put(1,"testing");
        myHashMap.put(2,"hash");
        myHashMap.put(3,"map");
        assertEquals("hash",myHashMap.get(2));
    }

    @Test
    void remove() {
        MyHashMap<Integer,String> myHashMap=new MyHashMap<>();
        myHashMap.put(1,"testing");
        myHashMap.put(2,"hash");
        myHashMap.put(3,"map");
        myHashMap.remove(3);
        assertEquals(2,myHashMap.getSize());
    }

    @Test
    void getSize() {
    }
}