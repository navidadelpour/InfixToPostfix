
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MyPanel extends JPanel {

	int width = 600;
	int height = 650;
	int waitTime = 1000;
	
	BufferedImage background;
	JTextField textArea;
	JTextField waitTimeField;
	JButton submit;
	JLabel outputLabel;
	JLabel waitTimeLabel;
	JLabel processingLabel;
	Font font;
	
	JPanel opStackPanel;
	JLabel opStackLabel;
	JScrollPane opStackScroll;
	ArrayList<JButton> opStackObj;
	
	JPanel nmStackPanel;
	JLabel nmStackLabel;
	JScrollPane nmStackScroll;
	ArrayList<JButton> nmStackObj;
	
	
	Tokenizer y;
	ArrayList<String> outputChar;
	String processing;
	
	public static void main(String[] args) {
		try {
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } 
	    catch (Exception e) {}
		
		JFrame frame = new JFrame("application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new MyPanel());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	public MyPanel() {
		super(null);
		setPreferredSize(new Dimension(width, height));
		createGUI();
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					processing = "processing";
					waitTime = Integer.parseInt(waitTimeField.getText());
					y = new Tokenizer (textArea.getText());
					calculate(y);
				} catch(NumberFormatException e2) {
					waitTime = 1000;
				} catch(Exception e3) {
				
				}
			}
		});

		//2+31*-312-3*(334*1+2)-3
