package hotciv.variant;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by Liam on 26-11-2015.
 */
public class CustomLayoutStrategy implements LayoutStrategy {

    private String[] layout;

    public CustomLayoutStrategy(String[] layout){
        this.layout = layout;
    }

    @Override
    public void generateWorld(Game game){
        setupWorld(layout, game);
    }

    public void setupWorld(String[] layout, Game game) {
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            line = layout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char atTileChar = line.charAt(c);

                Position p = new Position(r, c);
                if (atTileChar == '.') {
                    game.getTileMap().put(p, new TileImpl(GameConstants.OCEANS));
                }
                if (atTileChar == 'P') {
                    game.getTileMap().put(p, new TileImpl(GameConstants.PLAINS));
                }
                if (atTileChar == 'h') {
                    game.getTileMap().put(p, new TileImpl(GameConstants.HILLS));
                }
                if (atTileChar == 'M') {
                    game.getTileMap().put(p, new TileImpl(GameConstants.MOUNTAINS));
                }
                if (atTileChar == 'f') {
                    game.getTileMap().put(p, new TileImpl(GameConstants.FOREST));
                }

                if (atTileChar == 'B') {
                    game.getCityMap().put(p, new CityImpl(Player.BLUE));
                }
                if (atTileChar == 'R') {
                    game.getCityMap().put(p, new CityImpl(Player.RED));
                }

                if (atTileChar == 'A') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.BLUE));
                }
                if (atTileChar == 'a') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.RED));
                }
                if (atTileChar == 'L') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.BLUE));
                }
                if (atTileChar == 'l') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.RED));
                }
                if (atTileChar == 'S') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.BLUE));
                }
                if (atTileChar == 's') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.RED));
                }

            }
        }
    }
}
