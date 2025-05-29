import map.MyMap;
import domain.Student;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Student s1= new Student("Dan", 4.5f);
        Student s2= new Student("Ana", 8.5f);
        Student s3= new Student("Daniel", 4.5f);
        Student s4= new Student("Bogdan", 5f);

        //System.out.println(s1);
        Set<Student> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList(s1,s2,s3, s4));
        /*hashSet.add(s1);
        hashSet.add(s2);
        hashSet.add(s3);*/
        System.out.println("\nhashSet");
        System.out.println(hashSet);

        Set<Student> treeSet = new TreeSet<>(new NameComparator());
        treeSet.addAll(Arrays.asList(s1,s2,s3, s4));
        System.out.println("\ntreeSet");
        System.out.println(treeSet);

        Map<String, Student> hashMap = new HashMap<>();
        hashMap.put(s1.getNume(), s1);
        hashMap.put(s2.getNume(), s2);
        hashMap.put(s3.getNume(), s3);
        hashMap.put(s4.getNume(), s4);

        System.out.println("\nhashMap");
        for(Map.Entry<String,Student> kv : hashMap.entrySet()) {
            System.out.println((kv.getKey()+", "+kv.getValue()));
        }

        Map<String, Student> treeMap = new TreeMap<>();
        treeMap.put(s1.getNume(), s1);
        treeMap.put(s2.getNume(), s2);
        treeMap.put(s3.getNume(), s3);
        treeMap.put(s4.getNume(), s4);
        System.out.println("\ntreeMap");
        for(Map.Entry<String,Student> kv:treeMap.entrySet())
        {
            System.out.println((kv.getKey()+", "+kv.getValue()));
        }

        System.out.println("\nMyMap");
        MyMap myMap = new MyMap();
        List<Student> list = getList();
        for(Student student : list) {
            myMap.add(student);
        }
        myMap.afisare();

        System.out.println("\nOrdered students");
        for(Map.Entry<Integer, List<Student>> entry : myMap.getEntries()) {
            List<Student> listStud = entry.getValue();
            listStud.sort(new NameComparator());
            System.out.println(entry.getKey()+" "+listStud);
        }

        /*myMap.add(s1);
        myMap.add(s2);
        myMap.add(s3);
        myMap.add(s4);*/

    }


    public static List<Student> getList() {
        List<Student> l=new ArrayList<Student>();
        l.add(new Student("Marcel",9.7f));
        l.add(new Student("Ana",7.3f));
        l.add(new Student("Vlad",6f ));
        l.add(new Student("Bogdan",6.9f));
        l.add(new Student("Ilie",9.5f));
        l.add(new Student("Andrei",9.9f));
        return l;
    }

    static class NameComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.getNume().compareTo(o2.getNume());
        }
    }
}