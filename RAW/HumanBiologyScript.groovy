
import roguelikeengine.item.*;
import java.util.Random;
import roguelikeengine.largeobjects.*;
import roguelikeengine.stat.*;


class HumanBiologyScript implements BiologyScript {
    
    public boolean isAlive(Body body) {
        return (body.getBody().stats.getScore("HP") > 0);
    }
    
    public void step(Body body) {
    }
    
    public void beAttacked(Body body, Attack a) {
        body.getBody().takeAttack(a);
    }
    
    public void die() {
    }
}
