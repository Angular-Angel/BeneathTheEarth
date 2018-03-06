
import roguelikeengine.item.*;
import java.util.Random;
import roguelikeengine.largeobjects.*;
import roguelikeengine.stat.*;


class HumanBiologyScript implements BiologyScript {
    
    public boolean isAlive(Creature creature) {
        return (creature.getBody().stats.getScore("HP") > 0);
    }
    
    public void step(Creature creature) {
    }
    
    public void beAttacked(Creature creature, Attack a) {
        creature.getBody().takeAttack(a);
    }
    
    public void die() {
    }
}
