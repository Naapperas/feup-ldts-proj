package pt.up.fe.ldts.model.menus;

import com.googlecode.lanterna.graphics.TextGraphics;
import pt.up.fe.ldts.view.Drawable;

import java.util.List;

public class MenuDisplay implements Drawable {
    private final List<Button> buttons;

    public MenuDisplay(List<Button> buttons){
        this.buttons=buttons;
    }

    public void selectTop(){
        this.buttons.get(0).select();
    }

    public void selectBottom(){
        this.buttons.get(this.buttons.size()-1).select();
    }

    public void selectUP(){
        if (this.getSelected() == 0) {
            this.buttons.get(0).unSelect();
            this.selectBottom();
        }
        else{
            int here = this.getSelected();
            this.buttons.get(here).unSelect();
            this.buttons.get(here-1).select();
        }
    }

    public void selectDown(){
        if (this.getSelected() == this.buttons.size()-1) {
            this.buttons.get(this.buttons.size()-1).unSelect();
            this.selectTop();
        }
        else{
            int here = this.getSelected();
            this.buttons.get(here).unSelect();
            this.buttons.get(here+1).select();
        }
    }

    public int getSelected(){
        for (int i = 0; i < buttons.size(); i++){
            if (buttons.get(i).isSelected())
                return i;
        }
        return -1;
    }

    @Override
    public void render(TextGraphics tg){
        for (var button : this.buttons)
            button.render(tg);
    }
}
