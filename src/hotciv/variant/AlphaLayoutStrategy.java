package hotciv.variant;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

/**
 * Created by Liam on 26-11-2015.
 */
public class AlphaLayoutStrategy implements LayoutStrategy {


    @Override
    public void generateWorld(Game game) {
        String[] layout = new String[]{
                "P.PPPPPPPPPPPPPP",
                "hPPPPPPPPPPPPPPP",
                "PPMPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
                "PPPPPPPPPPPPPPPP",
        };

        game.getUnitMap().put(new Position(2,0), new UnitImpl(GameConstants.ARCHER, Player.RED, 2,3));
        game.getUnitMap().put(new Position(3,2), new UnitImpl(GameConstants.LEGION, Player.BLUE, 4,2));
        game.getUnitMap().put(new Position(4,3), new UnitImpl(GameConstants.SETTLER, Player.RED, 0,3));
        game.getCityMap().put(new Position(1, 1), new CityImpl(Player.RED));
        game.getCityMap().put(new Position(4, 1), new CityImpl(Player.BLUE));

        setupWorld(layout, game);
    }

    public void setupWorld(String[] layout, Game game) {
        String line;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            line = layout[r];
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                char atTileChar = line.charAt(c);

                Position p = new Position(c, r);
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
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.BLUE, 2,3));
                }
                if (atTileChar == 'a') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.RED, 2,3));
                }
                if (atTileChar == 'L') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.BLUE, 2,3));
                }
                if (atTileChar == 'l') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.RED, 2,3));
                }
                if (atTileChar == 'S') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.BLUE, 2,3));
                }
                if (atTileChar == 's') {
                    game.getUnitMap().put(p, new UnitImpl(GameConstants.ARCHER, Player.RED, 2,3));
                }

            }
        }
    }
}
