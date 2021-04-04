
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@SuppressWarnings("serial")

public class CalcFrame extends JFrame {
	
	JMenuBar mBar;
	JMenu mFile,mHelp;
	JMenuItem iClr,iExit,iAbout,iContact;
	JPanel primePanel,buttPanel,numPanel,funPanel,midPanel,emptyPanel;
	JTextField userInput;
	JButton bDel,bClr,bAns,bExit,b1,b2,b3,b4,b5,b6,b7,
	b8,b9,b0,bPlus,bMinus,bMul,bDiv,bDot,bPm,
	bPow_2,bPow_3,bPow_y,bRec,bR,bL,bRoot,bRoot2,bRooty,bFac,bSin,bCos,
	bTan,bExp,bNpr,bNcr,bAsin,bAcos,bAtan,bLog,bLog10,bPi,bSinh,bCosh,bTanh;
	JCheckBox scientific ;
	
	 String result="";
     char lastChar=' ';
     boolean isDot=false;
     Action action = new Action();
	
	public CalcFrame () {
		
		this.setTitle("Calculator");
		this.setSize(new Dimension(335,370));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(300, 200);
		this.setResizable(false);
		this.setLayout(null);
			
		/*----------------------------------------------------------------*/
		/*----------------------------------------------------------------*/
		/** menus file help**/
		mBar=new JMenuBar();
		
		mFile =new JMenu("File");mFile.setFocusable(false);
		mHelp=new JMenu("Help");mHelp.setFocusable(false);
		
		iClr=new JMenuItem("Clear");iClr.addActionListener(action);
		iExit=new JMenuItem("Exit");iExit.addActionListener(action);
		iClr.setFocusable(false);iExit.setFocusable(false);
		iAbout=new JMenuItem("About");iAbout.setFocusable(false);
		iContact=new JMenuItem("Contact");iContact.setFocusable(false);
		
		mFile.add(iClr);
		mFile.add(iExit);
		mHelp.add(iAbout);
		mHelp.add(iContact);
		
		mBar.add(mFile);
		mBar.add(mHelp);
		
		this.setJMenuBar(mBar);
		
		/*----------------------------------------------------------------*/
		/*----------------------------------------------------------------*/
		/** primary panel   with all components and panels**/
		
		primePanel=new JPanel();
		primePanel.setBackground(Color.decode("#1D1F33"));
		primePanel.setBounds(0,0,320,370);
		primePanel.setLayout(null);
		
		/*----------------------------------------------------------------*/
		/*----------------------------------------------------------------*/
		/**text field **/
		
		userInput=new JTextField();
		userInput.setBounds(15, 10, 290, 25);
		userInput.setEditable(false);
		
		/*----------------------------------------------------------------*/
		/*----------------------------------------------------------------*/
		/** buttons panel with del,clr,ans,exit **/
		
		buttPanel=new JPanel();
		buttPanel.setBackground(Color.decode("#81d1ff"));
		buttPanel.setBounds(15, 50, 290, 45);
		buttPanel.setLayout(null);
		
		bDel=new JButton("Del");
		bDel.setBounds(10, 10, 60, 25);
		bDel.addActionListener(action);
        bDel.setFocusable(false);
        
		bClr=new JButton("Clr");
		bClr.setBounds(80, 10, 60, 25);
		bClr.addActionListener(action);
		bClr.setFocusable(false);
		
		bAns=new JButton("Ans");
		bAns.setBounds(150, 10, 60, 25);
		bAns.addActionListener(action);
		bAns.setFocusable(false);
		
		bExit=new JButton("Exit");
		bExit.setBounds(220, 10, 60, 25);
		bExit.addActionListener(action);
		bExit.setFocusable(false);
		
		buttPanel.add(bDel);
		buttPanel.add(bClr);
		buttPanel.add(bAns);
		buttPanel.add(bExit);

		/*----------------------------------------------------------------*/
		/*----------------------------------------------------------------*/
		/**numbers panel which have 1,2,3,-,+,...**/ 
		
		numPanel=new JPanel();
		numPanel.setBackground(Color.decode("#81d1ff"));
		numPanel.setBounds(10, 10, 270, 130);
		numPanel.setLayout(new GridLayout(4,4,7,7));
	
		//creat JButtons objects and add acyion listener
		b1=new JButton("1");b1.addActionListener(action);b1.setFocusable(false);
		b2=new JButton("2");b2.addActionListener(action);b2.setFocusable(false);
		b3=new JButton("3");b3.addActionListener(action);b3.setFocusable(false);
		b4=new JButton("4");b4.addActionListener(action);b4.setFocusable(false);
		b5=new JButton("5");b5.addActionListener(action);b5.setFocusable(false);
		b6=new JButton("6");b6.addActionListener(action);b6.setFocusable(false);
		b7=new JButton("7"); b7.addActionListener(action);b7.setFocusable(false);
		b8=new JButton("8");b8.addActionListener(action);b8.setFocusable(false);
		b9=new JButton("9");b9.addActionListener(action);b9.setFocusable(false);
		b0=new JButton("0");b0.addActionListener(action);b0.setFocusable(false);
		bMul=new JButton("*");bMul.addActionListener(action);bMul.setFocusable(false);
		bDiv=new JButton("/");bDiv.addActionListener(action);bDiv.setFocusable(false);
		bPlus=new JButton("+");bPlus.addActionListener(action);bPlus.setFocusable(false);
		bMinus=new JButton("-");bMinus.addActionListener(action);bMinus.setFocusable(false);
		bDot=new JButton(".");bDot.addActionListener(action);bDot.setFocusable(false);
		bPm=new JButton("±");bPm.addActionListener(action);bPm.setFocusable(false);
		
		//add components by order
		numPanel.add(b7);numPanel.add(b8);numPanel.add(b9);
		numPanel.add(bPlus);numPanel.add(b4);numPanel.add(b5);
		numPanel.add(b6);numPanel.add(bMinus);numPanel.add(b1);
		numPanel.add(b2);numPanel.add(b3);numPanel.add(bMul);
		numPanel.add(b0);numPanel.add(bDot);numPanel.add(bPm);
		numPanel.add(bDiv);
		
		/*----------------------------------------------------------------*/
		/*----------------------------------------------------------------*/
		/**scientific functions panel**/
		
		funPanel =new JPanel();
		funPanel.setBackground(Color.decode("#81d1ff"));
		funPanel.setBounds(260, 10, 385, 130);
		funPanel.setLayout(new GridLayout(5,5,7,7));
		
           
		bPow_2=new JButton("χ2");bPow_2.addActionListener(action);bPow_2.setFocusable(false);
		bPow_3=new JButton("χ3"); bPow_3.addActionListener(action);bPow_3.setFocusable(false);
		bPow_y=new JButton("χᵞ");bPow_y.addActionListener(action);bPow_y.setFocusable(false);
		bRec=new JButton("1/χ");bRec.addActionListener(action);bRec.setFocusable(false);
		bL=new JButton("(");bL.addActionListener(action);bL.setFocusable(false);
		bRoot=new JButton("√");bRoot.addActionListener(action);bRoot.setFocusable(false);
		bRoot2=new JButton("∛");bRoot2.addActionListener(action);bRoot2.setFocusable(false);
		bRooty=new JButton("ᵞ√");bRooty.addActionListener(action);bRooty.setFocusable(false);
		bFac=new JButton("n!");bFac.addActionListener(action);bFac.setFocusable(false);  
		bR=new JButton(")");bR.addActionListener(action);bR.setFocusable(false);
		bSin=new JButton("sin"); bSin.addActionListener(action);bSin.setFocusable(false);
		bCos=new JButton("cos");bCos.addActionListener(action);bCos.setFocusable(false);
		bTan=new JButton("tan");bTan.addActionListener(action);bTan.setFocusable(false);
		bExp=new JButton("exp");bExp.addActionListener(action);bExp.setFocusable(false);
		bNpr=new JButton("ⁿPr");bNpr.addActionListener(action);bNpr.setFocusable(false);
		bAsin=new JButton("asin");bAsin.addActionListener(action);bAsin.setFocusable(false);
		bAcos=new JButton("acos");bAcos.addActionListener(action);bAcos.setFocusable(false);
		bAtan=new JButton("atan"); bAtan.addActionListener(action);bAtan.setFocusable(false);
		bLog=new JButton("log");bLog.addActionListener(action);bLog.setFocusable(false);
		bNcr=new JButton("ⁿCr");bNcr.addActionListener(action);bNcr.setFocusable(false);
		bSinh=new JButton("sinh");bSinh.addActionListener(action);bSinh.setFocusable(false);
		bCosh=new JButton("cosh");bCosh.addActionListener(action);bCosh.setFocusable(false);
		bTanh=new JButton("tanh");bTanh.addActionListener(action);bTanh.setFocusable(false);
		bLog10=new JButton("log10");bLog10.addActionListener(action);bLog10.setFocusable(false);
		bPi=new JButton("π");bPi.addActionListener(action);bPi.setFocusable(false);
		    
		
		funPanel.add(bPow_2);funPanel.add(bPow_3);funPanel.add(bPow_y);
		funPanel.add(bRec);funPanel.add(bL);funPanel.add(bRoot);
		funPanel.add(bRoot2);funPanel.add(bRooty);funPanel.add(bFac);
		funPanel.add(bR);funPanel.add(bSin);funPanel.add(bCos);
		funPanel.add(bTan);funPanel.add(bExp);funPanel.add(bNpr);
		funPanel.add(bAsin);funPanel.add(bAcos);funPanel.add(bAtan);
		funPanel.add(bLog);funPanel.add(bNcr);funPanel.add(bSinh);
		funPanel.add(bCosh);funPanel.add(bTanh);funPanel.add(bLog10);
		funPanel.add(bPi);
		funPanel.setVisible(false);
		
		/*----------------------------------------------------------------*/
		/*----------------------------------------------------------------*/
		/**middle panel for numerical and functions panels**/
		
		midPanel=new JPanel();
		midPanel.setBackground(Color.decode("#81d1ff"));
		midPanel.setBounds(15, 115, 290, 150);
		midPanel.setLayout(null);
		
		//to adjust the color as required
		emptyPanel=new JPanel();
		emptyPanel.setBackground(Color.decode("#1D1F33"));
		emptyPanel.setBounds(245, 0, 10, 150);
		emptyPanel.setVisible(false);
		
		midPanel.add(numPanel);
		midPanel.add(emptyPanel);
		midPanel.add(funPanel);
		
		/*----------------------------------------------------------------*/
		/*----------------------------------------------------------------*/
		/**check box for scientific **/
		
		scientific=new JCheckBox("Scientific",false);
		scientific.setBounds(15, 275, 100, 20);
		scientific.setBackground(Color.decode("#1D1F33"));
        scientific.setForeground(Color.decode("#81d1ff"));
		scientific.setSelected(false);
		scientific.setFocusable(false);
		scientific.addItemListener(new showHide());
		/*----------------------------------------------------------------*/
		/*----------------------------------------------------------------*/
		/** what will add to primary frame**/
		
		primePanel.add(userInput);
		primePanel.add(buttPanel);
		primePanel.add(midPanel);
		primePanel.add(scientific);

		//keyboard events
		addKeyListener(new KeyBoard());
		setFocusable(true);
		this.add(primePanel);
		this.setVisible(true);
	}//end of constructor
	
