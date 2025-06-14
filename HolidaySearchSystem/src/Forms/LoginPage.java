package Forms;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.DatabaseConnection;
import Classes.User;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 465);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 349, 428);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblContent = new JLabel("\"Your dream vacation is just a plan away.\"");
		lblContent.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblContent.setBounds(20, 138, 308, 60);
		panel.add(lblContent);

		JLabel lblContentDown = new JLabel("Customize. Explore. Remember forever.");
		lblContentDown.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblContentDown.setBounds(47, 209, 254, 13);
		panel.add(lblContentDown);

		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblUsername.setBounds(429, 155, 106, 26);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPassword.setBounds(429, 216, 106, 26);
		contentPane.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtUsername.setBounds(545, 155, 174, 26);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.BOLD, 14));
		passwordField.setBounds(545, 216, 174, 26);
		contentPane.add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DatabaseConnection db = new DatabaseConnection();

				String username = txtUsername.getText();
				String password = new String(passwordField.getPassword());

				if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {

					JOptionPane.showMessageDialog(contentPane, "Fill the blanks!");
					return;
				}

				try {
					if (db.Login(username, password)) {

						User getUsername = db.getUserInfo(username);

						JOptionPane.showMessageDialog(contentPane, "Login is successfull.");
						UserPage user = new UserPage();
						user.labelUsername(getUsername);
						user.setVisible(true);
						dispose();

					} else {
						JOptionPane.showMessageDialog(contentPane, "Incorrect username or password.");
						txtUsername.setText("");
						passwordField.setText("");
					}
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnLogin.setBounds(498, 296, 132, 37);
		btnLogin.setBackground(Color.RED);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setOpaque(true);
		btnLogin.setBorderPainted(false);
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnLogin);

		JLabel lblSignUp = new JLabel("<html><a href=''>Sign Up</a></html>");
		lblSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterPage rp = new RegisterPage();
				rp.setVisible(true);
				dispose();
			}
		});
		contentPane.add(lblSignUp);
		lblSignUp.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblSignUp.setBounds(666, 304, 53, 20);
		lblSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));

	}
}
