
import roguelikeengine.item.*;
import java.util.Random;
import roguelikeengine.largeobjects.*;
import stat.*;


class BrittleDamageScript implements DamageScript {

    public void run(Attack a, Item i) {
        i.stats.getStat("HP").modify("Damage", -a.stats.getScore("Damage"));
    }

}
