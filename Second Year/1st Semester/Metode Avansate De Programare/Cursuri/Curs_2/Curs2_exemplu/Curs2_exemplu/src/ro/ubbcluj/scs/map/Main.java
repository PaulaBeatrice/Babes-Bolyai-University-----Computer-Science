package ro.ubbcluj.scs.map;

import ro.ubbcluj.scs.map.character.Adventure;
import ro.ubbcluj.scs.map.character.Character;
import ro.ubbcluj.scs.map.character.IronMan;

public class Main {
    public static void main(String[] args) {
        IronMan ironMan = new IronMan();
//        Character ironMan = new IronMan();
        Adventure.fightAdventure(ironMan);
        Adventure.flyAdventure(ironMan);
        Adventure.talkAdventure(ironMan);
        Adventure.fightCharacter(ironMan);
    }
}