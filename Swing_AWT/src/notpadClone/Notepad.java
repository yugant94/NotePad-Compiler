package notpadClone;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Notepad {

	JFrame frame;
	JTextArea textArea;
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenu langMenu;
	JMenu formatMenu;
	JMenu cmdMenu;

//	file menu items
	JMenuItem itemNew;
	JMenuItem itemNewWnindow;
	JMenuItem itemOpen;
	JMenuItem itemSave;
	JMenuItem itemSaveAs;
	JMenuItem itemExit;

//	format menu items
	JMenuItem itemWordWrap;
	JMenu itemFont;
	JMenu itemFontSize;

//	cmd prompt  item
	JMenuItem itemOpnCmdPrompt;

	String openFileName = null;
	String openPath = null;

//	wordwrap
	boolean wrap = false;

	Font arial;
	Font timesNewRoman;
	Font consolas;

	String fontStyle = "Consolas";
	int currentFontSize = 18;

	public Notepad() {
		createFrame();
		createTextArea();
		createScrollBar();
		createMenuBar();
		createFileMenuItems();
		createEditLanguageItems();
		createFormatMenuItems();
		createCmdPromptItems();
	}

	private void createEditLanguageItems() {
		// TODO Auto-generated method stub
		JMenuItem itemJava = new JMenuItem("Java");
		itemJava.setFont(itemJava.getFont().deriveFont(16.0f));
		langMenu.add(itemJava);
		itemJava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setLanguage("JAVA");
			}
		});

		JMenuItem itemHtml = new JMenuItem("HTML");
		itemHtml.setFont(itemHtml.getFont().deriveFont(16.0f));
		langMenu.add(itemHtml);
		itemHtml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setLanguage("HTML");
			}
		});

		JMenuItem itemC = new JMenuItem("C");
		itemC.setFont(itemC.getFont().deriveFont(16.0f));
		langMenu.add(itemC);
		itemC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setLanguage("C");
			}
		});

		JMenuItem itemCpp = new JMenuItem("C++");
		itemCpp.setFont(itemCpp.getFont().deriveFont(16.0f));
		langMenu.add(itemCpp);
		itemCpp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setLanguage("CPP");
				
			}
		});

	}

	public void createCmdPromptItems() {

		itemOpnCmdPrompt = new JMenuItem("Open Command Prompt");
		itemOpnCmdPrompt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (openPath != null) {
						Runtime.getRuntime().exec(new String[] { "cmd", "/K", "start" }, null, new File(openPath));
					} else {

						Runtime.getRuntime().exec(new String[] { "cmd", "/K", "start" }, null, null);
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "Unable to open Command Prompt!!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		itemOpnCmdPrompt.setFont(itemOpnCmdPrompt.getFont().deriveFont(16.0f));
		cmdMenu.add(itemOpnCmdPrompt);

	}

	public void createFormatMenuItems() {
		// TODO Auto-generated method stub

		itemWordWrap = new JMenuItem("Word wrap - OFF");
		itemWordWrap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (wrap == false) {
					textArea.setLineWrap(true);
					textArea.setWrapStyleWord(true);
					wrap = true;
					itemWordWrap.setText("Word wrap - ON");
				} else {
					textArea.setLineWrap(false);
					textArea.setWrapStyleWord(false);
					wrap = false;
					itemWordWrap.setText("Word wrap - OFF");
				}
			}
		});
		itemWordWrap.setFont(itemWordWrap.getFont().deriveFont(16.0f));
		formatMenu.add(itemWordWrap);

		itemFont = new JMenu("Font");
		itemFont.setFont(itemFont.getFont().deriveFont(16.0f));
		formatMenu.add(itemFont);

		JMenuItem itemArial = new JMenuItem("Arial");
		itemArial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontType("Arial");
			}
		});
		itemArial.setFont(itemArial.getFont().deriveFont(16.0f));
		itemFont.add(itemArial);

		JMenuItem itemTimesNewRoman = new JMenuItem("Times New Roman");
		itemTimesNewRoman.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontType("Times New Roman");

			}
		});
		itemTimesNewRoman.setFont(itemTimesNewRoman.getFont().deriveFont(16.0f));
		itemFont.add(itemTimesNewRoman);

		JMenuItem itemConsolas = new JMenuItem("Consolas");
		itemConsolas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontType("Consolas");
			}
		});
		itemConsolas.setFont(itemConsolas.getFont().deriveFont(16.0f));
		itemFont.add(itemConsolas);

		itemFontSize = new JMenu("Font size");
		itemFontSize.setFont(itemFontSize.getFont().deriveFont(16.0f));
		formatMenu.add(itemFontSize);

		JMenuItem size10 = new JMenuItem("10");
		size10.setFont(size10.getFont().deriveFont(16.0f));
		itemFontSize.add(size10);
		size10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(10);
			}
		});

		JMenuItem size14 = new JMenuItem("14");
		size14.setFont(size14.getFont().deriveFont(16.0f));
		itemFontSize.add(size14);
		size14.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(14);
			}
		});

		JMenuItem size18 = new JMenuItem("18");
		size18.setFont(size18.getFont().deriveFont(16.0f));
		itemFontSize.add(size18);
		size18.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(18);
			}
		});

		JMenuItem size22 = new JMenuItem("22");
		size22.setFont(size22.getFont().deriveFont(16.0f));
		itemFontSize.add(size22);
		size22.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(22);
			}
		});

		JMenuItem size26 = new JMenuItem("26");
		size26.setFont(size26.getFont().deriveFont(16.0f));
		itemFontSize.add(size26);

		size26.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFontSize(26);
			}
		});

		JMenuItem size30 = new JMenuItem("30");
		size30.setFont(size30.getFont().deriveFont(16.0f));
		itemFontSize.add(size30);
		size30.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setFontSize(30);

			}
		});


		JMenuItem size34 = new JMenuItem("34");
		size34.setFont(size34.getFont().deriveFont(16.0f));
		size34.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setFontSize(34);

			}
		});
		itemFontSize.add(size34);

	}

	public void setFontSize(int size) {
	    currentFontSize = size;
	    setFontType(fontStyle); 
	}

	public void setFontType(String font) {
	    fontStyle = font;
	    textArea.setFont(new Font(fontStyle, Font.PLAIN, currentFontSize));
	}


	public void setLanguage(String lang) {
		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader("D:\\Documents\\filehandle\\" + lang + "Format.txt"));
			String sentence = br.readLine();
			textArea.setText("");
			frame.setTitle("Untitled");
			openFileName = null;
			openPath = null;

			while (sentence != null) {
				textArea.append(sentence + "\n");
				sentence = br.readLine();
			}
		} catch (FileNotFoundException e1) {
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(frame, "Unable to read data!!", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e1) {
		} finally {
			try {
				br.close();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame, "Unable to close file!!", "ERROR", JOptionPane.ERROR_MESSAGE);
			} catch (NullPointerException e1) {
			}
		}
	}

	void writeDataToFile(String filename, String path) {
		// TODO Auto-generated method stub
		String newPath = path + filename;
		BufferedWriter bw = null;

		if (filename != null && path != null) {
			try {
				bw = new BufferedWriter(new FileWriter(newPath));
				String text = textArea.getText();
				bw.write(text);

			} catch (IOException e1) {
				JOptionPane.showMessageDialog(frame, "Unable to write data!!", "ERROR", JOptionPane.ERROR_MESSAGE);
			} finally {
				try {
					bw.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "Unable to close file!!", "ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e1) {
//					
				}
			}
		}
	}

	public void createFileMenuItems() {
		// TODO Auto-generated method stub

		itemNew = new JMenuItem("New");
		itemNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textArea.setText("");
				frame.setTitle("Untitled");
				openFileName = null;
				openPath = null;
			}
		});

		itemNew.setFont(itemNew.getFont().deriveFont(16.0f));
		fileMenu.add(itemNew);

		itemNewWnindow = new JMenuItem("New Window");
		itemNewWnindow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Notepad n1 = new Notepad();
				n1.frame.setTitle("untitled");
			}
		});
		itemNewWnindow.setFont(itemNewWnindow.getFont().deriveFont(16.0f));
		fileMenu.add(itemNewWnindow);

		itemOpen = new JMenuItem("Open");
		itemOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog fd = new FileDialog(frame, "Open");
				fd.setVisible(true);
				String path = fd.getDirectory();
				String filename = fd.getFile();

				String newPath = path + filename;
				if (filename != null) {
					frame.setTitle(filename);
					openFileName = filename;
					openPath = path;
				}

				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(newPath));
					String sentence = br.readLine();
					textArea.setText("");

					while (sentence != null) {
						textArea.append(sentence + "\n");
						sentence = br.readLine();
					}
				} catch (FileNotFoundException e1) {
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(frame, "Unable to read data!!", "ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e1) {
				} finally {
					try {
						br.close();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(frame, "Unable to close file!!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					} catch (NullPointerException e1) {
					}
				}

			}
		});
		itemOpen.setFont(itemOpen.getFont().deriveFont(16.0f));
		fileMenu.add(itemOpen);

		itemSave = new JMenuItem("Save");
		itemSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (openPath != null && openFileName != null) {

					writeDataToFile(openFileName, openPath);
				} else {
					FileDialog fd = new FileDialog(frame, "Save as", FileDialog.SAVE);
					fd.setVisible(true);
					String path = fd.getDirectory();
					String filename = fd.getFile();

					if (path != null && filename != null) {
						writeDataToFile(filename, path);
						openFileName = filename;
						openPath = path;
						frame.setTitle(openFileName);
					}
				}
			}
		});
		itemSave.setFont(itemSave.getFont().deriveFont(16.0f));
		fileMenu.add(itemSave);

		itemSaveAs = new JMenuItem("Save as..");
		itemSaveAs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				FileDialog fd = new FileDialog(frame, "Save as", FileDialog.SAVE);
				fd.setVisible(true);

				String path = fd.getDirectory();
				String filename = fd.getFile();

				openPath = path;
				openFileName = filename;

				if (path != null && filename != null) {
					writeDataToFile(filename, path);
					openFileName = filename;
					openPath = path;
					frame.setTitle(openFileName);

				}
			}
		});
		itemSaveAs.setFont(itemSaveAs.getFont().deriveFont(16.0f));
		fileMenu.add(itemSaveAs);

		itemExit = new JMenuItem("Exit");
		itemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		itemExit.setFont(itemExit.getFont().deriveFont(16.0f));
		fileMenu.add(itemExit);
	}

	private void createMenuBar() {

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		fileMenu.setFont(fileMenu.getFont().deriveFont(16.0f));
		menuBar.add(fileMenu);

		langMenu = new JMenu("Language");
		langMenu.setFont(langMenu.getFont().deriveFont(16.0f));
		menuBar.add(langMenu);

		formatMenu = new JMenu("Format");
		formatMenu.setFont(formatMenu.getFont().deriveFont(16.0f));
		menuBar.add(formatMenu);

		cmdMenu = new JMenu("Command Prompt");
		cmdMenu.setFont(cmdMenu.getFont().deriveFont(16.0f));
		menuBar.add(cmdMenu);

	}

	private void createScrollBar() {

		JScrollPane scrollBar = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(scrollBar);
	}

	public void createFrame() {
		frame = new JFrame("Notepad");
		frame.setBounds(600, 200, 400, 350);
		Image icon =Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\Desktop\\notepad_logo.png");
		frame.setIconImage(icon);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void createTextArea() {
		textArea = new JTextArea();
		textArea.setFont(new Font("Consolas", Font.PLAIN, 18));
		frame.add(textArea);
		textArea.setVisible(true);
	}
}
