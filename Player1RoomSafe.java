import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Player1RoomSafe extends Palette implements Popup {
    ArrayList<Button> safeButtons = new ArrayList<>();
    String password = "";
    Boolean safeUnlocked = false;
    Boolean safeOpened = false;
    Boolean keyTaken = false;
    Button closeButton = new CloseButton();

    Player1RoomSafe(){
        safeButtons.add(new Player1RoomSafeButton(427.6,200.9,67.5,63.7,safeButton,1,null));
        safeButtons.add(new Player1RoomSafeButton(515.1,200.9,67.5,63.7,safeButton,2,null));
        safeButtons.add(new Player1RoomSafeButton(603,200.9,67.5,63.7,safeButton,3,null));
        safeButtons.add(new Player1RoomSafeButton(427.6,280.3,67.5,63.7,safeButton,4,null));
        safeButtons.add(new Player1RoomSafeButton(515.1,280.3,67.5,63.7,safeButton,5,null));
        safeButtons.add(new Player1RoomSafeButton(603,280.3,67.5,63.7,safeButton,6,null));
        safeButtons.add(new Player1RoomSafeButton(427.6,357.8,67.5,63.7,safeButton,7,null));
        safeButtons.add(new Player1RoomSafeButton(515.1,357.8,67.5,63.7,safeButton,8,null));
        safeButtons.add(new Player1RoomSafeButton(603,357.8,67.5,63.7,safeButton,9,null));
        safeButtons.add(new Player1RoomSafeButton(515.1,435.3,67.5,63.7,safeButton,0,null));
        safeButtons.add(new Player1RoomSafeButton(427.3,435.3,67.5,63.7,safeEnter,11,"enter"));
        safeButtons.add(new Player1RoomSafeButton(603,435.3,67.5,63.7,safeDelete,12,"reset"));
    }


    @Override
    public void draw(Graphics2D g2d) {
        if(!safeOpened) {

            Rectangle2D.Double safeBorders = new Rectangle2D.Double(65, 60.1, 679.9, 479.9);
            g2d.setColor(safeBorder);
            g2d.fill(safeBorders);

            Rectangle2D.Double safeBody = new Rectangle2D.Double(93.4, 81.7, 618.5, 439.2);
            g2d.setColor(pantsColor);
            g2d.fill(safeBody);

            Rectangle2D.Double safeHandleShades = new Rectangle2D.Double(165.6, 195.6, 6.3, 224.7);
            g2d.setColor(safeHandleShade);
            g2d.fill(safeHandleShades);

            Rectangle2D.Double safeHandle1 = new Rectangle2D.Double(119.8, 195.6, 32.9, 23.8);
            Rectangle2D.Double safeHandle2 = new Rectangle2D.Double(147.2, 195.6, 18.4, 224.7);
            Rectangle2D.Double safeHandle3 = new Rectangle2D.Double(119.8, 396.5, 32.9, 23.8);
            g2d.setColor(safeHandle);
            g2d.fill(safeHandle1);
            g2d.fill(safeHandle2);
            g2d.fill(safeHandle3);

            Rectangle2D.Double outputShade = new Rectangle2D.Double(437.3, 109.9, 243.3, 72.1);
            Rectangle2D.Double button1Shade = new Rectangle2D.Double(437.7, 200.9, 67.5, 63.7);
            Rectangle2D.Double button2Shade = new Rectangle2D.Double(525.2, 200.9, 67.5, 63.7);
            Rectangle2D.Double button3Shade = new Rectangle2D.Double(613.1, 200.9, 67.5, 63.7);
            Rectangle2D.Double button4Shade = new Rectangle2D.Double(437.3, 280.3, 67.5, 63.7);
            Rectangle2D.Double button5Shade = new Rectangle2D.Double(525.2, 280.3, 67.5, 63.7);
            Rectangle2D.Double button6Shade = new Rectangle2D.Double(613.1, 280.3, 67.5, 63.7);
            Rectangle2D.Double button7Shade = new Rectangle2D.Double(437.3, 357.8, 67.5, 63.7);
            Rectangle2D.Double button8Shade = new Rectangle2D.Double(525.2, 357.8, 67.5, 63.7);
            Rectangle2D.Double button9Shade = new Rectangle2D.Double(613.1, 357.8, 67.5, 63.7);
            Rectangle2D.Double button0Shade = new Rectangle2D.Double(525.2, 435.3, 67.5, 63.7);
            g2d.setColor(safeButtonShade);
            g2d.fill(outputShade);
            g2d.fill(button1Shade);
            g2d.fill(button2Shade);
            g2d.fill(button3Shade);
            g2d.fill(button4Shade);
            g2d.fill(button5Shade);
            g2d.fill(button6Shade);
            g2d.fill(button7Shade);
            g2d.fill(button8Shade);
            g2d.fill(button9Shade);
            g2d.fill(button0Shade);

            Rectangle2D.Double output = new Rectangle2D.Double(427.2, 109.9, 243.3, 72.1);
            g2d.setColor(safeButton);
            g2d.fill(output);


            Rectangle2D.Double enterShade = new Rectangle2D.Double(437.4, 435.3, 67.5, 63.7);
            g2d.setColor(safeEnterShade);
            g2d.fill(enterShade);

            Rectangle2D.Double resetShade = new Rectangle2D.Double(613.1, 435.3, 67.5, 63.7);
            g2d.setColor(safeDeleteShade);
            g2d.fill(resetShade);

            for (Button drawButtons : safeButtons) {
                drawButtons.draw(g2d);
            }

            Font f = new Font("Cascadia Mono", Font.BOLD, 30);
            g2d.setColor(numbers);
            g2d.setFont(f);
            g2d.drawString("1", 452, 244);
            g2d.drawString("2", 538, 244);
            g2d.drawString("3", 625, 244);
            g2d.drawString("4", 452, 322);
            g2d.drawString("5", 538, 322);
            g2d.drawString("6", 625, 322);
            g2d.drawString("7", 452, 400);
            g2d.drawString("8", 538, 400);
            g2d.drawString("9", 625, 400);
            g2d.drawString("0", 538, 478);

            g2d.drawString(password, 449, 161);
        }
        else{
            AffineTransform reset = g2d.getTransform();
            Rectangle2D.Double safeBorders = new Rectangle2D.Double(65, 60.1, 679.9, 479.9);
            g2d.setColor(safeBorder);
            g2d.fill(safeBorders);

            Rectangle2D.Double safeInside1 = new Rectangle2D.Double(93.4,81.7,618.5,439.2);
            g2d.setColor(safeInside);
            g2d.fill(safeInside1);

            Rectangle2D.Double safeInside2 = new Rectangle2D.Double(130.3,148.1,486.2,343.7);
            g2d.setColor(safeBack);
            g2d.fill(safeInside2);

            Rectangle2D.Double safeOpen1 = new Rectangle2D.Double(616.4,81.7,618.5,439.2);
            g2d.setColor(safeCorner);
            g2d.fill(safeOpen1);

            Rectangle2D.Double safeOpen2 = new Rectangle2D.Double(631.4,93.7,175.1,415.3);
            g2d.setColor(safeInner);
            g2d.fill(safeOpen2);

            Rectangle2D.Double goo1 = new Rectangle2D.Double(132.3,463.5,156.5,28.2);
            Rectangle2D.Double goo2 = new Rectangle2D.Double(169.3,420.3,68.7,71.4);
            g2d.setColor(goo);
            g2d.fill(goo1);
            g2d.fill(goo2);

            Line2D.Double cobweb1 = new Line2D.Double(90.9,183.9,133.3,81);
            Line2D.Double cobweb2 = new Line2D.Double(170.9,80.4,90.6,135.2);
            Line2D.Double cobweb3 = new Line2D.Double(93.8,249.5,189,80.7);
            Line2D.Double cobweb4 = new Line2D.Double(223.4,81.1,92.5,206.6);
            Line2D.Double cobweb5 = new Line2D.Double(96,251.3,251.8,82.5);
            Line2D.Double cobweb6 = new Line2D.Double(97.4,275.9,289.8,83.5);
            Line2D.Double cobweb7 = new Line2D.Double(212.6,312.4,212.2,125);
            g2d.setColor(cobweb);
            g2d.setStroke(new BasicStroke(4));
            g2d.draw(cobweb1);
            g2d.draw(cobweb2);
            g2d.draw(cobweb3);
            g2d.draw(cobweb4);
            g2d.draw(cobweb5);
            g2d.draw(cobweb6);
            g2d.draw(cobweb7);

            Line2D.Double spiderLeg1 = new Line2D.Double(188.9,281.4,177.3,293.5);
            Line2D.Double spiderLeg2 = new Line2D.Double(186.9,281.9,198.4,294.2);
            Line2D.Double spiderLeg3 = new Line2D.Double(189.3,290.7,177.6,302.8);
            Line2D.Double spiderLeg4 = new Line2D.Double(187.2,291.2,198.8,303.5);
            Line2D.Double spiderLeg5 = new Line2D.Double(189.1,299.2,177.5,311.4);
            Line2D.Double spiderLeg6 = new Line2D.Double(187.1,299.8,198.6,312);
            Line2D.Double spiderLeg7 = new Line2D.Double(190.2,307.3,178.6,319.4);
            Line2D.Double spiderLeg8 = new Line2D.Double(188.2,307.8,199.7,320.1);
            g2d.setColor(black);
            g2d.draw(spiderLeg1);
            g2d.draw(spiderLeg2);
            g2d.draw(spiderLeg3);
            g2d.draw(spiderLeg4);
            g2d.draw(spiderLeg5);
            g2d.draw(spiderLeg6);
            g2d.draw(spiderLeg7);
            g2d.draw(spiderLeg8);

            g2d.translate(48,0);
            Line2D.Double spiderLeg9 = new Line2D.Double(188.9,281.4,177.3,293.5);
            Line2D.Double spiderLeg10 = new Line2D.Double(186.9,281.9,198.4,294.2);
            Line2D.Double spiderLeg11 = new Line2D.Double(189.3,290.7,177.6,302.8);
            Line2D.Double spiderLeg12 = new Line2D.Double(187.2,291.2,198.8,303.5);
            Line2D.Double spiderLeg13 = new Line2D.Double(189.1,299.2,177.5,311.4);
            Line2D.Double spiderLeg14 = new Line2D.Double(187.1,299.8,198.6,312);
            Line2D.Double spiderLeg15 = new Line2D.Double(190.2,307.3,178.6,319.4);
            Line2D.Double spiderLeg16 = new Line2D.Double(188.2,307.8,199.7,320.1);
            g2d.draw(spiderLeg9);
            g2d.draw(spiderLeg10);
            g2d.draw(spiderLeg11);
            g2d.draw(spiderLeg12);
            g2d.draw(spiderLeg13);
            g2d.draw(spiderLeg14);
            g2d.draw(spiderLeg15);
            g2d.draw(spiderLeg16);

            g2d.setTransform(reset);
            Rectangle2D.Double spiderBody = new Rectangle2D.Double(197.2,291,30,30.4);
            g2d.fill(spiderBody);

            Rectangle2D.Double poster = new Rectangle2D.Double(379.8,176.3,180,187.2);
            g2d.setColor(paperColor);
            g2d.fill(poster);

            Rectangle2D.Double orange1 = new Rectangle2D.Double(430.7,190.9,21.8,22.7);
            Rectangle2D.Double orange2 = new Rectangle2D.Double(458.4,190.9,21.8,22.7);
            Rectangle2D.Double orange3 = new Rectangle2D.Double(486.2,190.9,21.8,22.7);
            Rectangle2D.Double orange4 = new Rectangle2D.Double(389,258.1,21.8,22.7);
            Rectangle2D.Double orange5 = new Rectangle2D.Double(389,326.4,21.8,22.7);
            Rectangle2D.Double orange6 = new Rectangle2D.Double(528.7,258.6,21.8,22.7);
            Rectangle2D.Double orange7 = new Rectangle2D.Double(528.1,326.4,21.8,22.7);
            g2d.setColor(orangeThing);
            g2d.fill(orange1);
            g2d.fill(orange2);
            g2d.fill(orange3);
            g2d.fill(orange4);
            g2d.fill(orange5);
            g2d.fill(orange6);
            g2d.fill(orange7);

            Rectangle2D.Double redCode = new Rectangle2D.Double(458.9,222.6,21.8,22.7);
            g2d.setColor(redBooks);
            g2d.fill(redCode);

            Rectangle2D.Double pinkCode = new Rectangle2D.Double(387.8,291.2,23.4,22.5);
            g2d.setColor(pinkBooks);
            g2d.fill(pinkCode);

            Rectangle2D.Double greenCode = new Rectangle2D.Double(435.4,291.2,23.4,22.5);
            g2d.setColor(greenBooks);
            g2d.fill(greenCode);

            Rectangle2D.Double blueCode = new Rectangle2D.Double(481.7,291.2,23.4,22.5);
            g2d.setColor(blueBooks);
            g2d.fill(blueCode);

            Rectangle2D.Double yellowCode = new Rectangle2D.Double(527.6,291.2,23.4,22.5);
            g2d.setColor(yellowBooks);
            g2d.fill(yellowCode);

            Line2D.Double code1 = new Line2D.Double(416.1,296.4,431.4,311.1);
            Line2D.Double code2 = new Line2D.Double(431.3,296.2,416.2,311.3);
            Line2D.Double code3 = new Line2D.Double(479.7,302.7,461.3,302.7);
            Line2D.Double code4 = new Line2D.Double(470.7,311.8,470.3,293.5);
            Line2D.Double code5 = new Line2D.Double(524.3,310.3,508.9,295.6);
            Line2D.Double code6 = new Line2D.Double(524.2,295.4,509,310.5);
            Line2D.Double code7 = new Line2D.Double(477.5,276.5,462.1,262);
            Line2D.Double code8 = new Line2D.Double(477.4,261.8,462.2,277);
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(safeHandleShade);
            g2d.draw(code1);
            g2d.draw(code2);
            g2d.draw(code3);
            g2d.draw(code4);
            g2d.draw(code5);
            g2d.draw(code6);
            g2d.draw(code7);
            g2d.draw(code8);

            Line2D.Double tape1 = new Line2D.Double(334.9,250.9,484.9,383.8);
            Line2D.Double tape2 = new Line2D.Double(336.9,273.3,576.2,183.7);
            Line2D.Double tape3 = new Line2D.Double(594.2,280.1,408.1,385.7);
            g2d.setStroke(new BasicStroke(30));
            g2d.setColor(tape);
            g2d.draw(tape1);
            g2d.draw(tape2);
            g2d.draw(tape3);

            g2d.setStroke(new BasicStroke(5));

            if(!keyTaken){
                Rectangle2D.Double key1 = new Rectangle2D.Double(330.2,442.2,55.6,56.4);
                Rectangle2D.Double key2 = new Rectangle2D.Double(372.2,473.6,101.6,10.8);
                Rectangle2D.Double key3 = new Rectangle2D.Double(447.1,442.8,7.3,30.8);
                Rectangle2D.Double key4 = new Rectangle2D.Double(466.6,442.8,7.3,30.8);
                g2d.setColor(safeKey);
                g2d.fill(key1);
                g2d.fill(key2);
                g2d.fill(key3);
                g2d.fill(key4);

                Rectangle2D.Double keyHole = new Rectangle2D.Double(338.2,449.4,14,17.6);
                g2d.setColor(safeBack);
                g2d.fill(keyHole);
            }
        }
        closeButton.draw(g2d);
    }

    @Override
    public String getPopupName() {
        return "Player1RoomSafe";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        ArrayList<Button> player1Safe = new ArrayList<>();
        player1Safe.add(closeButton);
        if(!safeUnlocked) {
            player1Safe.addAll(safeButtons);
        }
        else if(safeUnlocked && !safeOpened){
            player1Safe.add(new OpenSafe());
        }
        else if(safeOpened){
            player1Safe.add(new GetKey());
        }
        return player1Safe;
    }

    @Override
    public void defaultPopup() {

    }

    @Override
    public boolean hasChanged() {
        if(keyTaken){
            return true;
        }
        return false;
    }

    public String whatChanged(){
        if(keyTaken){
            return "Key Taken";
        }
        return "No Change";
    }

    public class Player1RoomSafeButton implements Button{
        double x,y,width,height;
        Color color;
        int buttonNum;
        String otherFunction;

        Player1RoomSafeButton(double x, double y, double width, double height, Color color, int buttonNum, String otherFunction){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
            this.buttonNum = buttonNum;
            this.otherFunction = otherFunction;
        }

        @Override
        public void draw(Graphics2D g2d) {
            Rectangle2D.Double button = new Rectangle2D.Double(x,y,width,height);
            g2d.setColor(color);
            g2d.fill(button);
        }
        @Override
        public void function() {
            if(!password.equals("Correct!")) {
                if (!password.equals("Incorrect!") && password.length() < 6) {
                    if (otherFunction == null) {
                        password += Integer.toString(buttonNum);
                    } else {
                        if (otherFunction.equals("enter")) {
                            password = "Incorrect!";
                        }
                    }
                }
                if (otherFunction != null) {
                    if (otherFunction.equals("enter")) {
                        if (password.equals("412166")) {
                            password = "Correct!";
                            safeUnlocked = true;
                        } else {
                            password = "Incorrect!";
                        }
                    } else if (otherFunction.equals("reset")) {
                        password = "";
                    }
                }
            }
        }

        @Override
        public String getButton() {
            return "Not Close.";
        }

        @Override
        public int getButtonNum() {
            return buttonNum;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((x <= mouseClick.getX() && x + width >= mouseClick.getX()) && (y <= mouseClick.getY() && y + height >= mouseClick.getY())){
                return true;
            }
            return false;
        }

    }
    public class OpenSafe implements Button{

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            safeOpened = true;

        }

        @Override
        public String getButton() {
            return "Not Close.";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((0 <= mouseClick.getX() && 800 >= mouseClick.getX()) && (0 <= mouseClick.getY() && 600 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
    public class GetKey implements Button{

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            keyTaken = true;

        }
        @Override
        public String getButton() {
            return "aisgdjksa";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }
        @Override
        public boolean contains(Point mouseClick) {
            if((320.2 <= mouseClick.getX() && 320.2+162.4 >= mouseClick.getX()) && (431.4 <= mouseClick.getY() && 431.4+73.2 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
}

