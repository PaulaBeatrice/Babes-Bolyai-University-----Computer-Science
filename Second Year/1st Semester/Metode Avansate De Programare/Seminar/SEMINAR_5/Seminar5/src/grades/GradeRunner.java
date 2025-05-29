package grades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GradeRunner {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<Student>();
        Student s1 = new Student(212, "Ana");
        s1.setId(1L);
        Student s2 = new Student(212, "Dan");
        s2.setId(2L);
        Student s3 = new Student(214, "Paula");
        s3.setId(3L);
        Student s4 = new Student(215, "Mircea");
        s3.setId(4L);
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);

        List<Tema> temaList = new ArrayList<Tema>();
        Tema t1 = new Tema("1", "la MAP");
        Tema t2 = new Tema("2", "la SO");
        Tema t3 = new Tema("3", "la ASC");
        Tema t4 = new Tema("4", "la MAP");
        temaList.addAll(Arrays.asList(t1,t2,t3,t4));

        List<Note> notaLista = new ArrayList<Note>();
        Note n1 = new Note("Popescu", s1, t1, 9d);
        Note n2 = new Note("Popescu", s2, t2, 10d);
        Note n3 = new Note("Vancea", s3, t3, 7d);
        Note n4 = new Note("Ionescu", s4, t4, 8d);
        Note n5 = new Note("Ionescu", s2, t1, 9d);
        notaLista.add(n1);
        notaLista.add(n2);
        notaLista.add(n3);
        notaLista.add(n4);
        notaLista.add(n5);

       // report1(notaLista);
       // report2(notaLista);
       // report3(notaLista, t1);

    }

    private static void report1(List<Note> notaList){
        Predicate<Note> byGroup = x->x.getStudent().getGroup()==212;
        Predicate<Note> byProfesor = x->x.getProfesor().equals("Popescu");
        Predicate<Note> filter = byGroup.and(byProfesor);
        notaList.stream()
                .filter(filter)
                .map(x->
                    new NotaDTO(x.getStudent().getName(), x.getValue(), x.getTema().getId(), x.getProfesor())
                )
                .forEach(x -> System.out.println(x.toString()));
    }

    private static void report2(List<Note> notaList) {//media notelor de lab pt fiecare student
        Map<Student, List<Note>> note = notaList.stream()
                .collect(Collectors.groupingBy(x->x.getStudent()));
        note.entrySet()
                .forEach(x ->{
                    System.out.println(x.getKey().getName());
                    int count = x.getValue().size();
                    double sum = x.getValue().stream()
                            .map(y -> y.getValue())
                            .reduce(0d, (a,b)->a + b);
                    System.out.println(sum / count);
                });
    }

    private static void report3(List<Note> notaList, String idtema){
//        Predicate<Note> byId = x->x.getTema().getId().equals(tema.getId());
//        Predicate<Note> byDesc = x->x.getTema().getDesc().equals(tema.getDesc());
//        Predicate<Note> byTema = byId.and(byDesc);
//        List<Note> note = notaList.stream().filter(byTema).toList();
//        System.out.println(note.stream()
//                .map(y -> y.getValue())
//                .reduce(0d, (a,b) -> (a + b))/note.size()
//        );
        Predicate<Note> byTema = x -> x.getTema().getId().equals(idtema);
        Double d = notaList.stream().filter(byTema).map(x -> x.getValue())
                .reduce(0d, (x,y) -> x + y/notaList.stream().filter(byTema).count());
    }

    private static void report4(List<Note> notaList, Tema tema){
     //   Map <Tema, List<Note>> map = notaList.stream().collect()
    }
}
