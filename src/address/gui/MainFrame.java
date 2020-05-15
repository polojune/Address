package address.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import address.model.GroupType;
import address.model.Member;
import address.service.MemberService;
import address.utils.MyStringParser;

public class MainFrame extends JFrame {
	private MemberService memberService = MemberService.getInstance();

	private MainFrame mainFrame = this;
	private Container backGroundPanal;
	private JPanel topPanel, menuPanel, listPanel;
	private JButton homeButton, frButton, coButton, scButton, faButton, addButton;
	private JList<Member> userList;
	private DefaultListModel<Member> listModel;
	private JScrollPane jspane;

	public MainFrame() {
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
		
	}

	// ��ü����
	private void initObject() {
		backGroundPanal = getContentPane();
		topPanel = new JPanel();
		menuPanel = new JPanel();
		listPanel = new JPanel();

		homeButton = new JButton("�ּҷ� ��ü");
		frButton = new JButton("ģ��");
		coButton = new JButton("ȸ��");
		scButton = new JButton("�б�");
		faButton = new JButton("����");
		addButton = new JButton("�߰�");

		listModel = new DefaultListModel<>();
		userList = new JList<>(listModel);

		jspane = new JScrollPane(userList);

	}

	// �������ʱ�ȭ
	private void initData() {
		List<Member> members = memberService.��ü���();
		for (Member member : members) {
			listModel.addElement(member);
		}

	}

	// ������
	private void initDesign() {
		// 1.�⺻����
		setTitle("�ּҷ� ����");
		setSize(400, 500);
		setLocationRelativeTo(null);// JFrame �� ����� �߾ӿ� ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 2.�гμ���
		backGroundPanal.setLayout(new BorderLayout());
		topPanel.setLayout(new GridLayout(2, 1));// �� 2
		menuPanel.setLayout(new GridLayout(1, 4));// �� 4
		listPanel.setLayout(new BorderLayout());

		// 3.������
		userList.setFixedCellHeight(50); // ����Ʈ ������ ����
		topPanel.setPreferredSize(new Dimension(0, 100));

		// 4.�г�(�����̳�)�� ������Ʈ �߰�
		menuPanel.add(frButton);
		menuPanel.add(coButton);
		menuPanel.add(scButton);
		menuPanel.add(faButton);
		topPanel.add(homeButton);
		topPanel.add(menuPanel);
		listPanel.add(jspane);
		backGroundPanal.add(topPanel, BorderLayout.NORTH);
		backGroundPanal.add(listPanel, BorderLayout.CENTER);
		backGroundPanal.add(addButton, BorderLayout.SOUTH);
	}

	// ������ ���
	private void initListener() {
		userList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println(userList.getSelectedIndex());
				// System.out.println(userList.getSelectedValue());
				int memberId = MyStringParser.getid(userList.getSelectedValue().toString());
				new DetailFrame(mainFrame, memberId);
			}
		});

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddFrame(mainFrame);
				mainFrame.setVisible(false);

			}
		});
	}

	public void notifyUserList() {
		// 1.listModel ����!!
		listModel.clear();
		// 2.select�ؼ� ��ü��� ��������!! List<Member>�� ���
		// 3.listModel ä���ֱ�!!(usetList �ڵ� ����)
		initData();
	}
}