//		for(int i = 0; i < y.statement.length; i++)
//			System.out.print(y.statement[i] + ", ");
//		System.out.println();
		//4 2 3 * +
		// 2 31
		// +
		// 2 31 -312 * + 3 -
	}
	
	
	
	public void calculate(Tokenizer y) {
		new Thread(new Runnable() {
			public void run() {
				outputChar = new ArrayList<String>();
				String temp1 = "";
				String temp2 = "";
				for(int i = 0; i < y.statement.length; i++) {
					processing = y.statement[i];
					if(y.isInteger(y.statement[i])) {
						y.nmStack.push(y.statement[i]);
					} else if(y.statement[i].equals(")")) {
						String temp = "";
						while(true) {
							temp = y.opStack.pop();
							if(temp.equals("(")) {
//								y.nmStack.push(temp2);
//								y.nmStack.push(temp1);
								break;
							} else {
								temp1 = y.nmStack.pop();
								temp2 = y.nmStack.pop();
								y.nmStack.push(temp2 + " " + temp1 + " " + temp);
							}
							reload();
							try {
								Thread.sleep(waitTime);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} else if(pre(y.statement[i], y.lastObject(y.opStack))){
						y.opStack.push(y.statement[i]);
					} else {
						temp1 = y.nmStack.pop();
						temp2 = y.nmStack.pop();
						y.nmStack.push(temp2 + " " + temp1 + " " + y.opStack.pop());
						i--;
					}
					
					reload();
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				while(y.nmStack.size() > 1) {
					temp1 = y.nmStack.pop();
					temp2 = y.nmStack.pop();
					y.nmStack.push(temp2 + " " + temp1 + " " + y.opStack.pop());
					reload();
					try {
						Thread.sleep(waitTime);
					} catch (InterruptedException e) {
//							e.printStackTrace();
					}
				}
				processing = "process ended. see output at bottom";
				String output = nmStackObj.get(0).getText();
				String temp = "";
				for(int i = 0; i < output.length(); i++) {
					if(output.charAt(i) == ' ') {
						outputChar.add(temp);
						temp = "";
					} else {
						temp += output.charAt(i);
					}
				}
				outputChar.add(temp);
				outputLabel.setText(outputChar.toString());
				reload();
				int num1;
				int num2;
				for(int i = 0; i < outputChar.size(); i++) {
					switch(outputChar.get(i)) {
					case "+":
						reload();
						try {
							Thread.sleep(waitTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						num1 = Integer.parseInt(outputChar.get(i - 2));
						num2 = Integer.parseInt(outputChar.get(i - 1));
						outputChar.remove(i);
						outputChar.remove(i - 1);
						outputChar.remove(i - 2);
						outputChar.add(i - 2, "" + (num1 + num2));
						i = i - 2;
						break;
					case "-":
						reload();
						try {
							Thread.sleep(waitTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						num1 = Integer.parseInt(outputChar.get(i - 2));
						num2 = Integer.parseInt(outputChar.get(i - 1));
						outputChar.remove(i);
						outputChar.remove(i - 1);
						outputChar.remove(i - 2);
						outputChar.add(i - 2, "" + (num1 - num2));
						i = i - 2;
						break;
					case "*":
						reload();
						try {
							Thread.sleep(waitTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						num1 = Integer.parseInt(outputChar.get(i - 2));
						num2 = Integer.parseInt(outputChar.get(i - 1));
						outputChar.remove(i);
						outputChar.remove(i - 1);
						outputChar.remove(i - 2);
						outputChar.add(i - 2, "" + (num1 * num2));
						i = i - 2;
						break;
					case "/":
						reload();
						try {
							Thread.sleep(waitTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						num1 = Integer.parseInt(outputChar.get(i - 2));
						num2 = Integer.parseInt(outputChar.get(i - 1));
						outputChar.remove(i);
						outputChar.remove(i - 1);
						outputChar.remove(i - 2);
						outputChar.add(i - 2, "" + (num1 / num2));
						i = i - 2;
						break;
					}
				}
				reload();

			}
		}).start();
		
	}
	
	public boolean pre(String s1, String s2) {
		switch(s1) {
		case "+":
		case "-":
			if(s2.equals("") || s2.equals("(")) return true;
			else return false;
		case "*":
		case "/":
			if(s2.equals("+") || s2.equals("-") || s2.equals("") || s2.equals("(")) return true;
			else return false;
		case "(":
//			if(s2.equals("")) return true;
//			else return false;
			return true;
		}
		return false;
	}
	
	public void reload() {
		outputLabel.setText(outputChar.toString());
		processingLabel.setText("processing : " + processing);
//		for(int i = 0; i < nmStackObj.size(); i++) {
			nmStackPanel.removeAll();
			nmStackObj.removeAll(nmStackObj);
//		}
			opStackPanel.removeAll();
			opStackObj.removeAll(opStackObj);
			
		for(int i = 0; i < y.nmStack.size(); i++) {
			JButton button = new JButton(y.nmStack.get(i));
			button.setFont(font);
			nmStackObj.add(button);
			nmStackPanel.add(nmStackObj.get(i));
		}
		for(int i = 0; i < y.opStack.size(); i++) {
			JButton button = new JButton(y.opStack.get(i));
			button.setFont(font);
			opStackObj.add(button);
			opStackPanel.add(opStackObj.get(i));
		}
//		for(int i = 0; i < nmStackObj.size(); i++) {
//			System.out.print(nmStackObj.get(i).getText() + "_");
//		}
//		System.out.println();
		repaint();
		doLayout();
		revalidate();
	}
	
	@Override
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		g2.drawImage(background, 0, 0, width + 30, height + 30, null);
	}
	
	public void createGUI() {
		try {
            background = ImageIO.read(getClass().getResourceAsStream("background.jpg"));
        } catch(IOException e) {
            e.printStackTrace();
        };
		font = new Font("Arial", Font.PLAIN, 16);
		
		waitTimeField = new JTextField(waitTime + "");
		waitTimeField.setFont(font);
		waitTimeField.setBounds(450, 25, 100, 25);

		waitTimeLabel = new JLabel("wait time (in milli seconds) : ");
		waitTimeLabel.setFont(font);
		waitTimeLabel.setBounds(250, 25, 300, 25);
		
		textArea = new JTextField("please enter your statement to calculate");
		textArea.setFont(font);
		textArea.setBounds(50, 50, 500, 25);
	
		submit = new JButton("calculate");
		submit.setFont(font);
		submit.setBounds(450, 75, 100, 25);
		
		outputLabel = new JLabel("output:");
		outputLabel.setFont(font);
		outputLabel.setBounds(50, 500, 600, 200);
		
		processingLabel = new JLabel("waiting for entering statement");
		processingLabel.setFont(font);
		processingLabel.setBounds(50, 550, 500, 50);
		
		opStackPanel = new JPanel();
		opStackLabel = new JLabel("Operation stack");
		opStackObj = new ArrayList<JButton>();
		opStackLabel.setFont(font);
		opStackLabel.setBounds(120, 100, 250, 100);
		opStackPanel.setLayout(new GridLayout(10,1));
		opStackScroll = new JScrollPane(opStackPanel);
		opStackScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		opStackScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		opStackScroll.setBounds(50, 175, 250, 350);
		
		nmStackPanel = new JPanel();
		nmStackLabel = new JLabel("Numeration stack");
		nmStackObj = new ArrayList<JButton>();
		nmStackLabel.setFont(font);
		nmStackLabel.setBounds(370, 100, 250, 100);
//		for (int i = 0; i < 10; i++) {
//			nmStackObj.add(new JButton("Hello-" + i));
//	          nmStack.add(nmStackObj.get(i));
//			}
		nmStackPanel.setLayout(new GridLayout(10,1));
		nmStackScroll = new JScrollPane(nmStackPanel);
		nmStackScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		nmStackScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		nmStackScroll.setBounds(300, 175, 250, 350);

		add(waitTimeField);
		add(waitTimeLabel);
		add(textArea);
		add(submit);
		add(outputLabel);
		add(processingLabel);
		add(nmStackScroll);
		add(nmStackLabel);
		add(opStackScroll);
		add(opStackLabel);
	}
	
}
