package ro.ubbcluj.scs.map.character;

import ro.ubbcluj.scs.map.character.CanTalk;
import ro.ubbcluj.scs.map.character.Character;

public class Robot extends Character implements CanTalk {
    @Override
    public void fight() {
        System.out.println("Robot can fight.");
    }

    @Override
    public void play() {
        System.out.println("Robot can play.");
    }

    @Override
    public void talk() {
        System.out.println("Robot can talk.");
    }
}