	private class Action implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
        	
            if( ! (e.getSource()==b1 || e.getSource()==b2 || e.getSource()==b3 ||e.getSource()==b4 ||
                            e.getSource()==b5 ||e.getSource()==b6 ||e.getSource()==b7 ||e.getSource()==b8 ||
                            e.getSource()==b9 ||e.getSource()==b0 ||e.getSource()== bDot ||e.getSource()==bPm)) 
            {
                    isDot=false;
            }
            if(e.getSource() == bClr || e.getSource()==iClr)
            {
                result="";
                lastChar=' ';
            }
            else if(e.getSource() == bDel)
            {
                int pos = result.length()-1;

                if(pos>=0)
                {
                    if(lastChar == '(')
                    {
                            do{
                                if(pos>0)
                                lastChar=result.charAt(--pos);
                                else {
                                    --pos;
                                    break;
                                }
                            }while( (lastChar>='a' && lastChar<='z') ||lastChar=='^');
                            pos++;
                            result= result.substring(0, pos);
                    }
                    else
                        result= result.substring(0, pos);
                }

                if(pos>0)
                    lastChar = result.charAt(pos-1);
                else
                    lastChar=' ';

            }
            else if(e.getSource() == bExit ||e.getSource()== iExit)
                System.exit(0);

