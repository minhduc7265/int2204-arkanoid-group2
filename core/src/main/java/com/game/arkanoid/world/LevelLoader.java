package com.game.arkanoid.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.game.arkanoid.leveldata.BrickInformation;
import com.game.arkanoid.leveldata.LevelInfo;
import com.game.arkanoid.model.Brick;
import com.game.arkanoid.model.BrickFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LevelLoader {

    public List<Brick> loadLevel(int level) {
        String filePath = "level/" + "level" + Integer.toString(level) + ".json";
        List<Brick> newBricks = new ArrayList<>();
        try {
            Json json = new Json();
            FileHandle fileHandle = Gdx.files.internal(filePath);
            LevelInfo levelInfo = json.fromJson(LevelInfo.class, fileHandle);
            if (levelInfo != null) {
                System.out.println(levelInfo.level);
                Iterator<BrickInformation> brick = levelInfo.brick.iterator();
                while (brick.hasNext()) {
                    BrickInformation temp = brick.next();
                    Brick brick_temp = BrickFactory.call(temp.power,temp.item, temp.ox, temp.oy,80,32);
                    newBricks.add(brick_temp);
                }
            }
            return newBricks;

        } catch (Exception e) {
            Gdx.app.error("File not found", e.getMessage());
            return null;
        }
    }

}
