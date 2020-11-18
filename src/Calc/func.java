package Calc;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.Timer;

import java.text.DecimalFormat;
import java.util.*;
import java.lang.Math;

public class func extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	public JTextPane screen = new JTextPane();	//Question Screen
	public JTextPane ascreen = new JTextPane(); //Answer Screen
	public JScrollPane sscreen,asscreen;		//Scroll Screen for both
	public JButton[] butt = new JButton[9];
	public JButton[] obutt = new JButton[14];
	int i,j;
	public String s="";
	public strcrt bhand = new strcrt();			//Action Listener for String Creation
	public strprc jhand = new strprc();			//Action Listener for = button
	ArrayList<String> exp = new ArrayList<String>(); //The expression(exp) for calculation
	Timer delay = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ascreen.setText("");
        }
    });
	int btyped = 0;
	double res=0.0;
	
	public func()
	{
		super("Calculator");
		setLayout(new FlowLayout());
		//setUndecorated(true);
		//setBackground(new Color(0,0,0,0));
		getContentPane().setBackground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Icon1.png")));
		
		//screen = new JTextPane();
		screen.setPreferredSize(new Dimension(445,60));
		//screen.setHorizontalAlignment(JTextPane.);
		screen.setEditable(true);
		screen.setBackground(Color.WHITE);
		screen.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				screen.setText("");
				screen.setText(s);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				screen.setText("");
				screen.setText(s);
			}
			@Override
			public void keyTyped(KeyEvent e) 
			{
			  if (e.getKeyChar()=='\b' && s.length()>0)
			  {
				  if (s.charAt(s.length()-1)==')')
			      btyped++;
				  if (s.charAt(s.length()-1)=='(')
				  btyped--;
				  s = s.substring(0, s.length()-1);
			  }		  
			  else if ((e.getKeyChar()>='0' && e.getKeyChar()<='9') || (e.getKeyChar()=='.' && s.length()>0))
			  {
				  s = s + e.getKeyChar();
			  }
			  else if (e.getKeyChar()=='(' || e.getKeyChar()==')')
			  {
				  if (e.getKeyChar()=='(' && !(s.length()==1 && s.charAt(0)=='-'))
				  {
					   s = s + e.getKeyChar();
					   btyped++;
				  }
				  else if (e.getKeyChar()==')' && btyped>0 && s.charAt(s.length()-1)!='(')
				  {
					  s = s + e.getKeyChar();
					  btyped--;
				  }
			  }
			  else if (e.getKeyChar()=='+' && s.length()>0)
			  {
			      if (s.charAt(s.length()-1)=='.' || s.charAt(s.length()-1)=='×' || s.charAt(s.length()-1)=='÷' || s.charAt(s.length()-1)=='^' || s.charAt(s.length()-1)=='+' || s.charAt(s.length()-1)=='-' || s.charAt(s.length()-1)=='(')
				  {
				  		if (s.charAt(s.length()-1)=='(')
				  		btyped--;
			  			s = s.substring(0, s.length()-1);
			  			s = s + e.getKeyChar();
			  	  }
				  else
				  s = s + e.getKeyChar();
			  }
		  	  else if (e.getKeyChar()=='-')
		  	  {
		  		  if (s.length()==0)
		  		  s = s + e.getKeyChar();
		  		  else if (s.charAt(s.length()-1)=='.')
		  		  {
		  			s = s.substring(0, s.length()-1);
		  			s = s + "×";
		  			s = s + e.getKeyChar();
		  		  }
		  		  else if (s.charAt(s.length()-1)=='+' || s.charAt(s.length()-1)=='-')
		  		  {
			  			s = s.substring(0, s.length()-1);
			  			s = s + e.getKeyChar();
			  	  }
		  		  else
		  		  s = s + e.getKeyChar();
		  	  }
			  else if ((e.getKeyChar()=='x' || e.getKeyChar()=='X' || e.getKeyChar()=='*') && s.length()>0)
			  {
				  if (s.charAt(s.length()-1)=='.' || s.charAt(s.length()-1)=='×' || s.charAt(s.length()-1)=='÷' || s.charAt(s.length()-1)=='^' || s.charAt(s.length()-1)=='+' || s.charAt(s.length()-1)=='-' || s.charAt(s.length()-1)=='(')
				  {
			    	    if (s.charAt(s.length()-1)=='(')
				  		btyped--;
			  			s = s.substring(0, s.length()-1);
			  			s = s + "×";
			  	  }
				  else
				  s = s + "×";
			  }
			  else if ((e.getKeyChar()=='/' || e.getKeyChar()=='÷') && s.length()>0)
			  {
				  if (s.charAt(s.length()-1)=='.' || s.charAt(s.length()-1)=='×' || s.charAt(s.length()-1)=='÷' || s.charAt(s.length()-1)=='^' || s.charAt(s.length()-1)=='+' || s.charAt(s.length()-1)=='-' || s.charAt(s.length()-1)=='(')
				  {
			    	    if (s.charAt(s.length()-1)=='(')
				  		btyped--;
			  			s = s.substring(0, s.length()-1);
			  			s = s + "÷";
			  	  }
				  else
				  s = s + "÷";
			  }
			  else if (e.getKeyChar()=='^' && s.length()>0)
			  {
				  if (s.charAt(s.length()-1)=='.' || s.charAt(s.length()-1)=='×' || s.charAt(s.length()-1)=='÷' || s.charAt(s.length()-1)=='^' || s.charAt(s.length()-1)=='+' || s.charAt(s.length()-1)=='-' || s.charAt(s.length()-1)=='(')
				  {
			    	    if (s.charAt(s.length()-1)=='(')
				  		btyped--;
			  			s = s.substring(0, s.length()-1);
			  			s = s + "^";
			  	  }
				  else
				  s = s + "^";
			  }
			  else if (e.getKeyChar()=='\n' && btyped==0)
			  {
				  delay.stop();
				  screen.setText(s);
				  System.out.println("Enter Pressed");
				  if (btyped!=0 || s.length()==0 || ((s.charAt(s.length()-1)<'0' || s.charAt(s.length()-1)>'9') && s.charAt(s.length()-1)!=')') || (s.charAt(0)=='-' && s.charAt(1)=='('))
				  {
					  if (s.length()==0)
					  ascreen.setText("Void Expression");
					  else
					  ascreen.setText("Math Error");
					  delay.start();
					  screen.setText("");
			          s="";
					  /*
					  try 
					  {
						Thread.sleep(500);
						ascreen.setText("");
					  } 
					  catch (InterruptedException e1) 
					  {
						e1.printStackTrace();
						ascreen.setText("");
					  }
					  */
				  }
				  else
				  expcreator();
			  }
			  else if (e.getKeyChar()=='c')
			  {
			   btyped=0;
			   s="";
			   screen.setText("");
			   ascreen.setText("");
			  }
			  /*else if (e.getKeyChar()=='a')
			  {
				  if (s.length()==0 || s.charAt(s.length()-1)==')')
				  s = s + String.format("%f", res);
				  else
				  s = s + String.format("×%f", res);
			  }*/
			  screen.setText(s);
			}
		});
		screen.setFont(new Font("Arial", Font.PLAIN, 42));
		
		sscreen = new JScrollPane(screen);
		sscreen.setPreferredSize(new Dimension(445,70));
		add(sscreen);
		
		obutt[10] = new JButton();
		obutt[10].setPreferredSize(new Dimension(70,70));
		obutt[10].setIcon(new ImageIcon(getClass().getResource("cro.jpg")));
		obutt[10].setRolloverIcon(new ImageIcon(getClass().getResource("croc.jpg")));
		obutt[10].setBorderPainted(false);
		obutt[10].setActionCommand("<");
		obutt[10].addActionListener(bhand);
		add(obutt[10]);
		

		obutt[9] = new JButton();
		obutt[9].setPreferredSize(new Dimension(70,70));
		obutt[9].setIcon(new ImageIcon(getClass().getResource("eq.jpg")));
		obutt[9].setRolloverIcon(new ImageIcon(getClass().getResource("eqc.jpg")));
		obutt[9].setBorderPainted(false);
		add(obutt[9]);
		
		ascreen.setPreferredSize(new Dimension(445,70));
		ascreen.setEditable(false);
		ascreen.setBackground(Color.CYAN);
		ascreen.setFont(new Font("Arial", Font.PLAIN, 50));
		
		asscreen = new JScrollPane(ascreen);
		asscreen.setPreferredSize(new Dimension(445,70));
		add(asscreen);
		
		obutt[0] = new JButton();
		obutt[0].setPreferredSize(new Dimension(100,100));
		obutt[0].setIcon(new ImageIcon(getClass().getResource("pl.png")));
		obutt[0].setRolloverIcon(new ImageIcon(getClass().getResource("plr.png")));
		obutt[0].setPressedIcon(new ImageIcon(getClass().getResource("plc.png")));
		obutt[0].setBorderPainted(false);
		obutt[0].setActionCommand("+");
		add(obutt[0]);
		
		for (i=0;i<3;i++)
		{
			butt[i] = new JButton();
			butt[i].setPreferredSize(new Dimension(100,100));
			butt[i].setIcon(new ImageIcon(getClass().getResource(String.format("%d.png", i+1))));
			butt[i].setRolloverIcon(new ImageIcon(getClass().getResource(String.format("%dc.png", i+1))));
			butt[i].setBorderPainted(false);
			butt[i].setActionCommand(String.format("%d",i+1));
			add(butt[i]);
		}
		
		obutt[11] = new JButton();
		obutt[11].setPreferredSize(new Dimension(100,100));
		obutt[11].setIcon(new ImageIcon(getClass().getResource("c.png")));
		obutt[11].setRolloverIcon(new ImageIcon(getClass().getResource("cr.png")));
		obutt[11].setPressedIcon(new ImageIcon(getClass().getResource("cc.png")));
		obutt[11].setBorderPainted(false);
		obutt[11].setActionCommand("C");
		obutt[11].setFont(new Font("Arial", Font.PLAIN, 50));
		obutt[11].addActionListener(bhand);
		add(obutt[11]);
		
		obutt[1] = new JButton();
		obutt[1].setPreferredSize(new Dimension(100,100));
		obutt[1].setIcon(new ImageIcon(getClass().getResource("-.png")));
		obutt[1].setRolloverIcon(new ImageIcon(getClass().getResource("-r.png")));
		obutt[1].setPressedIcon(new ImageIcon(getClass().getResource("-c.png")));
		obutt[1].setBorderPainted(false);
		obutt[1].setBackground(Color.ORANGE);
		obutt[1].setActionCommand("-");
		obutt[1].setFont(new Font("Arial", Font.PLAIN, 50));
		add(obutt[1]);
		
		for (i=3;i<6;i++)
		{
			butt[i] = new JButton();
			butt[i].setPreferredSize(new Dimension(100,100));
			butt[i].setIcon(new ImageIcon(getClass().getResource(String.format("%d.png", i+1))));
			butt[i].setRolloverIcon(new ImageIcon(getClass().getResource(String.format("%dc.png", i+1))));
			butt[i].setBorderPainted(false);
			butt[i].setActionCommand(String.format("%d",i+1));
			add(butt[i]);
		}
		
		obutt[7] = new JButton();
		obutt[7].setPreferredSize(new Dimension(46,100));
		obutt[7].setIcon(new ImageIcon(getClass().getResource("lbr.jpg")));
		obutt[7].setRolloverIcon(new ImageIcon(getClass().getResource("lbrc.jpg")));
		obutt[7].setBorderPainted(false);
		obutt[7].setActionCommand("(");
		add(obutt[7]);
		
		obutt[8] = new JButton();
		obutt[8].setPreferredSize(new Dimension(47,100));
		obutt[8].setIcon(new ImageIcon(getClass().getResource("rbr.jpg")));
		obutt[8].setRolloverIcon(new ImageIcon(getClass().getResource("rbrc.jpg")));
		obutt[8].setBorderPainted(false);
		obutt[8].setActionCommand(")");
		add(obutt[8]);
		
		obutt[2] = new JButton();
		obutt[2].setPreferredSize(new Dimension(100,100));
		obutt[2].setIcon(new ImageIcon(getClass().getResource("x.png")));
		obutt[2].setRolloverIcon(new ImageIcon(getClass().getResource("xr.png")));
		obutt[2].setPressedIcon(new ImageIcon(getClass().getResource("xc.png")));
		obutt[2].setBorderPainted(false);
		obutt[2].setActionCommand("×");
		add(obutt[2]);
		
		for (i=6;i<9;i++)
		{
			butt[i] = new JButton();
			butt[i].setPreferredSize(new Dimension(100,100));
			butt[i].setIcon(new ImageIcon(getClass().getResource(String.format("%d.png", i+1))));
			butt[i].setRolloverIcon(new ImageIcon(getClass().getResource(String.format("%dc.png", i+1))));
			butt[i].setBorderPainted(false);
			butt[i].setActionCommand(String.format("%d",i+1));
			add(butt[i]);
		}
		
		obutt[6] = new JButton();
		obutt[6].setPreferredSize(new Dimension(100,100));
		obutt[6].setIcon(new ImageIcon(getClass().getResource("p.png")));
		obutt[6].setRolloverIcon(new ImageIcon(getClass().getResource("pr.png")));
		obutt[6].setPressedIcon(new ImageIcon(getClass().getResource("pc.png")));
		obutt[6].setBorderPainted(false);
		obutt[6].setActionCommand("^");
		add(obutt[6]);
		
		obutt[3] = new JButton();
		obutt[3].setPreferredSize(new Dimension(100,100));
		obutt[3].setIcon(new ImageIcon(getClass().getResource("d.png")));
		obutt[3].setRolloverIcon(new ImageIcon(getClass().getResource("dr.png")));
		obutt[3].setPressedIcon(new ImageIcon(getClass().getResource("dc.png")));
		obutt[3].setBorderPainted(false);
		obutt[3].setActionCommand("÷");
		add(obutt[3]);
		
		obutt[5] = new JButton();
		obutt[5].setPreferredSize(new Dimension(100,100));
		obutt[5].setIcon(new ImageIcon(getClass().getResource("do.png")));
		obutt[5].setRolloverIcon(new ImageIcon(getClass().getResource("doc.png")));
		obutt[5].setBorderPainted(false);
		obutt[5].setActionCommand(".");
		add(obutt[5]);
		
		obutt[4] = new JButton();
		obutt[4].setPreferredSize(new Dimension(100,100));
		obutt[4].setIcon(new ImageIcon(getClass().getResource("0.png")));
		obutt[4].setRolloverIcon(new ImageIcon(getClass().getResource("0c.png")));
		obutt[4].setBorderPainted(false);
		obutt[4].setActionCommand("0");
		add(obutt[4]);

		obutt[12] = new JButton();
		obutt[12].setPreferredSize(new Dimension(100,100));
		obutt[12].setIcon(new ImageIcon(getClass().getResource("ans.png")));
		obutt[12].setRolloverIcon(new ImageIcon(getClass().getResource("ansc.png")));
		obutt[12].setBorderPainted(false);
		obutt[12].setActionCommand("A");
		obutt[12].addActionListener(bhand);
		add(obutt[12]);
		
		obutt[13] = new JButton();
		obutt[13].setPreferredSize(new Dimension(100,100));
		obutt[13].setIcon(new ImageIcon(getClass().getResource("r.png")));
		obutt[13].setRolloverIcon(new ImageIcon(getClass().getResource("rr.png")));
		obutt[13].setPressedIcon(new ImageIcon(getClass().getResource("rc.png")));
		obutt[13].setBorderPainted(false);
		obutt[13].setActionCommand("R");
		obutt[13].addActionListener(bhand);
		add(obutt[13]);
		
		for (i=0;i<9;i++)
		{
			butt[i].addActionListener(bhand);
		}
		for (i=0;i<9;i++)
		{
			 obutt[i].addActionListener(bhand);
		}
		
		obutt[9].addActionListener(jhand);
	}
	
	public void expcreator() //This sets up the collection exp from string s
	{
		int ii;
		//double res;
		DecimalFormat df = new DecimalFormat("######.######");
		String comp="";
		for (ii=0;ii<s.length();ii++)
		{
			  if (s.charAt(ii)>='0' && s.charAt(ii)<='9' || s.charAt(ii)=='.')
			  {
				  comp=comp+Character.toString(s.charAt(ii));
				  System.out.printf("comp=%s",comp);
			  }
			  else if (s.charAt(ii)=='(' || s.charAt(ii)==')')
			  {
				  if (!comp.equals(""))
				  exp.add(comp);
				  comp="";
				  exp.add(String.valueOf(s.charAt(ii)));
			  }
			  else if (s.charAt(ii)=='×')
			  {
				  if (!comp.equals(""))
				  exp.add(comp);
				  comp="";
				  exp.add(String.valueOf(s.charAt(ii)));
			  }
			  else if (s.charAt(ii)=='÷')
			  {
				  if (!comp.equals(""))
				  exp.add(comp);
				  comp="";
				  exp.add(String.valueOf(s.charAt(ii)));
			  }
			  else if (s.charAt(ii)=='+')
			  {  
				  if (!comp.equals(""))
				  exp.add(comp);
				  comp="";
				  exp.add(String.valueOf(s.charAt(ii)));
			  }
			  else if (s.charAt(ii)=='-')
			  {
				  if (ii==0 || ((s.charAt(ii-1)<'0' || s.charAt(ii-1)>'9') && s.charAt(ii-1)!=')'))
				  {
					  comp=comp+Character.toString(s.charAt(ii));
					  System.out.printf("comp=%s",comp);
			      }
				  else
				  {
					  if (!comp.equals(""))
					  exp.add(comp);
					  comp="";
					  exp.add(String.valueOf(s.charAt(ii)));
				  }
			  }
			  else if (s.charAt(ii)=='^')
			  {
				  if (!comp.equals(""))
				  exp.add(comp);
				  comp="";
				  exp.add(String.valueOf(s.charAt(ii)));
			  }
			  else if (s.charAt(ii)=='\u221A')
			  {
				  if (!comp.equals(""))
				  exp.add(comp);
				  comp="";
				  exp.add(String.valueOf(s.charAt(ii)));
			  }
		}
		if (!comp.equals(""))
		exp.add(comp);
		System.out.println("exp Display:");
		for (String xxx: exp.toArray(new String[exp.size()]))
		  {
			  System.out.printf("[%s] ",xxx);
		  }
		//ascreen.setText(String.format("%f",calc(exp)));
		res=calc(exp);
		if (res>10000000)
		ascreen.setText(String.format("%e",res));
		else if (res==(int)res)
		ascreen.setText(String.format("%d",(int)res));
		else
		ascreen.setText(df.format(res));
		exp.clear();
	}
	
	public double calc(ArrayList<String> exp1) // exp1 initially stores expression then it stores primary expression chunk
	{
		int z,c=0,b,br; //br=counter of brackets      b=iterator of exp1
		double ans=0.0;
		String[] stri = exp1.toArray(new String[exp1.size()]);
		String[] s2 = new String[stri.length];
		exp1.clear();
		for (z=0;z<stri.length;z++)
		{
			 if (stri[z].equals("("))
			 {   
				 br=1;
				 b=z+1;
				 while (true)
				 {
					 if (stri[b].equals("("))
					 br++; 
					 else if (stri[b].equals(")"))
					 {
				      if (br==1)
					  break;
				      else if (br>1)
				      br--;
					 }
					 exp1.add(stri[b]);
					 b++;
				 }
				 System.out.println("\nexp1 Display:");
					for (String xxx: exp1.toArray(new String[exp1.size()]))
					  {
						  System.out.printf("[%s] ",xxx);
					  }
				 if (z!=0 && Character.isDigit(stri[z-1].charAt(0)))
				 {
					 s2[c]="×";
					 c++;
					 s2[c]=Double.toString(calc(exp1));
					 c++;
					 if (b!=stri.length-1 && (Character.isDigit(stri[b+1].charAt(0)) || stri[b+1].charAt(0)=='('))
					 {
						 s2[c]="×";
						 c++;
					 }
				 }
				 else if (b!=stri.length-1 && (Character.isDigit(stri[b+1].charAt(0)) || stri[b+1].charAt(0)=='('))
				 {
					 s2[c]=Double.toString(calc(exp1));
					 c++;
					 s2[c]="×";
					 c++;
				 }
				 else
				 {
					 s2[c]=Double.toString(calc(exp1));
					 c++;
				 }
				 z=b;
			 }
			 else
			 {
				 s2[c]=stri[z];
				 c++;
			 }
		}
		stri = new String[c];
		c=0;
		System.out.println("\ns2 Display:");
		for (String xxx: s2)
		  {
			if (xxx!=null && !xxx.isEmpty())
			{
			  System.out.printf("[%s] ",xxx);
		      stri[c]=s2[c];
			  c++;
			}
		  }
		s2 = new String[stri.length];
		c=0;
		for (z=0;z<stri.length;z++)
		{
			 if (stri[z].equals("\u221A"))
			 {
			     if (z==0 || !Character.isDigit(stri[z-1].charAt(0)))
				 {
				  s2[c]=Double.toString(Math.sqrt(Double.parseDouble(stri[z+1])));
				  c++;
				 }
				 else
				 s2[c-1]=Double.toString(Math.pow(Math.E, Math.log(Double.parseDouble(stri[z+1]))/Double.parseDouble(stri[z-1])));
				 z+=1;
			 }
			 else
			 {
				 s2[c]=stri[z];
				 c++;
			 }
		}
		stri = new String[c];
		c=0;
		for (String xxx: s2)
		  {
			if (xxx!=null && !xxx.isEmpty())
			{
		      stri[c]=s2[c];
			  c++;
			}
		  }
		s2 = new String[stri.length];
		c=0;
		for (z=0;z<stri.length;z++)
		{
			 if (stri[z].equals("^"))
			 {
				 s2[c-1]=Double.toString(Math.pow(Double.parseDouble(stri[z-1]),Double.parseDouble(stri[z+1])));
				 z+=1;
			 }
			 else
			 {
				 s2[c]=stri[z];
				 c++;
			 }
		}
		stri = new String[c];
		c=0;
		for (String xxx: s2)
		  {
			if (xxx!=null && !xxx.isEmpty())
			{
		      stri[c]=s2[c];
			  c++;
			}
		  }
		s2 = new String[stri.length];
		c=0;
		for (z=0;z<stri.length;z++)
		{
			 if (stri[z].equals("÷"))
			 {
				 s2[c-1]=Double.toString(Double.parseDouble(stri[z-1])/Double.parseDouble(stri[z+1]));
				 z+=1;
			 }
			 else
			 {
				 s2[c]=stri[z];
				 c++;
			 }
		}
		stri = new String[c];
		c=0;
		for (String xxx: s2)
		  {
			if (xxx!=null && !xxx.isEmpty())
			{
		      stri[c]=s2[c];
			  c++;
			}
		  }
		s2 = new String[stri.length];
		c=0;
		for (z=0;z<stri.length;z++)
		{
			 if (stri[z].equals("×"))
			 {
				 s2[c-1]=Double.toString(Double.parseDouble(stri[z-1])*Double.parseDouble(stri[z+1]));
				 stri[z+1]=s2[c-1];
				 z+=1;
			 }
			 else
			 {
				 s2[c]=stri[z];
				 c++;
			 }
		}
		stri = new String[c];
		c=0;
		for (String xxx: s2)
		  {
			if (xxx!=null && !xxx.isEmpty())
			{
		      stri[c]=s2[c];
			  c++;
			}
		  }
		ans+=Double.parseDouble(stri[0]);
		for (z=1;z<stri.length;z++)
		{
			if (stri[z].equals("+"))
		    ans+=Double.parseDouble(stri[z+1]);
			else if (stri[z].equals("-"))
			ans-=Double.parseDouble(stri[z+1]);
		}
		return ans;
	}
	
	public class strprc implements ActionListener //String Processor for in application call (The Calculation)
	{
		public void actionPerformed(ActionEvent e)
		{
			delay.stop();
			if (s.length()==0 || ((s.charAt(s.length()-1)<'0' || s.charAt(s.length()-1)>'9') && s.charAt(s.length()-1)!=')'))
			  {
				  if (s.length()==0)
				  ascreen.setText("Void Expression");
				  else
				  ascreen.setText("Math Error");
				  delay.start();
				  screen.setText("");
		          s="";
				  /*
				  try 
				  {
					Thread.sleep(500);
					ascreen.setText("");
				  } 
				  catch (InterruptedException e1) 
				  {
					e1.printStackTrace();
					ascreen.setText("");
				  }
				  */
			  }
			  else if (btyped==0)
			  expcreator();
			  screen.setText(s);
			  screen.requestFocus();
		}
	}
	
	public class strcrt implements ActionListener //String Creator
	{
		public void actionPerformed(ActionEvent e)
		{
			  if (e.getActionCommand().charAt(0)=='<' && s.length()>0)
			  {
				  if (s.charAt(s.length()-1)==')')
			      btyped++;
				  if (s.charAt(s.length()-1)=='(')
				  btyped--;
				  s = s.substring(0, s.length()-1);
			  }		  
			  else if ((e.getActionCommand().charAt(0)>='0' && e.getActionCommand().charAt(0)<='9'))
			  {
				  s = s + e.getActionCommand();
			  }
			  else if (e.getActionCommand().charAt(0)=='(' || e.getActionCommand().charAt(0)==')')
			  {
				  if (e.getActionCommand().charAt(0)=='(' && !(s.length()==1 && s.charAt(0)=='-'))
				  {
					   s = s + e.getActionCommand();
					   btyped++;
				  }
				  else if (e.getActionCommand().charAt(0)==')' && btyped>0  && s.charAt(s.length()-1)!='(' && s.charAt(s.length()-1)!='÷' && s.charAt(s.length()-1)!='×' && s.charAt(s.length()-1)!='^' && s.charAt(s.length()-1)!='\u221A' && s.charAt(s.length()-1)!='+' && s.charAt(s.length()-1)!='-')
				  {
					  s = s + e.getActionCommand();
					  btyped--;
				  }
			  }
			  else if (e.getActionCommand().charAt(0)=='.' && s.length()>0)
			  {
				  if (s.charAt(s.length()-1)!='.' && s.charAt(s.length()-1)!='(' && s.charAt(s.length()-1)!='×' && s.charAt(s.length()-1)!='÷' && s.charAt(s.length()-1)!='^' && s.charAt(s.length()-1)!='\u221A' && s.charAt(s.length()-1)!='+' && s.charAt(s.length()-1)!='-' && s.charAt(s.length()-1)!=')')
				  s = s + ".";
				  else
				  {
					  if (s.charAt(s.length()-1)=='(')
					  btyped--;
				   	  s = s.substring(0, s.length()-1);
				  	  s = s + ".";
				  }
			  }
			  else if (e.getActionCommand().charAt(0)=='+' && s.length()>0)
			  {
				  if (s.charAt(s.length()-1)!='×' && s.charAt(s.length()-1)!='÷' && s.charAt(s.length()-1)!='^' && s.charAt(s.length()-1)!='\u221A' && s.charAt(s.length()-1)!='+' && s.charAt(s.length()-1)!='-' && s.charAt(s.length()-1)!='(' && s.charAt(s.length()-1)!='.')
				  s = s + e.getActionCommand();
				  else
				  {
			    	    if (s.charAt(s.length()-1)=='(')
				  		btyped--;
			  			s = s.substring(0, s.length()-1);
			  			s = s + '+';
			  	  }	  
			  }
		  	  else if (e.getActionCommand().charAt(0)=='-')
		  	  {
		  		  if (s.length()==0)
		  		  s = s + e.getActionCommand();
		  		  else if (s.charAt(s.length()-1)=='.' || s.charAt(s.length()-1)=='+')
		  		  {
		  			if (s.charAt(s.length()-1)=='.')
		  			{
		  				s = s.substring(0, s.length()-1);
		  				s = s + "×";
		  			}
		  			else
		  			s = s.substring(0, s.length()-1);
		  			s = s + e.getActionCommand();
		  		  }
		  		  else if (s.charAt(s.length()-1)!='-' && s.charAt(s.length()-1)!='\u221A')
		  		  s = s + e.getActionCommand();
		  	  }
			  else if (e.getActionCommand().charAt(0)=='×' && s.length()>0)
			  {
				  if (s.charAt(s.length()-1)!='×' && s.charAt(s.length()-1)!='÷' && s.charAt(s.length()-1)!='^' && s.charAt(s.length()-1)!='\u221A' && s.charAt(s.length()-1)!='+' && s.charAt(s.length()-1)!='-' && s.charAt(s.length()-1)!='(' && s.charAt(s.length()-1)!='.')
				  s = s + '×';
				  else
				  {
			    	    if (s.charAt(s.length()-1)=='(')
				  		btyped--;
			  			s = s.substring(0, s.length()-1);
			  			s = s + '×';
			  	  }	  
			  }
			  else if (e.getActionCommand().charAt(0)=='÷' && s.length()>0)
			  {
				  if (s.charAt(s.length()-1)!='×' && s.charAt(s.length()-1)!='÷' && s.charAt(s.length()-1)!='^' && s.charAt(s.length()-1)!='\u221A' && s.charAt(s.length()-1)!='+' && s.charAt(s.length()-1)!='-' && s.charAt(s.length()-1)!='(' && s.charAt(s.length()-1)!='.')
				  s = s + '÷';
				  else
				  {
			    	    if (s.charAt(s.length()-1)=='(')
				  		btyped--;
			  			s = s.substring(0, s.length()-1);
			  			s = s + '÷';
			  	  }	  
			  }
			  else if (e.getActionCommand().charAt(0)=='^' && s.length()>0)
			  {
				  if (s.charAt(s.length()-1)!='×' && s.charAt(s.length()-1)!='÷' && s.charAt(s.length()-1)!='^' && s.charAt(s.length()-1)!='\u221A' && s.charAt(s.length()-1)!='+' && s.charAt(s.length()-1)!='-' && s.charAt(s.length()-1)!='(' && s.charAt(s.length()-1)!='.')
				  s = s + '^';
				  else
				  {
			    	    if (s.charAt(s.length()-1)=='(')
				  		btyped--;
			  			s = s.substring(0, s.length()-1);
			  			s = s + '^';
			  	  }	  
			  }

			  else if (e.getActionCommand().charAt(0)=='R')
			  {
			  	  if (s.length()==0)
			  	  s = s + '\u221A';
				  else if (s.charAt(s.length()-1)!='\u221A' && s.charAt(s.length()-1)!='.')
				  s = s + '\u221A';
			  }
			  else if (e.getActionCommand().charAt(0)=='C')
			  {
			   btyped=0;
			   s="";
			   screen.setText("");
			   ascreen.setText("");
			  }
			  else if (e.getActionCommand().charAt(0)=='A' && res!=0.0)
			  {
				  if (s.length()==0 || s.charAt(s.length()-1)==')')
				  s = s + String.format("%f", res);
				  else
				  s = s + String.format("×%f", res);
			  }
			screen.setText(s);
			screen.requestFocus();
		}
	}
}
