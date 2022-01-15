package pt.up.fe.ldts.model.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.model.Element;

public class Button extends Element {

    private String text;

    public Button(int x, int y, String text){
        super(x, y);
        this.text = text;
    }
    @Override
    public void render(TextGraphics tg){
        tg.setBackgroundColor(TextColor.Factory.fromString(TextColor.ANSI.BLUE.name()));
        tg.fillRectangle(new TerminalPosition(this.getX(), this.getY()), new TerminalSize(20, 7), ' ');

        var previousForegroundColor = tg.getForegroundColor();
        tg.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        tg.putString(this.getX() + 3,this.getY() + 3, this.text);
        tg.setForegroundColor(previousForegroundColor);
    }
}
