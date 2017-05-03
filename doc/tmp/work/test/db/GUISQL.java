package work.test.db;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUISQL extends JFrame {

	private static final long serialVersionUID = 1L;

	private String query, server, username, password;
	private JPanel jpLogin;
	private JTextArea jtaText, jtaConsole;
	private JButton jbSubmit;
	private JTextField jtfServer, jtfUsername, jtfPassword;
	private JLabel jlServer, jlUsername, jlPassword;
	private JFrame jfWindowSignIn, jfSQL;
	private CRUDexample inUsername;
	
	public GUISQL() {
		
	}
	public GUISQL(boolean whichWindow) {
		if (!whichWindow) {
			login();
		}else {
			disposeWindow();
			textArea();
		}
	}

	public void textArea() {	
		jfSQL = new JFrame();
		jtaText = new JTextArea();
		
		jtaConsole = new JTextArea();
		jtaConsole.setEditable(false);
		
		jfSQL.setSize(800, 400);
		jfSQL.setLocationRelativeTo(null);
		jfSQL.setVisible(true);
		jfSQL.setTitle("SQL QUERY");
		jfSQL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfSQL.setResizable(false);

		jtaText.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F5) {
					setQuery(jtaText.getText());
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					jfSQL.dispose();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		jfSQL.add(jtaConsole, BorderLayout.SOUTH);
		jfSQL.add(jtaText, BorderLayout.CENTER);
		
	}

	public void login() {
		jfWindowSignIn = new JFrame();
		jbSubmit = new JButton("Sign in");
		
		jtfServer = new JTextField(15);
		jlServer = new JLabel("Server:");

		jtfUsername = new JTextField(15);
		jlUsername = new JLabel("Username:");

		jtfPassword = new JPasswordField(15);
		jlPassword = new JLabel("Password:");

		jpLogin = new JPanel(new GridLayout(0, 2));

		jpLogin.add(jlServer);
		jpLogin.add(jtfServer);

		jpLogin.add(jlUsername);
		jpLogin.add(jtfUsername);

		jpLogin.add(jlPassword);
		jpLogin.add(jtfPassword);

		jfWindowSignIn.setSize(400, 110);
		jfWindowSignIn.setLocationRelativeTo(null);
		jfWindowSignIn.setVisible(true);
		jfWindowSignIn.setTitle("Login Form");
		jfWindowSignIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfWindowSignIn.setResizable(false);
		
		jbSubmit = new JButton("Sign in");
		jbSubmit.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "pressed");
		jbSubmit.getInputMap().put(KeyStroke.getKeyStroke("released ENTER"), "released");
		jbSubmit.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				setUsername(jtfUsername.getText());
				setPassword(jtfPassword.getText());
				inUsername = new CRUDexample(jtfUsername.getText(), jtfPassword.getText());
			}
		});
		jfWindowSignIn.add(jbSubmit, BorderLayout.SOUTH);
		jfWindowSignIn.add(jpLogin, BorderLayout.CENTER);
	}

	public void disposeWindow() {
		jfWindowSignIn.dispose();
	}
	// QUERY GET/SET
	public void setQuery(String in) {
		query = in;
	}

	public String getQuery() {
		return query;
	}

	// SERVER GET/SET
	public void setServer(String in) {
		server = in;
	}

	public String getServer() {
		return server;
	}

	// USERNAME GET/SET
	public void setUsername(String in) {
		username = in;
	}

	public String getUsername() {
		return username;
	}

	// PASSWORD GET/SET
	public void setPassword(String in) {
		password = in;
	}

	public String getPassword() {
		return password;
	}
//	 public static void main(String[]args) {
//	        SwingUtilities.invokeLater(new Runnable() {
//	            public void run() {
//	                new GUISQL();
//	            }
//	        });
//	 }
}