            else if(e.getSource() == b0)
            {
                if(lastChar==')' || lastChar=='!' || lastChar=='π')
                   result+="*0";
                else
                   result+="0"; 
                lastChar = '0';
            }
            else if(e.getSource() == b1)
            {
                if(lastChar==')' || lastChar=='!' || lastChar=='π')
                   result+="*1";
                else
                   result+="1"; 
                lastChar = '1';
            }
            else if(e.getSource() == b2)
            {
                if(lastChar==')' || lastChar=='!' || lastChar=='π')
                   result+="*2";
                else
                   result+="2"; 
                lastChar = '2';
            }
            else if(e.getSource() == b3)
            {
                if(lastChar==')' || lastChar=='!' || lastChar=='π')
                   result+="*3";
                else
                   result+="3"; 
                lastChar = '3';
            }
            else if(e.getSource() == b4)
            {
                if(lastChar==')' || lastChar=='!' || lastChar=='π')
                   result+="*4";
                else
                   result+="4"; 
                lastChar = '4';
            }
            else if(e.getSource() == b5)
            {
                if(lastChar==')' || lastChar=='!' || lastChar=='π')
                   result+="*5";
                else
                   result+="5"; 
                lastChar = '5';
            }
            else if(e.getSource() == b6)
            {
                if(lastChar==')' || lastChar=='!' || lastChar=='π')
                   result+="*6";
                else
                   result+="6"; 
                lastChar = '6';
            }
            else if(e.getSource() == b7)
            {
                if(lastChar==')' || lastChar=='!' || lastChar=='π')
                   result+="*7";
                else
                   result+="7"; 
                lastChar = '7';
            }
            
