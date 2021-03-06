package textEditor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class EditorFrame extends JFrame {
	private JTextArea _textArea;
	private JFileChooser _choose;
	private File _currentFile;
	private ActionListener _a;
	public EditorFrame(){
		setUpEditor();
	}
	/*
	 * This method sets up the Text Editor plus basic functionality including saving and loading
	 * .txt files
	 */
	private void setUpEditor(){
		//set JFrame stuff up
		this.setTitle("Text Edit");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		_choose = new JFileChooser();
		//instantiate panel and set size
		JPanel panel = new JPanel();
		Dimension panelSize = new Dimension(600,400);
		panel.setPreferredSize(panelSize);
		this.setContentPane(panel);
			
		//set up menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("file");
		JMenu edit = new JMenu("edit");
		
		//add buttons to various menu bar things (JMenu)
		JMenuItem newFile = new JMenuItem("new");
		JMenuItem open = new JMenuItem("open");
		JMenuItem save = new JMenuItem("save");
		JMenuItem saveAs = new JMenuItem("save as");
		JMenuItem exit = new JMenuItem("exit");
		JMenuItem delay = new JMenuItem("delay");
		
		//put everything together
		//file
		menuBar.add(file);
		file.add(newFile);
		file.add(open);
		file.add(save);
		file.add(saveAs);
		file.add(exit);
		//edit
		menuBar.add(edit);
		edit.add(delay);
		
		//add action listeners to everything
		//open file
		open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int openResult = _choose.showOpenDialog(null);
				if(openResult == JFileChooser.APPROVE_OPTION){
					openFile(_choose.getSelectedFile());
				}
			}
			
		});
		//save file
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(_currentFile==null){
					int saveResult = _choose.showSaveDialog(null);
					if(saveResult== JFileChooser.APPROVE_OPTION){
						saveFile(_choose.getSelectedFile(), _textArea.getText());
					}
				}else{
					saveFile(_currentFile, _textArea.getText());
				}
			}
			
		});
		//saveAs
		saveAs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				int saveResult = _choose.showSaveDialog(null);
				if(saveResult == JFileChooser.APPROVE_OPTION){
					saveFile(_choose.getSelectedFile(), _textArea.getText());
				}
				
			}
			
		});
		//new File
		newFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				_textArea.setText("");
				_currentFile = null;
				setTitle("Text Editor");
			}
		});
		// exit
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				closeWindow();
			}
		});
		//button I put in to test pop up events- will NOT be in final code
		delay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				_a = new ActionListener(){
					public void actionPerformed(ActionEvent e){
						System.out.print("new popup even\n");
						//creepy laugh from http://soundbible.com/2052-Creepy-Laugh.html= public domain
						new SoundClipPlayer("Creepy-Laugh.wav");
						new PopUp("title", "contents", "plzdontleave");
					}
				};
				new EventTimer(2000, _a);
				
			}
		});
		//finally add the menubar
		this.setJMenuBar(menuBar);
		//set up the JTextArea, in which the text will be typed
		_textArea = new JTextArea(15, 40);
		_textArea.setLineWrap(true);
		_textArea.setWrapStyleWord(true);
		Dimension textASize = _textArea.getPreferredSize();
		_textArea.setBounds(0, 0, textASize.width, textASize.height);
		//make a scrolling pane to hold the text area and add it to the JFrame
		JScrollPane scroll = new JScrollPane(_textArea);
		panel.add(scroll, BorderLayout.CENTER);
		//pack and set visible
		this.pack(); //Fit's JFrame to content.
		this.setVisible(true);
	}
	private void openFile(File file){
		if(file.canRead()){
			String filePath = file.getPath();
			String fileContents = "";
			if (filePath.endsWith(".txt")){
				try{
					Scanner scan = new Scanner(new FileInputStream(file));
					while(scan.hasNextLine()){
						fileContents+= scan.nextLine();
					}
					scan.close();
					_textArea.setText(fileContents);
					setTitle("Text Edit " + filePath);
					_currentFile = file;
				} catch(FileNotFoundException e){}
			}else{
				JOptionPane.showMessageDialog(null, "Not a .txt file");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Error Loading File");
		}
	}
	private void saveFile(File file, String content){
		BufferedWriter buff = null;
		String filePath = file.getPath();
		if(!filePath.endsWith(".txt")){
			filePath += ".txt";	
		}
		try{
			buff = new BufferedWriter(new FileWriter(filePath));
			buff.write(content);
			buff.close();
			_textArea.setText(content);
			setTitle("Text Edit " + filePath);
			_currentFile = file;
		}catch(Exception e){}
	}
	private void closeWindow(){
		WindowEvent close = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(close);
	}

}
