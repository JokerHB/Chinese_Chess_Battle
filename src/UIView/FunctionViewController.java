package UIView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

public class FunctionViewController implements ActionListener {
	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 50;
	private static final int VIEW_WIDTH = 700;
	private static final int VIEW_HEIGHT = 712;
	
	public static int gameModel = -1;
	public static int gameFirst = -1;
	
	private JFrame frame;
	private JLayeredPane functionPane;

	private JButton singlePlayer_Btn;
	private JButton doublePlayer_Btn;
	private JButton watchPlayer_Btn;
	private JButton offensive_Btn;
	private JButton defensive_Btn;
	
	public void init() {
		this.frame = new JFrame("Chinese Chess Battle @HongBo.Jing, Jing.Chen, WenShan.Li");
		this.frame.setIconImage(new ImageIcon("res/img/icon.icon.png").getImage());
		this.frame.setSize(this.VIEW_WIDTH, this.VIEW_HEIGHT);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.functionPane = new JLayeredPane();
		this.frame.add(this.functionPane);

		ImageIcon background = new ImageIcon("res/img/board.png");
		JLabel backgroundLabel = new JLabel(background);
		backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		this.functionPane.add(backgroundLabel, 1);

		ImageIcon title = new ImageIcon("res/img/tt.png");
		JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds(35, 65, title.getIconWidth(), title.getIconHeight());
		this.functionPane.add(titleLabel, 0);

		this.singlePlayer_Btn = new JButton("Single Play");
		this.singlePlayer_Btn.setLocation(100, 320);
		this.singlePlayer_Btn.setSize(this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
		this.singlePlayer_Btn.setActionCommand("single");
		this.singlePlayer_Btn.addActionListener(this);

		this.doublePlayer_Btn = new JButton("Double Play");
		this.doublePlayer_Btn.setLocation(280, 320);
		this.doublePlayer_Btn.setSize(this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
		this.doublePlayer_Btn.setActionCommand("double");
		this.doublePlayer_Btn.addActionListener(this);

		this.watchPlayer_Btn = new JButton("Watch Model");
		this.watchPlayer_Btn.setLocation(460, 320);
		this.watchPlayer_Btn.setSize(this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
		this.watchPlayer_Btn.setActionCommand("watch");
		this.watchPlayer_Btn.addActionListener(this);

		this.offensive_Btn = new JButton("Offensive");
		this.offensive_Btn.setLocation(200, 320);
		this.offensive_Btn.setSize(this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
		this.offensive_Btn.setActionCommand("offensive");
		this.offensive_Btn.addActionListener(this);
		
		this.defensive_Btn = new JButton("Defensive");
		this.defensive_Btn.setLocation(370, 320);
		this.defensive_Btn.setSize(this.BUTTON_WIDTH, this.BUTTON_HEIGHT);
		this.defensive_Btn.setActionCommand("defensive");
		this.defensive_Btn.addActionListener(this);

		this.functionPane.add(singlePlayer_Btn, 0);
		this.functionPane.add(doublePlayer_Btn, 0);
		this.functionPane.add(watchPlayer_Btn, 0);
		
		this.frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int hashCode = e.getActionCommand().hashCode();
		
		if (hashCode == "single".hashCode()) {
			this.gameModel = 1;
			
			this.doublePlayer_Btn.setVisible(false);
			this.watchPlayer_Btn.setVisible(false);
			this.singlePlayer_Btn.setVisible(false);
			
			this.functionPane.add(offensive_Btn, 0);
			this.functionPane.add(defensive_Btn, 0);
			
		} else if (hashCode == "double".hashCode()) {
			this.gameModel = 2;
			this.frame.dispose();
		} else if (hashCode == "watch".hashCode()) {
			this.gameModel = 3;
			System.out.println(3);
			this.frame.dispose();
		} else if (hashCode == "offensive".hashCode()) {
			this.gameFirst = 1;
			this.frame.dispose();
		} else {
			this.gameFirst = 2;
			this.frame.dispose();
		}
	}
}