            else if(e.getSource() == b8)
            {
                if(lastChar==')' || lastChar=='!' || lastChar=='π')
                   result+="*8";
                else
                   result+="8"; 
                lastChar = '8';
            }
            else if(e.getSource() == b9)
            {
                if(lastChar==')' || lastChar=='!' || lastChar=='π')
                   result+="*9";
                else
                   result+="9"; 
                lastChar = '9';
            }
            else if(e.getSource() == bMul)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                {
                    result+="*"; lastChar = '*';
                } 
            }
            else if(e.getSource() == bDiv)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                {
                    result+="/"; lastChar = '/';
                } 
            }
            else if(e.getSource() == bPlus)
            {
                result+="+";  lastChar = '+';
            }
                 
            
            else if(e.getSource() == bMinus)
            {
                result+="-";lastChar = '-';
            } 
            
            else if(e.getSource() == bDot)
            { 
            	if( lastChar=='(' || lastChar=='*' || lastChar=='/' || lastChar=='+'|| lastChar=='-' || result.isEmpty()) 
                {
               	result+="0.";lastChar='.';
               	isDot = true;
                }
            	
            	else if(lastChar==')' || lastChar=='!' || lastChar=='π')
                {
                    result+="*0."; lastChar = '.';
                    isDot = true;
                }
            	else if(lastChar>='0' && lastChar<='9' && !isDot)
            	{
            		result+="."; lastChar = '.';
            		isDot = true;
            	}
                
             }
            
            else if(e.getSource() == bPm)
            {//implement +- here
                if( (lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar=='π')
                {
                    int pos = result.length()-1;

                       while( pos>0 &&( (result.charAt(pos)>='0' && result.charAt(pos)<='9') 
                               || result.charAt(pos)=='.' || result.charAt(pos)=='π') )
                       {
                           pos--;
                       }
                       if(pos>0)
                       {
                           if(result.charAt(pos)=='-')
                               result =result.substring(0, pos)+"+"+result.substring(pos+1);
                           else if(result.charAt(pos)=='+')
                               result =result.substring(0, pos)+"-"+result.substring(pos+1);
                           else
                               result = result.substring(0, pos+1)+"-"+result.substring(pos+1);
                       }
                           
                       else // pos==0 which means that the number is the first number in the string
                       {
                           if(result.charAt(pos)=='-')
                               result ="+"+result.substring(pos+1);
                           else if(result.charAt(pos)=='+')
                               result ="-"+result.substring(pos+1);
                           else
                               result ="-"+result.substring(pos);
                       }
                }
            }
            else if(e.getSource() == bRec)
            {//implement 1/x here
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                {
                    result+="^(-1)"; lastChar = ')';
                } 
            }
            
            
            else if(e.getSource() == bPow_2)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                {
                    result+="^(2)"; lastChar = ')';
                } 
            }
            else if(e.getSource() == bPow_3)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                {
                    result+="^(3)"; lastChar = ')';
                } 
            }
            else if(e.getSource() == bPow_y)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                {
                    result+="^("; lastChar = '(';
                } 
            }
            else if(e.getSource() == bL)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*(";
                else
                    result+="(";
                lastChar = '(';
            }
            else if(e.getSource() == bR)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar=='!' || lastChar=='π' || lastChar==')')
                {
                    result+=")"; lastChar = ')';
                }
                
            }
            else if(e.getSource() == bRoot)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*sqrt(";
                else
                    result+="sqrt(";
                lastChar = '(';
            }
            else if(e.getSource() == bRoot2)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*cbrt(";
                else
                    result+="cbrt(";
                lastChar = '(';
            }
            else if(e.getSource() == bRooty)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                {
                    result+="root("; lastChar = '(';
                }
            }
            else if(e.getSource() == bFac)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar==')')
                {
                    result+="!"; lastChar = '!';
                }
            }
            else if(e.getSource() == bSin)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*sin(";
                else
                    result+="sin(";
                lastChar = '(';
            }
            else if(e.getSource() == bCos)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*cos(";
                else
                    result+="cos(";
                lastChar = '(';
            }
            else if(e.getSource() == bTan)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*tan(";
                else
                    result+="tan(";
                lastChar = '(';
            }
            else if(e.getSource() == bAsin)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*asin(";
                else
                    result+="asin(";
                lastChar = '(';
            }
            else if(e.getSource() == bAcos)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*acos(";
                else
                    result+="acos(";
                lastChar = '(';
            }
            else if(e.getSource() == bAtan)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*atan(";
                else
                    result+="atan(";
                lastChar = '(';
            }
            else if(e.getSource() == bSinh)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*sinh(";
                else
                    result+="sinh(";
                lastChar = '(';
            }
            else if(e.getSource() == bCosh)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*cosh(";
                else
                    result+="cosh(";
                lastChar = '(';
            }
            else if(e.getSource() == bTanh)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*tanh(";
                else
                    result+="tanh(";
                lastChar = '(';
            }
            else if(e.getSource() == bExp)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*e^(";
                else
                    result+="e^(";
                lastChar = '(';
            }
            else if(e.getSource() == bLog)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*ln(";
                else
                    result+="ln(";
                lastChar = '(';
            }
            else if(e.getSource() == bLog10)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                    result+="*log(";
                else
                    result+="log(";
                lastChar = '(';
            }
            else if(e.getSource() == bNpr)
            {
                //this function n and r must be int >=0 and n>=r 
                if((lastChar>='0' && lastChar<='9') || lastChar==')' || lastChar=='!')
                {
                    result+="P"; lastChar = 'P';
                }
            }
            else if(e.getSource() == bNcr)
            {
                //this function n and r must be int >=0 and n>=r 
                if((lastChar>='0' && lastChar<='9') || lastChar==')' || lastChar=='!')
                {
                    result+="C"; lastChar = 'C';
                }
            }
            else if(e.getSource() == bPi)
            {
                if((lastChar>='0' && lastChar<='9') || lastChar=='.' || lastChar==')' || lastChar=='!' || lastChar=='π')
                {
                    result+="*π";
                    lastChar = 'π';
                }
                else
                {
                	result+="π"; lastChar = 'π';
                
                }
                
            }
            //refresh the textField every time a button is pressed with the new string
            userInput.setText(result);
            
             if(e.getSource() == bAns)
             {
            	CalcEval val = new CalcEval(result);
            	//if the string is empty 
                if(result.isEmpty())
                	JOptionPane.showMessageDialog(null, "No Input.");
                	
                else {
                	BigDecimal res = val.eval();
                        if(res.scale()>5)
                            res=res.setScale(5, RoundingMode.HALF_UP);
                        
                	if(val.isException()) {
                		JOptionPane.showMessageDialog(null, "Math Error.");
                		result="";
                        lastChar=' ';
                        userInput.setText(result);
                	}
                        
       	    		else {
                            // to format the string before outputing it on the text field
                            // if the number of digits before the decimal point >10 format it as #.10digits E
                            // else print it as a plainString
                   	    final int scale = 10;
                   	    if (res.precision()-res.scale() > scale)
                   	    	userInput.setText(String.format(result+" = %.9E", res));
                   	    else
                   	    	userInput.setText(result+" = "+res.toPlainString());
                   	    
                   	    result="";
                   	    lastChar=' ';
       	    		}
                   
                }
                
                
            }
       }

   
   }// end of Action Listener
	
	private class showHide implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent isChecked) {
                    if (! scientific.isSelected()) {
                            setSize(new Dimension(335,370));
                            primePanel.setBounds(0,0,335,370);
                            userInput.setBounds(15, 10, 290, 25);
                            buttPanel.setBounds(15, 50, 290, 45);
                            bDel.setBounds(10, 10, 60, 25);
                            bClr.setBounds(80, 10, 60, 25);
                            bAns.setBounds(150, 10, 60, 25);
                            bExit.setBounds(220, 10, 60, 25);
                            midPanel.setBounds(15, 115, 290, 150);
                            numPanel.setBounds(10, 10, 270, 130);
                            funPanel.setVisible(false);
                            emptyPanel.setVisible(false);
                    }
			
                    else {
                            funPanel.setVisible(true);
                            emptyPanel.setVisible(true);
                            setSize(new Dimension(695,370));
                            primePanel.setBounds(0, 0, 695, 370);
                            userInput.setBounds(15, 10, 650, 25);
                            buttPanel.setBounds(15, 50, 650, 50);
                            bDel.setBounds(15, 10, 60, 25);
                            bClr.setBounds(90, 10, 60, 25);
                            bAns.setBounds(500, 10, 60, 25);
                            bExit.setBounds(575, 10, 60, 25);
                            numPanel.setBounds(5, 10, 235, 130);	
                            midPanel.setBounds(15, 115, 650, 150);
                    }
				
			
		}
		
	}//end of itemListener
	
	public class KeyBoard implements KeyListener{

		@Override
		public void keyPressed(KeyEvent event) {
			
                    int key=event.getKeyCode();
                        
                    if( ! (key==KeyEvent.VK_NUMPAD1||key ==KeyEvent.VK_NUMPAD2|| key==KeyEvent.VK_NUMPAD3 ||key==KeyEvent.VK_NUMPAD4 ||
                            key==KeyEvent.VK_NUMPAD5 ||key==KeyEvent.VK_NUMPAD6 ||key==KeyEvent.VK_NUMPAD7 ||key==KeyEvent.VK_NUMPAD8 ||
                            key==KeyEvent.VK_NUMPAD9 ||key==KeyEvent.VK_NUMPAD0 ||key== 110)) 
                    {
        		isDot=false;
                    }
			
			
                    if(key==KeyEvent.VK_NUMPAD1) {
			
                        if(lastChar==')' || lastChar=='!' || lastChar=='π')
                            result+="*1";
                        else 
                           result+="1"; 
                
                        lastChar = '1';
	                
                    }
			
                    else if(key==KeyEvent.VK_NUMPAD2) {
					
                        if(lastChar==')' || lastChar=='!' || lastChar=='π')
                            result+="*2";
                        else 
                            result+="2"; 
	                
                        lastChar = '2';
	                
			}
                    else if(key==KeyEvent.VK_NUMPAD3) {
				
                        if(lastChar==')' || lastChar=='!' || lastChar=='π')
                            result+="*3";
                        else 
                            result+="3"; 
	                
                        lastChar = '3';
	                
			}
                    else if(key==KeyEvent.VK_NUMPAD4) {
				
                        if(lastChar==')' || lastChar=='!' || lastChar=='π')
                            result+="*4";
                         else 
                             result+="4"; 
	                
                        lastChar = '4';
	                
			}
                    else if(key==KeyEvent.VK_NUMPAD5) {
				
                        if(lastChar==')' || lastChar=='!' || lastChar=='π')
                            result+="*5";
                        else 
                            result+="5"; 
	                
                        lastChar = '5';
	                
                    }
                    else if(key==KeyEvent.VK_NUMPAD6) {
				
                        if(lastChar==')' || lastChar=='!' || lastChar=='π')
                            result+="*6";
                        else 
                            result+="6"; 
	                
                        lastChar = '6';
	                
                    }
                    else if(key==KeyEvent.VK_NUMPAD7) {
				
                        if(lastChar==')' || lastChar=='!' || lastChar=='π')
                            result+="*7";
                        else 
                            result+="7"; 
	                
                        lastChar = '7';
	                
			}
                    else if(key==KeyEvent.VK_NUMPAD8) {
				
                            if(lastChar==')' || lastChar=='!' || lastChar=='π')
                                result+="*8";
                            else 
                                result+="8"; 

                            lastChar = '8';
	                
			}
			else if(key==KeyEvent.VK_NUMPAD9) {

                            if(lastChar==')' || lastChar=='!' || lastChar=='π')
                                result+="*9";
                            else 
                                result+="9"; 

                            lastChar = '9';

			}
			else if(key==KeyEvent.VK_NUMPAD0) {
				
                            if(lastChar==')' || lastChar=='!' || lastChar=='π')
                                result+="*0";
                            else 
                                result+="0"; 
	                
                            lastChar = '0';
	                
			}
                        else if(key==110) {
                            if( lastChar=='(' || lastChar=='*' || lastChar=='/' || lastChar=='+'|| lastChar=='-' || result.isEmpty()) 
                            {
                                result+="0.";lastChar='.';
                                isDot = true;
                            }
            	
                            else if(lastChar==')' || lastChar=='!' || lastChar=='π')
                            {
                                result+="*0."; lastChar = '.';
                                isDot = true;
                            }
                            else if(lastChar>='0' && lastChar<='9' && !isDot)
                            {
                                result+="."; lastChar = '.';
                                isDot = true;
                            }
			}
			
			userInput.setText(result);
			
			if(key== KeyEvent.VK_ENTER)
			{
                            CalcEval val = new CalcEval(result);
                            //if the string is empty 
                            if(result.isEmpty())
                                    JOptionPane.showMessageDialog(null, "No Input.");

                            else {
                                    BigDecimal res = val.eval();
                                    if(res.scale()>5)
                                        res=res.setScale(5, RoundingMode.HALF_UP);

                                    if(val.isException()) {
                                            JOptionPane.showMessageDialog(null, "Math Error.");
                                            result="";
                                    lastChar=' ';
                                    userInput.setText(result);
                                    }

                                    else {
                                        // to format the string before outputing it on the text field
                                        // if the number of digits before the decimal point >10 format it as #.10digits E
                                        // else print it as a plainString
                                        final int scale = 10;
                                        if (res.precision()-res.scale() > scale)
                                            userInput.setText(String.format(result+" = %.9E", res));
                                        else
                                            userInput.setText(result+" = "+res.toPlainString());

                                        result="";
                                        lastChar=' ';
                                    }
                   
                }
            }
			
        }

		@Override
		public void keyReleased(KeyEvent arg0) {}

		@Override
		public void keyTyped(KeyEvent arg0) {}
		
	}
	
}//end of CalcFrame class
