package pt.up.fe.ldts.model.menus;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.model.game.Element;
import pt.up.fe.ldts.model.map.MapConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Button extends Element {

    private final List<String> text;
    private int buttonWidth = 20, buttonHeight = 7;
    private boolean selected;

    public Button(int x, int y, String text){
        super(x, y);
        this.text = new ArrayList<>();
        this.selected = false;

        int tmpWidth = 0;
        StringBuilder tmpStr = new StringBuilder();
        for (char c : text.toCharArray()) {
            tmpWidth++;
            if (c == '\n') {
                buttonWidth = Math.max(buttonWidth, tmpWidth);
                this.text.add(tmpStr.toString());
                buttonHeight++;
                tmpWidth = 0;
                tmpStr = new StringBuilder();
            } else
                tmpStr.append(c);
        }
        this.text.add(tmpStr.toString());
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

        int buttonX;
        if (this.getX() < 0)
            buttonX = MapConfiguration.getMapWidth()/2 - this.buttonWidth/2 - 1;
        else
            buttonX = this.getX();

        int buttonY;
        if (this.getY() < 0)
            buttonY = MapConfiguration.getMapHeight()/2 - this.buttonHeight/2 + 1;
        else
            buttonY = this.getY();

        tg.fillRectangle(new TerminalPosition(buttonX, buttonY), new TerminalSize(buttonWidth + 3, buttonHeight), ' ');
        for (int i = 0; i < this.text.size(); ++i)
            tg.putString(buttonX + 3, buttonY + 3 + i, this.text.get(i));

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
