import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class MyCalc extends WindowAdapter implements ActionListener{
    Frame f;
    Panel sidePanel;
    Label l1, historyLabel;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,custom;
    Button badd,bsub,bmult,bdiv,bmod,bcalc,bclr,bpts,bneg,bback;
    Button[] sideButtons;
    double xd;
    double num1,num2,check;
    ArrayList<String> history = new ArrayList<>();

    MyCalc(){
        f= new Frame("Calculator");
// INSTANTIATING COMPONENETS 
        l1=new Label();
        l1.setBackground(Color.lightGray);
        l1.setBounds(50,50,260,60);

// History label to display calculation history
        historyLabel = new Label("History:");
        historyLabel.setBackground(Color.white);
        historyLabel.setBounds(50, 120, 260, 30);


        b1=new Button("1");
        b1.setBounds(50,340,50,50);
        b2=new Button("2");
        b2.setBounds(120,340,50,50);
        b3=new Button("3");
        b3.setBounds(190,340,50,50);
        b4=new Button("4");
        b4.setBounds(50,270,50,50);
        b5=new Button("5");
        b5.setBounds(120,270,50,50);
        b6=new Button("6");
        b6.setBounds(190,270,50,50);
        b7=new Button("7");
        b7.setBounds(50,200,50,50);
        b8=new Button("8");
        b8.setBounds(120,200,50,50);
        b9=new Button("9");
        b9.setBounds(190,200,50,50);
        b0=new Button("0");
        b0.setBounds(120,410,50,50);
        bneg=new Button("+/-");
        bneg.setBounds(50,410,50,50);
        bpts=new Button(".");
        bpts.setBounds(190,410,50,50);
        bback=new Button("back");
        bback.setBounds(120,130,50,50);
        custom = new Button("<<");
        custom.setBounds(5,50,30,60);


        badd=new Button("+");
        badd.setBounds(260,340,50,50);
        bsub=new Button("-");
        bsub.setBounds(260,270,50,50);
        bmult=new Button("*");
        bmult.setBounds(260,200,50,50);
        bdiv=new Button("/");
        bdiv.setBounds(260,130,50,50);
        bmod=new Button("%");
        bmod.setBounds(190,130,50,50);
        bcalc=new Button("=");
        bcalc.setBounds(245,410,65,50);
        bclr=new Button("CE");
        bclr.setBounds(50,130,65,50);

        // Create side panel with 8 function buttons
        sidePanel = new Panel();
        sidePanel.setLayout(new GridLayout(8, 1, 5, 5));
        sidePanel.setBounds(320, 50, 60, 410);
        sidePanel.setVisible(false); // Initially hidden

        sideButtons = new Button[8];
        for (int i = 0; i < 8; i++) {
            sideButtons[i] = new Button("F" + (i + 1));
            sideButtons[i].addActionListener(this);
            sidePanel.add(sideButtons[i]);
        }


        // Register action listeners
        Button[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, custom, bpts, bneg, bback, badd, bsub, bmult, bdiv, bmod, bcalc, bclr};
        for (Button b : buttons) {
            b.addActionListener(this);
        }

/*
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b0.addActionListener(this);
        custom.addActionListener(this);

        bpts.addActionListener(this);
        bneg.addActionListener(this);
        bback.addActionListener(this);

        badd.addActionListener(this);
        bsub.addActionListener(this);
        bmult.addActionListener(this);
        bdiv.addActionListener(this);
        bmod.addActionListener(this);
        bcalc.addActionListener(this);
        bclr.addActionListener(this);
*/
        f.addWindowListener(this);

        //ADDING TO FRAME
        f.add(l1);
        f.add(historyLabel);
        for (Button b : buttons) {
            f.add(b);
        }
        f.add(sidePanel);

        f.setSize(400,550);
        f.setLayout(null);
        f.setVisible(true);
    }
    //FOR CLOSING THW WINDOW
    public void windowClosing(WindowEvent e) {
        f.dispose();
    }

    public void actionPerformed(ActionEvent e){

        String z,zt;

        if (e.getSource() == custom) {
            sidePanel.setVisible(!sidePanel.isVisible());
        }

        /* NUMBER BUTTON
        if(e.getSource()==b1){
            zt=l1.getText();
            z=zt+"1";
            l1.setText(z);
        }
        if(e.getSource()==b2){
            zt=l1.getText();
            z=zt+"2";
            l1.setText(z);
        }
        if(e.getSource()==b3){
            zt=l1.getText();
            z=zt+"3";
            l1.setText(z);
        }
        if(e.getSource()==b4){
            zt=l1.getText();
            z=zt+"4";
            l1.setText(z);
        }
        if(e.getSource()==b5){
            zt=l1.getText();
            z=zt+"5";
            l1.setText(z);
        }
        if(e.getSource()==b6){
            zt=l1.getText();
            z=zt+"6";
            l1.setText(z);
        }
        if(e.getSource()==b7){
            zt=l1.getText();
            z=zt+"7";
            l1.setText(z);
        }
        if(e.getSource()==b8){
            zt=l1.getText();
            z=zt+"8";
            l1.setText(z);
        }
        if(e.getSource()==b9){
            zt=l1.getText();
            z=zt+"9";
            l1.setText(z);
        }
        if(e.getSource()==b0){
            zt=l1.getText();
            z=zt+"0";
            l1.setText(z);
        }

        if(e.getSource()==bpts){  //ADD DECIMAL PTS
            zt=l1.getText();
            z=zt+".";
            l1.setText(z);
        }

         */

        // Number and dot buttons
        if (e.getSource() == b1 || e.getSource() == b2 || e.getSource() == b3 ||
                e.getSource() == b4 || e.getSource() == b5 || e.getSource() == b6 ||
                e.getSource() == b7 || e.getSource() == b8 || e.getSource() == b9 || e.getSource() == b0 || e.getSource() == bpts) {
            zt = l1.getText();
            z = zt + ((Button) e.getSource()).getLabel();
            l1.setText(z);
        }

        if(e.getSource()==bneg){ //FOR NEGATIVE
            zt=l1.getText();
            z="-"+zt;
            l1.setText(z);
        }

        if(e.getSource()==bback){  // FOR  BACKSPACE
            zt=l1.getText();
            try{
                z=zt.substring(0, zt.length()-1);
            }catch(StringIndexOutOfBoundsException f){return;}
            l1.setText(z);
        }

        //AIRTHMETIC BUTTON
        if(e.getSource()==badd){                     //FOR ADDITION
            try{
                num1=Double.parseDouble(l1.getText());
            }catch(NumberFormatException f){
                l1.setText("Invalid Format");
                return;
            }
            z="";
            l1.setText(z);
            check=1;
        }
        if(e.getSource()==bsub){                    //FOR SUBTRACTIOM
            try{
                num1=Double.parseDouble(l1.getText());
            }catch(NumberFormatException f){
                l1.setText("Invalid Format");
                return;
            }
            z="";
            l1.setText(z);
            check=2;
        }
        if(e.getSource()==bmult){                   //FOR MULTIPLICATION
            try{
                num1=Double.parseDouble(l1.getText());
            }catch(NumberFormatException f){
                l1.setText("Invalid Format");
                return;
            }
            z="";
            l1.setText(z);
            check=3;
        }
        if(e.getSource()==bdiv){                   //FOR DIVISIOM
            try{
                num1=Double.parseDouble(l1.getText());
            }catch(NumberFormatException f){
                l1.setText("Invalid Format");
                return;
            }
            z="";
            l1.setText(z);
            check=4;
        }
        if(e.getSource()==bmod){                  //FOR MOD/REMAINDER
            try{
                num1=Double.parseDouble(l1.getText());
            }catch(NumberFormatException f){
                l1.setText("Invalid Format");
                return;
            }
            z="";
            l1.setText(z);
            check=5;
        }


        /* RESULT BUTTON
        if(e.getSource()==bcalc){
            try{
                num2=Double.parseDouble(l1.getText());
            }catch(Exception f){
                l1.setText("ENTER NUMBER FIRST ");
                return;
            }
            if(check==1)
                xd =num1+num2;
            if(check==2)
                xd =num1-num2;
            if(check==3)
                xd =num1*num2;
            if(check==4)
                xd =num1/num2;
            if(check==5)
                xd =num1%num2;
            l1.setText(String.valueOf(xd));
        }     */
        // History update on calculation
        if (e.getSource() == bcalc) {
            zt = l1.getText();
            // Assuming operation already set, calculate xd based on `check` (simplified here)
            xd = num1 + num2; // placeholder operation
            String result = String.valueOf(xd);
            history.add(zt + " = " + result);
            historyLabel.setText("History: " + String.join(", ", history));
            l1.setText(result);
        }

        //FOR CLEARING THE LABEL and Memory
        if(e.getSource()==bclr){
            num1=0;
            num2=0;
            check=0;
            xd=0;
            z="";
            l1.setText(z);
        }
    }
    public static void main(String args[]){
        new MyCalc();
    }
}  