package Forms;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Classes.DatabaseConnection;
import Classes.User;

public class UpdateUserPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JPasswordField passwordFieldRepeat;
	private JRadioButton rdFemale;
	private JRadioButton rdMale;


	DatabaseConnection db = new DatabaseConnection();

	public void getUsername(String username) {
		txtUsername.setText(username);
		loadUserData(username);
	}

	public void loadUserData(String username) {
		User user = db.getUserInfo(username);

		if (user != null) {
			txtUsername.setText(user.getUsername());
			txtName.setText(user.getName());
			txtSurname.setText(user.getSurname());
			txtEmail.setText(user.getEmail());
			passwordField.setText(user.getPassword());
			passwordFieldRepeat.setText(user.getPassword());

			if (user.getGender().equalsIgnoreCase("male")) {
				rdMale.setSelected(true);
			} else if (user.getGender().equalsIgnoreCase("female")) {
				rdFemale.setSelected(true);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Kullanıcı bilgileri yüklenemedi.");
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateUserPage frame = new UpdateUserPage();
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
	public UpdateUserPage() {
		setTitle("Update Page");
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

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						UserPage user = new UserPage();
						user.labelUsername(txtUsername.getText());
						user.setVisible(true);
						dispose();
					}
				});
			}
		});
		btnBack.setOpaque(true);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnBack.setBorderPainted(false);
		btnBack.setBackground(Color.RED);
		btnBack.setBounds(10, 478, 108, 30);
		panel.add(btnBack);

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

		rdMale = new JRadioButton("Male");
		rdMale.setFont(new Font("Segoe UI", Font.BOLD, 14));
		rdMale.setBounds(568, 222, 103, 21);
		contentPane.add(rdMale);
		rdMale.setActionCommand("Male");

		rdFemale = new JRadioButton("Female");
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
		txtEmail.setEnabled(false);
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
		txtUsername.setEnabled(false);
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

		JButton btnSignUp = new JButton("Update");
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

				if (surname.trim().isEmpty() || gender.trim().isEmpty() || email.trim().isEmpty()
						|| password.trim().isEmpty() || passwordRepeat.trim().isEmpty()) {

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

					db.updateUser(username, name, surname, password, gender);
					JOptionPane.showMessageDialog(contentPane, "User information updated.");
					UserPage user = new UserPage();
					user.labelUsername(username);
					user.setVisible(true);
					dispose();

				} catch (HeadlessException e1) {

					e1.printStackTrace();
				}
			}

		});
		btnSignUp.setOpaque(true);
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSignUp.setBorderPainted(false);
		btnSignUp.setBackground(Color.RED);
		btnSignUp.setBounds(562, 443, 132, 37);
		contentPane.add(btnSignUp);
		btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));

	}
}
