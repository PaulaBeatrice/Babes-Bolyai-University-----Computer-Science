package ro.ubbcluj.scs.map.character;

public class IronMan extends Character implements CanFly, CanTalk {
    @Override
    public void fly() {
        System.out.println("IronMan can fly.");
    }

    @Override
    public void talk() {
        System.out.println("IronMan can talk.");
    }

    @Override
    public void fight() {
        System.out.println("IronMan can fight.");
    }

    @Override
    public void play() {
        System.out.println("IronMan can play.");
    }
}
