package ro.ubbcluj.scs.map.character;

public class Character implements CanFight {
    @Override
    public void fight() {
        System.out.println("Character can fight.");
    }

    @Override
    public void play() {
        System.out.println("Character can play.");
    }
}
