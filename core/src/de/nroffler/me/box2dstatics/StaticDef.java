package de.nroffler.me.box2dstatics;

import com.badlogic.gdx.math.Vector2;

public class StaticDef {

    public Vector2 position;
    public Class<?> type;
    public int meta;

    public StaticDef(Vector2 position, Class<?> type, int meta){
        this.position = position;
        this.type = type;
        this.meta = meta;
    }

}
