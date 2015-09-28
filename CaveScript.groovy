
package roguelikeengine.area;


import roguelikeengine.*;
import roguelikeengine.area.*;
import roguelikeengine.largeobjects.*;
import roguelikeengine.controller.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author angle
 */




public class CaveScript implements MapScript {
    Random rand = new Random();

    public LocalArea map;

    public int PercentAreWalls;
    public TerrainDefinition floor, wall;

    public LocalArea generateArea(Game game, String args) {
        floor = game.registry.terrainTypes.get("Stone Floor");
        wall = game.registry.terrainTypes.get("Stone Wall");
        PercentAreWalls = 40;

        map = new LocalArea(40, 40, floor, "Cavern");

        RandomFillMap();
        
        MakeCaverns();
        MakeCaverns();
        MakeCaverns();
        
        return map;
    }

    public void MakeCaverns()
    {
        TerrainDefinition[][] newTerrain = new TerrainDefinition[map.getWidth()][map.getHeight()];
        for(int row = 0; row < map.getWidth(); row++)
        {
            for(int column = 0; column < map.getHeight(); column++)
            {
                newTerrain[column][row] = PlaceWallLogic(column,row);
            }
        }
        for(int row = 0; row < map.getWidth(); row++)
        {
            for(int column = 0; column < map.getHeight(); column++)
            {
                map.setTerrain(column, row, newTerrain[column][row]);
            }
        }
    }

    public TerrainDefinition PlaceWallLogic(int x,int y)
    {
        int numWalls = GetAdjacentWalls(x,y,1,1);


        if(map.getTerrain(x, y) == wall)
        {
            if( numWalls >= 4 )
            {
                return wall;
            }
            if(numWalls<2)
            {
                return floor;
            }

        }
        else
        {
            if(numWalls>=5)
            {
                return wall;
            }
        }
        return floor;
    }

    public int GetAdjacentWalls(int x, int y, int scopeX, int scopeY)
    {
        int startX = x - scopeX;
        int startY = y - scopeY;
        int endX = x + scopeX;
        int endY = y + scopeY;

        int iX = startX;
        int iY = startY;

        int wallCounter = 0;

        for(iY = startY; iY <= endY; iY++) {
            for(iX = startX; iX <= endX; iX++)
            {
                if(!(iX==x && iY==y))
                {
                    if(IsWall(iX,iY))
                    {
                        wallCounter += 1;
                    }
                }
            }
        }
        return wallCounter;
    }

    boolean IsWall(int x,int y)
    {
        // Consider out-of-bound a wall
        if(IsOutOfBounds(x,y))
        {
            return true;
        }

        if(map.getTerrain(x, y) == wall)
        {
            return true;
        }

        if(map.getTerrain(x, y) == floor)
        {
            return false;
        }
        return false;
    }

    boolean IsOutOfBounds(int x, int y)
    {
        if( x<0 || y<0 )
        {
            return true;
        }
        else if( x>map.getWidth()-1 || y>map.getHeight()-1 )
        {
            return true;
        }
        return false;
    }

    public void PrintMap()
    {
            System.out.println(MapToString());
    }

    String MapToString()
    {
        String returnString = "Width:" + MapWidth + "\tHeight:" +
                              MapHeight + "\t% Walls:" + PercentAreWalls;

        for(int row=0; row < map.getHeight(); row++ ) {
            for(int column = 0; column < map.getWidth(); column++ )
            {
                returnString += map.getTerrain(column, row).getChar();
            }
            returnString += "\n";
        }
        return returnString;
    }

    public void RandomFillMap()
    {
        // New, empty map
        map.fill(floor);

        int mapMiddle = 0; // Temp variable
        for(int row=0; row < map.getHeight(); row++) {
            for(int column = 0; column < map.getWidth(); column++)
            {
                // If coordinants lie on the the edge of the map (creates a border)
                if(column == 0)
                {
                    map.setTerrain(column, row, wall);
                }
                else if (row == 0)
                {
                    map.setTerrain(column, row, wall);
                }
                else if (column == map.getWidth()-1)
                {
                    map.setTerrain(column, row, wall);
                }
                else if (row == map.getHeight()-1)
                {
                    map.setTerrain(column, row, wall);
                }
                // Else, fill with a wall a random percent of the time
                else
                {
                    mapMiddle = (map.getHeight() / 2);

                    if(row == mapMiddle)
                    {
                        map.setTerrain(column, row, floor);
                    }
                    else
                    {
                        map.setTerrain(column, row, RandomPercent(PercentAreWalls));
                    }
                }
            }
        }
    }

    TerrainDefinition RandomPercent(int percent)
    {
        if(percent >= rand.nextInt(100))
        {
                return wall;
        }
        return floor;
    }
    
    public void cellularAutomata() {
        
    }
    
}

