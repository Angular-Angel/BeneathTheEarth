/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelikeengine.stat;

import java.util.Random;

/**
 *
 * @author Greg
 */
public class DiceStat implements Stat {
    private int diceNumber, diceSize;

    @Override
    public int getScore() {
        Random random = new Random();
        int ret = 0;
        for (int i = 0; i < diceNumber; i++) {
            ret += random.nextInt(diceSize);
        }
        return ret;
    }

    @Override
    public void setContainer(StatContainer i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refactor() throws NoSuchStatException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modify(float change) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifyBase(float change) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stat copy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
