package net.lighthouse.levels;

import acm.util.RandomGenerator;
import net.lighthouse.model.BBlock;
import net.lighthouse.model.BlockList;

import java.awt.Color;

/**
 * A level is maximal 5 rows and 7 columns. If a block should be left empty set his color to null.
 *
 * @author Christoph Fricke
 */
public final class LevelManager {
    // All levels are stored as a class variable. This way there is no need to initialize them whenever
    // the getRandomLevel function get called.
    private static Color[][] level0 = {
        {Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN},
        {Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW},
        {Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN},
        {Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW},
        {Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN},
    };

    private static Color[][] level1 = {
        {Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN},
        {null, null, null, null, null, null, null},
        {Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW},
        {null, null, null, null, null, null, null},
        {Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN},
    };

    private static Color[][] level2 = {
        {Color.GREEN, null, Color.GREEN, null, Color.GREEN, null, Color.GREEN},
        {null, Color.CYAN, null, Color.CYAN, null, Color.CYAN, null},
        {Color.GREEN, null, Color.GREEN, null, Color.GREEN, null, Color.GREEN},
        {null, Color.CYAN, null, Color.CYAN, null, Color.CYAN, null},
        {Color.GREEN, null, Color.GREEN, null, Color.GREEN, null, Color.GREEN},
    };

    private static Color[][] level3 = {
        {null, null, null, Color.GREEN, null, null, null},
        {null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null},
    };

    /**
     * We do not want instances of LevelManager.
     */
    private LevelManager() {}

    /**
     * Get a BlockList for a random level.
     *
     * @return BlockList that can be used to build a level
     */
    public static BlockList getRandomLevel() {
        RandomGenerator rnd = RandomGenerator.getInstance();
        switch (rnd.nextInt(0, 2)) {
            case 0:
                return makeBlockList(level0);
            case 1:
                return makeBlockList(level1);
            case 2:
                return makeBlockList(level2);
            case 3:
                return makeBlockList(level3);
            default:
                return makeBlockList(level0);
        }
    }

    /**
     * Creates a BlockList from a given color array. If the color is
     * null no block will be drawn.
     *
     * @param level level which should be converted
     *
     * @return BlockList that forms the level
     */
    private static BlockList makeBlockList(Color[][] level) {
        assert level != null : "levels can not be null";

        BlockList list = new BlockList();

        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                if (level[y][x] != null) {
                    BBlock block = new BBlock(x * 80, y * 60, 80, 60, level[y][x]);
                    list.add(block);
                }
            }
        }

        return list;
    }
}
