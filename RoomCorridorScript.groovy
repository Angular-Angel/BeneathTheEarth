
import java.util.ArrayList;
import roguelikeengine.*;
import roguelikeengine.area.*;
import roguelikeengine.largeobjects.*;
import roguelikeengine.controller.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Greg
 */
class RoomCorridorScript implements MapScript {
    
    TerrainDefinition floor, wall, stairs;
    Game game;
    ArrayList<LocalArea> areas;
    
    public LocalArea generateArea(Game game, String args) {
        floor = game.registry.terrainTypes.get("Stone Floor");
        wall = game.registry.terrainTypes.get("Stone Wall");
        stairs = game.registry.terrainTypes.get("Stone Stairs");
        areas = new ArrayList<>();
        this.game = game;
        
        LocalArea ret = makeRoom(10, 10);
        
        areas.add(ret);
        
        areas.add(makeRoom(game.random.nextInt(7) +4, game.random.nextInt(7) +4));
        areas.add(makeRoom(game.random.nextInt(7) +4, game.random.nextInt(7) +4));
        areas.add(makeRoom(game.random.nextInt(7) +4, game.random.nextInt(7) +4));
        
        ret.setTerrain(3,3, stairs);
        
        addCorridors(ret, 4);
        
        for (int i = 1; i < 100 && i < areas.size(); i++) {
            addCorridors(areas.get(i), game.random.nextInt(5));
            populateRoom(areas.get(i));
        }
        
        return ret;
    }
    
    private LocalArea makeRoom(int width, int height) {
        
        LocalArea ret = new LocalArea(width, height, floor, "Start");
        111
        
        for (int i = 0; i < ret.getWidth(); i++) {
            ret.setTerrain(i, 0, wall);
            ret.setTerrain(i, ret.getHeight() - 1, wall);
        }
        
        for (int i = 0; i < ret.getHeight(); i++) {
            ret.setTerrain(0, i, wall);
            ret.setTerrain(ret.getWidth() - 1, i, wall);
        }
        
        return ret;
    }
    
