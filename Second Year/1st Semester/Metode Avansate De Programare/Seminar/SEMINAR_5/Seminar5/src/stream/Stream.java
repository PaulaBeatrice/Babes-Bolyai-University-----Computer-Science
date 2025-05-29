package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Stream {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("asf", "bcd", "asd", "bbb");
        String rez = list.stream()
                .filter(x -> {
                    return x.startsWith("b");
                })
                .map(x -> {
                    return x.toUpperCase();
                })
                .reduce("", (x,y)->x+y);
        System.out.println("A: ");
        System.out.println(rez);

        System.out.println("\nB: ");
        list.stream()
                .filter(x->{
                    return x.startsWith("b");
                })
                .map(x -> {
                    return x.toUpperCase();
                })
                .forEach(System.out::println);

        System.out.println("\nC:");
        Optional<String> rez2 = list.stream()
                .filter(x->{
                    return x.startsWith("b");
                })
                .map(x -> {
                    return x.toUpperCase();
                })
                .reduce( (x,y)->x+y);
        if(!rez2.isEmpty())
            System.out.println(rez2.get());
        rez2.ifPresent(x ->System.out.println(x));
    }
}
