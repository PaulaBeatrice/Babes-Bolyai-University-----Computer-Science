package ro.ubbcluj.scs.map.character;

public interface CanFight extends CanPlay {
    default void fight(){
        System.out.println("Fight");
    }
}
