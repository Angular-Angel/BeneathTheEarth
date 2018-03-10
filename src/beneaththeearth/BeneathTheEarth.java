/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beneaththeearth;

import roguelikeengine.display.RoguelikeInterface;
import roguelikeengine.display.DisplayChar;
import roguelikeengine.Game;
import roguelikeengine.largeobjects.Creature;
import roguelikeengine.area.AreaLocation;
import roguelikeengine.area.LocalArea;
import roguelikeengine.controller.Controller;
import generation.GenerationProcedure;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import roguelikeengine.controller.Player;
import roguelikeengine.area.Area;

/**
 *
 * @author Greg
 */
public class BeneathTheEarth extends Game {
    
    public BeneathTheEarth() {
        super();
        registry.readJSONMaterials(new File("RAW/MaterialDefinitions.json"));
        registry.readJSONItemDefs(new File("RAW/ItemDefinitions.json"));
        registry.readJSONBodyDefs(new File("RAW/BodyDefinitions.json"));
        registry.readJSONTerrainDefs(new File("RAW/TerrainDefinitions.json"));
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
            GenerationProcedure<Area> script = null;
            Area area;
            LocalArea start;
            Creature body;
            Controller player;
            clock.clearActors();
            switch (display.getKeyChar()) {
                case 'q': System.exit(0); return;
                case '1': 
                    script = (GenerationProcedure<Area>) registry.readGroovyScript(new File("RAW/RoomCorridorScript.groovy"));
                    break;
                case '2':
                    clock.clearActors();
                    script = (GenerationProcedure<Area>) registry.readGroovyScript(new File("RAW/CaveScript.groovy"));
                    break;
            }
            
            area = script.generate();
            start = area.start;
            body = new Creature("Player", new AreaLocation(start, 5, 5), 
                   registry.bodyTypes.get("Human"));
            start.addEntity(body);
            player = new Player(body, game);
            player.setBody(body);
            player.setGame(this);
            clock.addActor(player);
            for (Controller c : area.controllers) 
                clock.addActor(c);
            clock.play();
        }
        
    }
    
}
