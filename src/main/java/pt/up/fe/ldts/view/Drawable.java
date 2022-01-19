package pt.up.fe.ldts.view;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public interface Drawable {
    void render(TextGraphics tg) throws IOException;
}
