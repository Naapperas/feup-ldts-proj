package pt.up.fe.ldts.model.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.model.Element;

public class Button extends Element {

    private String text;
    private boolean selected;

    public Button(int x, int y, String text){
        super(x, y);
        this.text = text;
        this.selected = false;
    }
    @Override
    public void render(TextGraphics tg){
        var previousForegroundColor = tg.getForegroundColor();
        var previousBackgroundColor = tg.getBackgroundColor();
        tg.setBackgroundColor(TextColor.Factory.fromString(TextColor.ANSI.BLUE.name()));
        tg.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));

        if (this.selected){
            tg.setBackgroundColor(TextColor.Factory.fromString("#FFFF00"));
            tg.setForegroundColor(TextColor.Factory.fromString(TextColor.ANSI.BLUE.name()));
        }

        tg.fillRectangle(new TerminalPosition(this.getX(), this.getY()), new TerminalSize(20, 7), ' ');
        tg.putString(this.getX() + 3,this.getY() + 3, this.text);

        tg.setForegroundColor(previousForegroundColor);
        tg.setBackgroundColor(previousBackgroundColor);
    }


    public boolean isSelected() {
        return selected;
    }

    public void select() {
        this.selected = true;
    }

    public void unSelect() {
        this.selected = false;
    }
}
