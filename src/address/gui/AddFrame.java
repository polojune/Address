package address.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import address.model.GroupType;
import address.model.Member;
import address.service.MemberService;

public class AddFrame extends JFrame {
    
	private final static String TAG = "AddFrame:";
	
	private AddFrame addFrame = this;
	private MainFrame mainFrame;
	private Container backgroundPanel;
	private JPanel addPanel;
	private JLabel laName, laPhone, laAddress, laGroup;
	private JTextField tfName, tfPhone, tfAddress;
	private JComboBox<GroupType> cbGroup;
	private JButton addButton;
	private MemberService memberService = MemberService.getInstance();

	public AddFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		initObject();
		initDesign();
		initListener();
		setVisible(true);
	}

	private void initObject() {
		backgroundPanel = getContentPane();
		addPanel = new JPanel();
		laName = new JLabel("�̸�");
		laPhone = new JLabel("��ȭ��ȣ");
		laAddress = new JLabel("�ּ�");
		laGroup = new JLabel("�׷�");
		tfName = new JTextField(20);
		tfPhone = new JTextField(20);
		tfAddress = new JTextField(20);
		cbGroup = new JComboBox<>(GroupType.values());
		addButton = new JButton("�߰��ϱ�");

	}

	private void initDesign() {
		setTitle("�ּҷ� �߰��ϱ�");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		backgroundPanel.setLayout(new BorderLayout());
		addPanel.setLayout(new GridLayout(4, 2));

		addPanel.add(laName);
		addPanel.add(tfName);
		addPanel.add(laPhone);
		addPanel.add(tfPhone);
		addPanel.add(laAddress);
		addPanel.add(tfAddress);
		addPanel.add(laGroup);
		addPanel.add(cbGroup);

		backgroundPanel.add(addPanel, BorderLayout.CENTER);
		backgroundPanel.add(addButton, BorderLayout.SOUTH);
	}

	private void initListener() {
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 1.�ؽ�Ʈ �ʵ忡 �ִ� ���� ������ 2.���� Member�� ����
			 System.out.println(TAG+"addButton������:"+tfName.getText());
				Member member = Member.builder()
						.name(tfName.getText())
						.phone(tfPhone.getText())
						.address(tfAddress.getText()).groupType(GroupType.valueOf(cbGroup.getSelectedItem().toString()))
						.build();

				int result = memberService.�ּҷ��߰�(member);
				// 4.return���� Ȯ���ؼ� ������ ���� ¥����.(����,����)
				if (result == 1) {
					// 5.���� = mainFrame�� ���� ����(����ȭ)
					mainFrame.notifyUserList();
					addFrame.dispose();
					mainFrame.setVisible(true);
				} else {
                    JOptionPane.showMessageDialog(null, "�ּҷ� �߰��� �����Ͽ����ϴ�.");
				}

			}
		});
		addFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				mainFrame.setVisible(true);

			}
		});
	}
}
