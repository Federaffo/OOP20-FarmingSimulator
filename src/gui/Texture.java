package gui;

import item.Texturable;

public enum Texture implements Texturable {

    /**
     * This is all the player graphics.
     */
    PLAYER, PLAYER_LEFT, PLAYER_LEFT2, PLAYER_RIGHT, PLAYER_RIGHT2, PLAYER_UP, PLAYER_UP2, PLAYER_DOWN, PLAYER_DOWN2,

    /**
     * This is the graphics of the info panel.
     */
    WASD, E_KEY, F_KEY, MOUSE_WHEEL,

    /**
     * This is the graphics of the HUD.
     */
    LABELHUD, MONEY, SEED, TIME, INFO, EMPTY, LOCK, TREE;

}
