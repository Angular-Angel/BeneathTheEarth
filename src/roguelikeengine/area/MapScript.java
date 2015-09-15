/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package roguelikeengine.area;

import roguelikeengine.Game;
import roguelikeengine.Registry;

/**
 *
 * @author Greg
 */
public interface MapScript {
    
    public LocalArea generateArea(Game game, String args);
    
}
