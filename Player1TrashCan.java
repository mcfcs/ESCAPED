import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Player1TrashCan extends Palette implements Popup{

    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<Button> trashPieces = new ArrayList<>();
    Boolean isLooking = false;
    Button closeButton = new CloseButton();
    Player1TrashCan(){
        buttons.add(closeButton);
        buttons.add(new ButtonOption("Take a look"));

        trashPieces.add(new Trash(188.7,87.9,146.8,139.7,trash1,false));
        trashPieces.add(new Trash(293.1,87.9,99.1,96.1,trash2,false));
        trashPieces.add(new Trash(188.7,192.1,129.5,115,trash4,false));
        trashPieces.add(new Trash(481.5,87.4,129.5,115,trash4,false));
        trashPieces.add(new Trash(327.4,87.4,129.5,115,trash4,false));
        trashPieces.add(new Trash(188.7,265.6,99.1,96.1,trash3,false));
        trashPieces.add(new Trash(228.6,380.7,129.5,115,trash4,false));
        trashPieces.add(new Trash(188.7,356,146.8,139.7,trash1,false));
        trashPieces.add(new Trash(332.9,399.6,99.1,96.1,trash3,false));
        trashPieces.add(new Trash(512.2,399.6,99.1,96.1,trash2,false));
        trashPieces.add(new Trash(423.8,399.6,99.1,96.1,trash2,false));
        trashPieces.add(new Trash(476,361.7,99.1,96.1,trash3,false));
        trashPieces.add(new Trash(432,308,99.1,96.1,trash2,false));
        trashPieces.add(new Trash(464.5,227,146.8,139.7,trash1,false));
        trashPieces.add(new Trash(432,87.4,99.1,96.1,trash3,false));
        trashPieces.add(new Trash(481.5,317.4,129.5,115,trash4,false));
        trashPieces.add(new Trash(512.2,150.1,99.1,96.1,trash3,false));
        trashPieces.add(new Trash(512.2,184.5,99.1,96.1,trash2,false));
        trashPieces.add(new Trash(327.4,87.4,129.5,115,trash4,false));
        trashPieces.add(new Trash(384.3,279.3,146.8,139.7,trash1,false));
        trashPieces.add(new Trash(365.4,203.9,99.1,96.1,trash2,false));
        trashPieces.add(new Trash(262.1,279.3,99.1,96.1,trash2,false));
        trashPieces.add(new Trash(419.3,165.8,99.1,96.1,trash3,false));

    }

    @Override
    public void draw(Graphics2D g2d) {
        AffineTransform reset = g2d.getTransform();
        if(!isLooking) {
            Rectangle2D.Double popUpBorder = new Rectangle2D.Double(101.8, 390.1, 596.4, 169.5);
            g2d.setColor(popupBorder);
            g2d.fill(popUpBorder);

            Rectangle2D.Double popUpInner = new Rectangle2D.Double(117.5, 403, 565, 143.8);
            g2d.setColor(popupInner);
            g2d.fill(popUpInner);

            Font f = new Font("Cascadia Mono", Font.BOLD, 28);
            g2d.setColor(popupBorder);
            g2d.setFont(f);
            g2d.drawString("There is something inside the Trash Can", 128, 435);

            for(Button button : buttons){
                button.draw(g2d);
            }
        } else{
            Rectangle2D.Double trashCan = new Rectangle2D.Double(173.7,71.1,452.6,468.9);
            g2d.setColor(trashcanOuter);
            g2d.fill(trashCan);

            Rectangle2D.Double trashCanInner = new Rectangle2D.Double(188.7,87.4,422.3,407.9);
            g2d.setColor(trashcanInner);
            g2d.fill(trashCanInner);

            Rectangle2D.Double paper = new Rectangle2D.Double(225.2,217.9,167.7,277);
            g2d.setColor(paperColor);
            g2d.rotate(Math.toRadians(-27.4),225.2,217.9);
            g2d.fill(paper);
            g2d.setTransform(reset);

            Rectangle2D.Double yellowCode = new Rectangle2D.Double(257.4,240.1,29,29);
            g2d.setColor(yellowBooks);
            g2d.rotate(Math.toRadians(-27.4),257.4,240);
            g2d.fill(yellowCode);
            g2d.setTransform(reset);

            Rectangle2D.Double blueCode = new Rectangle2D.Double(283.9,291.1,29,29);
            g2d.setColor(blueBooks);
            g2d.rotate(Math.toRadians(-27.4),283.9,291.1);
            g2d.fill(blueCode);
            g2d.setTransform(reset);

            Rectangle2D.Double greenCode = new Rectangle2D.Double(310.2,341.6,29,29);
            g2d.setColor(greenBooks);
            g2d.rotate(Math.toRadians(-27.4),310.2,341.66);
            g2d.fill(greenCode);
            g2d.setTransform(reset);

            Rectangle2D.Double pinkCode = new Rectangle2D.Double(336.9,393.2,29,29);
            g2d.setColor(pinkBooks);
            g2d.rotate(Math.toRadians(-27.4),336.9,393.2);
            g2d.fill(pinkCode);
            g2d.setTransform(reset);

            Font f = new Font("Cascadia Mono", Font.BOLD, 28);
            g2d.setColor(popupBorder);
            g2d.setFont(f);

            g2d.rotate(Math.toRadians(-27.4),306,241);
            g2d.drawString("x 4", 306, 241);
            g2d.setTransform(reset);
            g2d.rotate(Math.toRadians(-27.4),332,292);
            g2d.drawString("x 3", 332, 292);
            g2d.setTransform(reset);
            g2d.rotate(Math.toRadians(-27.4),361,347);
            g2d.drawString("x 2", 361, 347);
            g2d.setTransform(reset);
            g2d.rotate(Math.toRadians(-27.4),389,400);
            g2d.drawString("x 1", 389, 400);
            g2d.setTransform(reset);

            for(Button trash : trashPieces){
                trash.draw(g2d);
            }

            closeButton.draw(g2d);

        }

    }

    @Override
    public String getPopupName() {
        return "Player1TrashCan";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        ArrayList<Button> newButtons = new ArrayList<>();

        if(isLooking){
            newButtons.addAll(buttons);
            newButtons.addAll(trashPieces);
        }
        else{
            newButtons.addAll(buttons);
        }
        return newButtons;
    }

    @Override
    public void defaultPopup() {
        isLooking = false;
    }

    @Override
    public boolean hasChanged() {
        return false;
    }

    @Override
    public String whatChanged() {
        return "No Change";
    }

    public class ButtonOption extends Palette implements Button{
        String text;
        ButtonOption(String text){
            this.text = text;
        }
        @Override
        public void draw(Graphics2D g2d) {
            Rectangle2D.Double buttonBorder = new Rectangle2D.Double(484.8,488.9,188.6,51.1);
            g2d.setColor(popupBorder);
            g2d.draw(buttonBorder);

            Rectangle2D.Double buttonInner = new Rectangle2D.Double(490.4,494.4,176.6,40.2);
            g2d.setColor(popupInner);
            g2d.draw(buttonInner);

            Font f = new Font("Cascadia Mono", Font.BOLD, 28);
            g2d.setColor(popupBorder);
            g2d.setFont(f);
            g2d.drawString(text, 495, 525);
        }

        @Override
        public void function() {
            isLooking = true;
        }

        @Override
        public String getButton() {
            return "I love bini";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((484.8 <= mouseClick.getX() && 484.8 + 188.6 >= mouseClick.getX()) && (488.9 <= mouseClick.getY() && 488.9 + 51.1 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
    public class Trash extends Palette implements Button{
        double x,y,width,height;
        Color color;
        Boolean cleaned;
        Trash(double x, double y, double width, double height, Color color, Boolean cleaned){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
            this.cleaned = cleaned;
        }

        @Override
        public void draw(Graphics2D g2d) {
            if(!cleaned) {
                Rectangle2D.Double trash = new Rectangle2D.Double(x, y, width, height);
                g2d.setColor(color);
                g2d.fill(trash);
            }
        }

        @Override
        public void function() {
            cleaned = true;
        }

        @Override
        public String getButton() {
            return "trashhhh";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((x <= mouseClick.getX() && x + width >= mouseClick.getX()) && (y <= mouseClick.getY() && y + height >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }

}
