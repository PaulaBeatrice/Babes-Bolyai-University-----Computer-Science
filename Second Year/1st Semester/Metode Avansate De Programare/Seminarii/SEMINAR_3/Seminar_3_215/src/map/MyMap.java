package map;

import domain.Student;

import java.util.*;

public class MyMap {
    private Map<Integer, List<Student>> studentMap;

    public MyMap() {
        studentMap = new TreeMap<>(new StudentComparator());
    }

    static class StudentComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1;
        }
    }

    public void add(Student student) {
        Integer mediaRot = student.getMediaRotunjita();
        List<Student> lista = studentMap.get(mediaRot);
        if(lista==null) {
            lista = new ArrayList<Student>();
            studentMap.put(mediaRot, lista);
        }
        lista.add(student);
    }

    public void afisare() {
        for(Map.Entry<Integer, List<Student>> kv : studentMap.entrySet()) {
            System.out.println(kv.getKey()+" "+kv.getValue().toString());
        }
    }

    public Set<Map.Entry<Integer, List<Student>>> getEntries() {
        return studentMap.entrySet();
    }


}
