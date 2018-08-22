import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.javafx.fxml.builder.JavaFXFontBuilder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Main extends JFrame implements ActionListener ,ChangeListener,KeyListener{
	
	JMenuBar mb;
	JMenu m1,m2,m3;
	JMenuItem mi1,mi2,mi3,mi4,mi5,mi6,mi7,mi8,mi9,mi10;
	JSeparator s1,s2,s3,s4;
	Font f;
	JTextArea ta;
	JSlider sl;
	int x=15;
	JLabel l1,l2,l3;
	
	public Main(int y) {
		super("Notepad");
		setLayout(null);
		f=new Font("Arial",Font.BOLD,20);
		
		mb=new JMenuBar();
		m1=new JMenu("File  ");
		m1.setFont(f);
		m2=new JMenu("Edit  ");
		m2.setFont(f);
		m3=new JMenu("Format  ");
		m3.setFont(f);
		
		mi1= new JMenuItem("New  ");
		mi1.setFont(f);
		mi2= new JMenuItem("Open  ");
		mi2.setFont(f);
		mi2.addActionListener(this);
		
		mi3= new JMenuItem("Save  ");
		mi3.setFont(f);
		mi3.addActionListener(this);
		
		mi4= new JMenuItem("Cut  ");
		mi4.setFont(f);
		mi5= new JMenuItem("Copy  ");
		mi5.setFont(f);
		mi6= new JMenuItem("Paste  ");
		mi6.setFont(f);
		mi7= new JMenuItem("Font  ");
		mi7.addActionListener(this);
		mi7.setFont(f);
		mi8= new JMenuItem("Color");
		mi8.addActionListener(this);
		mi8.setFont(f);
		
		mi9 = new JMenuItem("Background Color");
		mi9.addActionListener(this);
		mi9.setFont(f);
		
		s1=new JSeparator();             // it is a line that separates menu item.
		s2=new JSeparator();
		s3=new JSeparator();
		s4=new JSeparator();
		
		m1.add(mi1);
		m1.add(s1);
		m1.add(mi2);
		m1.add(s2);
		m1.add(mi3);
		
		m2.add(mi4);
		m2.add(s3);
		m2.add(mi5);
		m2.add(s4);
		m2.add(mi6);
		
		m3.add(mi7);
		m3.add(mi8);
		m3.add(mi9);
		
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		
		setJMenuBar(mb);
		
		l1= new JLabel("Font size :");
		l1.setBounds(5,0,100,50);
		l1.setFont(new Font("Cooper",Font.BOLD,15));
		add(l1);
		
		sl= new JSlider();
		sl.setMaximum(100);
		sl.setMinimum(0);
		sl.setValue(y);
		sl.setBounds(100,0,200,50);
		sl.addChangeListener(this);
		add(sl);
		
		ta= new JTextArea();
		ta.setBounds(2,50,1300,700);
		ta.setFont(new Font("Arial",Font.BOLD,y));
		add(ta);
		
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Main ob= new Main(15);

	}

	@Override
	public void actionPerformed(ActionEvent ac) {
		
		if(ac.getSource()==mi7) 
		{
			JOptionPane.showInputDialog(this,"Choose Font",f);
		}
		else if(ac.getSource()== mi8)
		{
		Color c= JColorChooser.showDialog(this, "Choose Color", Color.red);
		ta.setForeground(c);
		}
		else if(ac.getSource()== mi9)
		{
			Color co= JColorChooser.showDialog(this, "Choose Color", Color.red);
			ta.setBackground(co);
		}
		else if(ac.getSource()==mi2)
		{
			JFileChooser fc= new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		
			int result= fc.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION)
			{
				try
				{
			    File selectedFile = fc.getSelectedFile();
			    
			    
			    FileInputStream fi= new FileInputStream(selectedFile);
			   
				int i;
				int k=0;
				
				String str="";
				do
				{
					i=fi.read();
					if(i!=-1)
					{
						char ch= (char)i;
						//System.out.print(ch);
						str=str+ch;
					}
				}
				while(i!=-1);
			    ta.setText(str);
			    
			   // System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}
				catch(Exception e)
				{
					System.out.println(e.toString());
				}
			}

		}
		else if(ac.getSource()== mi3)
		{
			try
			{
				JFileChooser fc1= new JFileChooser();
				fc1.setCurrentDirectory(new File(System.getProperty("user.home")));
				int g=fc1.showSaveDialog(this);
				
				File selFile= fc1.getSelectedFile();
				FileOutputStream fo= new FileOutputStream(selFile);
				fo.write(ta.getText().getBytes());
				fo.close();
			
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		String str =ta.getText();
		x=(int) sl.getValue();
		ta.setFont(new Font("Arial",Font.BOLD,x));
		
		
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode()== ke.VK_CONTROL)
		{
			try
			{
				JFileChooser fc2= new JFileChooser();
				fc2.setCurrentDirectory(new File(System.getProperty("user.home")));
				int g=fc2.showSaveDialog(this);
				
				File selFile1= fc2.getSelectedFile();
				FileOutputStream fo1= new FileOutputStream(selFile1);
				fo1.write(ta.getText().getBytes());
				fo1.close();
			
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
