/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beneaththeearth;

import roguelikeengine.*;
import roguelikeengine.largeobjects.Body;
import roguelikeengine.display.*;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import roguelikeengine.area.AreaLocation;
import roguelikeengine.area.LocalArea;
import roguelikeengine.area.MapScript;
import roguelikeengine.controller.Controller;
import roguelikeengine.controller.DijkstraMap;

/**
 *
 * @author Greg
 */
public class BeneathTheEarth extends Game {
    
    public BeneathTheEarth() {
        super();
        registry.readJSONMaterials(new File("MaterialDefinitions.json"));
        registry.readJSONItemDefs(new File("ItemDefinitions.json"));
        registry.readJSONBodyDefs(new File("BodyDefinitions.json"));
        registry.readJSONTerrainDefs(new File("TerrainDefinitions.json"));
        display = new RoguelikeInterface("Beneath the Earth", 193, 47, 
                Color.white, Color.black, new Font("Liberation Mono", Font.PLAIN, 12));
    }
    
    public static void main(String[] args) {
        BeneathTheEarth game = new BeneathTheEarth();
        game.start();
    }

    @Override
    public void start() {
        
        while (true) {
            display.setAll(new DisplayChar(' ', Color.black));
            display.writeString(5, 8, "1 for Rooms and Corridors, 2 for caverns, and q to go back.");
            display.repaint();
            MapScript script;
            LocalArea start;
            Body body;
            Controller player;
            switch (display.getKey()) {
                case 'q': System.exit(0); return;
                case '1': 
                    clock.clearActors();
                    script = (MapScript) registry.readGroovyScript(new File("RoomCorridorScript.groovy"));
                    start = script.generateArea(this, "");
                    body = new Body("Player", new AreaLocation(start, 5, 5), 
                           registry.bodyTypes.get("Human"));
                    start.addEntity(body);
                    player = (Controller) registry.readGroovyScript(new File("Player.groovy"));
                    player.setBody(body);
                    player.setGame(this);
                    clock.addActor(player);
                    clock.play();
                    break;
                case '2':
                    clock.clearActors();
                    script = (MapScript) registry.readGroovyScript(new File("CaveScript.groovy"));
                    start = script.generateArea(this, "");
                    body = new Body("Player", new AreaLocation(start, 5, 5), 
                           registry.bodyTypes.get("Human"));
                    start.addEntity(body);
                    player = (Controller) registry.readGroovyScript(new File("Player.groovy"));
                    player.setBody(body);
                    player.setGame(this);
                    clock.addActor(player);
                    clock.play();
                    break;
            }
        }
        
    }
    
}
