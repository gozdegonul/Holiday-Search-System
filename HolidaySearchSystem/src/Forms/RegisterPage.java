package Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Classes.DatabaseConnection;
import Classes.User;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JPasswordField passwordFieldRepeat;
	private JLabel lblLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
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
	public RegisterPage() {
		setTitle("Register Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 902, 555);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 365, 518);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblreadyToTurn = new JLabel("\"Ready to turn your travel dreams into reality?\"");
		lblreadyToTurn.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblreadyToTurn.setBounds(10, 211, 355, 60);
		panel.add(lblreadyToTurn);

		JLabel lblEveryGreatTrip = new JLabel("Every great trip begins with a dream.");
		lblEveryGreatTrip.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		lblEveryGreatTrip.setBounds(66, 270, 254, 20);
		panel.add(lblEveryGreatTrip);

		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblUsername.setBounds(452, 69, 106, 26);
		contentPane.add(lblUsername);

		JLabel lblName = new JLabel("Name : ");
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblName.setBounds(452, 115, 106, 26);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtName.setColumns(10);
		txtName.setBounds(568, 115, 174, 26);
		contentPane.add(txtName);

		JLabel lblSurname = new JLabel("Surname : ");
		lblSurname.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSurname.setBounds(452, 165, 106, 26);
		contentPane.add(lblSurname);

		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtSurname.setColumns(10);
		txtSurname.setBounds(568, 165, 174, 26);
		contentPane.add(txtSurname);

		JLabel lblGender = new JLabel("Gender : ");
		lblGender.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblGender.setBounds(452, 216, 106, 26);
		contentPane.add(lblGender);

		JRadioButton rdMale = new JRadioButton("Male");
		rdMale.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rdMale.setBounds(568, 222, 103, 21);
		contentPane.add(rdMale);
		rdMale.setActionCommand("Male");

		JRadioButton rdFemale = new JRadioButton("Female");
		rdFemale.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rdFemale.setBounds(715, 222, 103, 21);
		contentPane.add(rdFemale);
		rdFemale.setActionCommand("Female");

		ButtonGroup getGender = new ButtonGroup();
		getGender.add(rdMale);
		getGender.add(rdFemale);

		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblEmail.setBounds(452, 271, 106, 26);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(568, 271, 174, 26);
		contentPane.add(txtEmail);

		JLabel lblPassword_1 = new JLabel("Password : ");
		lblPassword_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPassword_1.setBounds(452, 369, 106, 26);
		contentPane.add(lblPassword_1);

		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPassword.setBounds(452, 319, 106, 26);
		contentPane.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtUsername.setColumns(10);
		txtUsername.setBounds(568, 69, 174, 26);
		contentPane.add(txtUsername);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.BOLD, 14));
		passwordField.setBounds(568, 319, 174, 26);
		contentPane.add(passwordField);

		passwordFieldRepeat = new JPasswordField();
		passwordFieldRepeat.setFont(new Font("Segoe UI", Font.BOLD, 14));
		passwordFieldRepeat.setBounds(568, 369, 174, 26);
		contentPane.add(passwordFieldRepeat);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DatabaseConnection db = new DatabaseConnection();

				String username = txtUsername.getText();
				String name = txtName.getText();
				String surname = txtSurname.getText();
				String gender = getGender.getSelection() != null ? getGender.getSelection().getActionCommand() : "";
				String email = txtEmail.getText();
				String password = new String(passwordField.getPassword());
				String passwordRepeat = new String(passwordFieldRepeat.getPassword());

				if (username.trim().isEmpty() || name.trim().isEmpty() || surname.trim().isEmpty()
						|| gender.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()
						|| passwordRepeat.trim().isEmpty()) {

					JOptionPane.showMessageDialog(contentPane, "Please fill in all fields!");
					return;
				}

				if (!password.equals(passwordRepeat)) {
					JOptionPane.showMessageDialog(contentPane, "Passwords do not match!");
					passwordField.setText("");
					passwordFieldRepeat.setText("");
					return;
				}

				try {
					if (db.isEmailRegistered(email)) {
						JOptionPane.showMessageDialog(contentPane, "This email is already registered.");
						txtEmail.setText("");
						return;
					}

					if (db.isUsernameRegistered(username)) {
						JOptionPane.showMessageDialog(contentPane, "This username is already registered.");
						txtUsername.setText("");
						return;
					}

					User user = new User();
					user.setUsername(username);
					user.setName(name);
					user.setSurname(surname);
					user.setGender(gender);
					user.setEmail(email);
					user.setPassword(password);

					db.saveUser(user);
					JOptionPane.showMessageDialog(contentPane, "User saved successfully.");
					LoginPage login = new LoginPage();
					login.setVisible(true);
					dispose();

				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		btnSignUp.setOpaque(true);
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSignUp.setBorderPainted(false);
		btnSignUp.setBackground(Color.RED);
		btnSignUp.setBounds(520, 446, 132, 37);
		contentPane.add(btnSignUp);
		btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));

		lblLogin = new JLabel("<html><a href=''>Login</a></html>");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage login = new LoginPage();
				login.setVisible(true);
				dispose();
			}
		});
		lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblLogin.setBounds(688, 454, 53, 20);
		contentPane.add(lblLogin);
		lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

	}
}
