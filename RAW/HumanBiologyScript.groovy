
import roguelikeengine.item.*;
import java.util.Random;
import roguelikeengine.largeobjects.*;
import roguelikeengine.stat.*;


class HumanBiologyScript implements BiologyScript {
    
    public boolean isAlive(Body b) {
        return (b.stats.getScore("HP") > 0);
    }
    
    public void step(Body b) {
    }
    
    public void beAttacked(Body b, Attack a) {
        b.takeAttack(a);
    }
}
