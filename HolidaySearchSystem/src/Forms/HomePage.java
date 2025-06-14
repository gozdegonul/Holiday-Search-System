package Forms;

import java.awt.AlphaComposite;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.UIManager;

public class HomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setTitle("Home Page");
		setFont(new Font("Segoe UI", Font.BOLD, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 1029, 48);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage login = new LoginPage();
				login.setVisible(true);
				dispose();

			}
		});
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblLogin.setBounds(949, 0, 70, 38);
		panel.add(lblLogin);
		lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

		JLabel lblCompanyName = new JLabel("Voyage Vista");
		lblCompanyName.setBackground(Color.GRAY);
		lblCompanyName.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblCompanyName.setBounds(10, -1, 158, 38);
		panel.add(lblCompanyName);

		JPanel pnlBeach = new JPanel();
		pnlBeach.setBounds(0, 40, 1029, 511);
		contentPane.add(pnlBeach);

		ImageIcon picture = new ImageIcon(getClass().getResource("/images/beachPicture.png"));
		Image img = picture.getImage();
		Image scaledImg = img.getScaledInstance(1029, 511, Image.SCALE_SMOOTH);

		ImageIcon scaledIcon = new ImageIcon(scaledImg);
		pnlBeach.setLayout(null);

		JLabel lblBeachImage = new JLabel(scaledIcon);
		lblBeachImage.setBounds(0, 5, 1029, 511);
		pnlBeach.add(lblBeachImage);

		JLabel lblContent = new JLabel("\"Need a break from the everyday — but don’t know where to start?\"\n");
		lblContent.setForeground(Color.WHITE);
		lblContent.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblContent.setBounds(190, 200, 700, 25);
		lblBeachImage.add(lblContent);
		pnlBeach.add(lblBeachImage);

		JLabel lblContentDown = new JLabel("Explore tailored getaways based on your travel style, mood, and dreams.");
		lblContentDown.setForeground(SystemColor.control);
		lblContentDown.setFont(new Font("Segoe UI", Font.ITALIC, 18));
		lblContentDown.setBounds(190, 230, 700, 25);
		lblBeachImage.add(lblContentDown);
		pnlBeach.add(lblBeachImage);
		pnlBeach.add(lblBeachImage);

	}
}