    private LocalArea addCorridors(LocalArea ret, int corridors) {
        for (int i = 0; i < corridors; i++) {
            switch (game.random.nextInt(4)) {
                case 0: LocalArea corridor = makeRoom(3, game.random.nextInt(8) +3);
                    int x = game.random.nextInt(ret.getWidth()-2)+1;
                    if (ret.getTerrain(x, 0) == floor ||
                        ret.getTerrain(x-1, 0) == floor ||
                        ret.getTerrain(x+1, 0) == floor){
                        break;
                    }
                    ret.setTerrain(x, 0, floor);
                    corridor.setTerrain(1, corridor.getHeight() -1, floor);
                
                    ret.addBorder(corridor, x-1, -corridor.getHeight());
                    corridor.addBorder(ret, -(x-1), corridor.getHeight());
                    
                    LocalArea room;
                    switch(game.random.nextInt(2)){
                        case 0: 
                            room = makeRoom(game.random.nextInt(7) +4, game.random.nextInt(7) +4);
                            areas.add(room);
                            break;
                        case 1: room = areas.get(game.random.nextInt(areas.size()));
                    }
                    x = game.random.nextInt(room.getWidth()-2)+1;
                    if (room.getTerrain(x, room.getHeight() -1) == floor ||
                        room.getTerrain(x-1, room.getHeight() -1) == floor ||
                        room.getTerrain(x+1, room.getHeight() -1) == floor){
                        areas.add(corridor);
                        break;
                    }
                
                    room.setTerrain(x, room.getHeight() -1, floor);
                    corridor.setTerrain(1, 0, floor);
                
                    room.addBorder(corridor, (x-1), room.getHeight());
                    corridor.addBorder(room, -(x-1), -room.getHeight());
                    break;
                case 1: LocalArea corridor = makeRoom(game.random.nextInt(8) +3, 3);
                    int y = game.random.nextInt(ret.getHeight()-2)+1;
                    if (ret.getTerrain(ret.getWidth() -1, y) == floor ||
                        ret.getTerrain(ret.getWidth() -1, y -1) == floor ||
                        ret.getTerrain(ret.getWidth() -1, y +1) == floor){
                        break;
                    }
                    ret.setTerrain(ret.getWidth() -1, y, floor);
                    corridor.setTerrain(0, 1, floor);
                
                    ret.addBorder(corridor, ret.getWidth(), y-1);
                    corridor.addBorder(ret, -ret.getWidth(), -(y-1));
                    
                    LocalArea room;
                    switch(game.random.nextInt(2)){
                        case 0: 
                            room = makeRoom(game.random.nextInt(7) +4, game.random.nextInt(7) +4);
                            areas.add(room);
                            break;
                        case 1: room = areas.get(game.random.nextInt(areas.size()));
                    }
                    y = game.random.nextInt(room.getHeight()-2)+1;
                    if (room.getTerrain(0, y) == floor ||
                        room.getTerrain(0, y -1) == floor ||
                        room.getTerrain(0, y +1) == floor){
                        areas.add(corridor);
                        break;
                    }
                    
                    room.setTerrain(0, y, floor);
                    corridor.setTerrain(corridor.getWidth()-1, 1, floor);
                
                    room.addBorder(corridor, -corridor.getWidth(), y-1);
                    corridor.addBorder(room, corridor.getWidth(), -(y-1));
                    break;
                case 2:LocalArea corridor = makeRoom(3, game.random.nextInt(8) +3);
                    int x = game.random.nextInt(ret.getWidth()-2)+1;
                    if (ret.getTerrain(x, ret.getHeight() -1) == floor ||
                        ret.getTerrain(x -1, ret.getHeight() -1) == floor ||
                        ret.getTerrain(x +1, ret.getHeight() -1) == floor){
                        break;
                    }
                    ret.setTerrain(x, ret.getHeight() -1, floor);
                    corridor.setTerrain(1, 0, floor);
                
                    ret.addBorder(corridor, (x-1), ret.getHeight());
                    corridor.addBorder(ret, -(x-1), -ret.getHeight());
                    
                    LocalArea room;
                    switch(game.random.nextInt(2)){
                        case 0: 
                            room = makeRoom(game.random.nextInt(7) +4, game.random.nextInt(7) +4);
                            areas.add(room);
                            break;
                        case 1: room = areas.get(game.random.nextInt(areas.size()));
                    }
                    
                    x = game.random.nextInt(room.getWidth()-2)+1;
                    if (room.getTerrain(x, 0) == floor ||
                        room.getTerrain(x -1, 0) == floor ||
                        room.getTerrain(x +1, 0) == floor){
                        areas.add(corridor);
                        break;
                    }
                    
                    room.setTerrain(x, 0, floor);
                    corridor.setTerrain(1, corridor.getHeight() -1, floor);
                
                    room.addBorder(corridor, x-1, -corridor.getHeight());
                    corridor.addBorder(room, -(x-1), corridor.getHeight());
                    break;
                case 3: LocalArea corridor = makeRoom(game.random.nextInt(8) +3, 3);
                    int y = game.random.nextInt(ret.getHeight()-2)+1;
                    if (ret.getTerrain(0, y) == floor ||
                        ret.getTerrain(0, y +1) == floor ||
                        ret.getTerrain(0, y -1) == floor){
                        break;
                    }
                    ret.setTerrain(0, y, floor);
                    corridor.setTerrain(corridor.getWidth()-1, 1, floor);
                
                    ret.addBorder(corridor, -corridor.getWidth(), y-1);
                    corridor.addBorder(ret, corridor.getWidth(), -(y-1));
                    
                    LocalArea room;
                    switch(game.random.nextInt(2)){
                        case 0: 
                            room = makeRoom(game.random.nextInt(7) +4, game.random.nextInt(7) +4);
                            areas.add(room);
                            break;
                        case 1: room = areas.get(game.random.nextInt(areas.size()));
                    }
                    y = game.random.nextInt(room.getHeight()-2)+1;
                    if (room.getTerrain(room.getWidth() -1, y) == floor ||
                        room.getTerrain(room.getWidth() -1, y +1) == floor ||
                        room.getTerrain(room.getWidth() -1, y -1) == floor){
                        areas.add(corridor);
                        break;
                    }
                    
                    room.setTerrain(room.getWidth() -1, y, floor);
                    corridor.setTerrain(0, 1, floor);
                
                    room.addBorder(corridor, room.getWidth(), y-1);
                    corridor.addBorder(room, -room.getWidth(), -(y-1));
                    break;
            }
        }
        
        return ret;
        
    }
    
    private void populateRoom(LocalArea area) {
        int size = area.getWidth() * area.getHeight();
        size /= 9;
        size = game.random.nextInt(size);
        for (int i = 0; i < size; i++) {
        int x = game.random.nextInt(area.getWidth()), y = game.random.nextInt(area.getHeight());
            BodyDefinition enemyDef = game.registry.bodyTypes.get("Human");
            Body enemy = new Body("Enemy", new AreaLocation(area, x, y), 
                    enemyDef);
            area.addEntity(enemy);
//            game.clock.addActor(enemy);
        }
    }
	
}

