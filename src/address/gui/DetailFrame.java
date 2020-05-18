package address.gui;

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
import javax.swing.JTextField;

import address.model.GroupType;
import address.model.Member;
import address.service.MemberService;

public class DetailFrame extends JFrame {

	private DetailFrame detailFrame = this;
	private MainFrame mainFrame;
	private int memberId; // mainFrame
	private Container backgroundPanel;
	private JLabel laName, laPhone, laAddress, laGroup;
	private JTextField tfName, tfPhone, tfAddress;
	private JComboBox<GroupType> cbGroup;
	private JButton updateButton, deleteButton;
	private MemberService memberService = MemberService.getInstance();

	public DetailFrame(MainFrame mainFrame, int memberId) {
		this.mainFrame = mainFrame;
		this.memberId = memberId;
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
	}

	private void initObject() {
		backgroundPanel = getContentPane();
		laName = new JLabel("�̸�");
		laPhone = new JLabel("��ȭ��ȣ");
		laAddress = new JLabel("�ּ�");
		laGroup = new JLabel("�׷�");
		tfName = new JTextField(20);
		tfPhone = new JTextField(20);
		tfAddress = new JTextField(20);
		cbGroup = new JComboBox<>(GroupType.values());
		updateButton = new JButton("�����ϱ�");
		deleteButton = new JButton("�����ϱ�");

	}

	private void initData() {
		// DetailFrame-> MemberService ->MemberDao�� �󼼺���() -> DB
		Member member = memberService.�󼼺���(memberId);
		tfName.setText(member.getName());// setText()�� repaint�� ��� ����
		tfPhone.setText(member.getPhone());
		tfAddress.setText(member.getAddress());
		cbGroup.setSelectedItem(member.getGroupType());
	}

	private void initDesign() {
		setTitle("�ּҷ� �󼼺���");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		backgroundPanel.setLayout(new GridLayout(5, 2));

		backgroundPanel.add(laName);
		backgroundPanel.add(tfName);
		backgroundPanel.add(laPhone);
		backgroundPanel.add(tfPhone);
		backgroundPanel.add(laAddress);
		backgroundPanel.add(tfAddress);
		backgroundPanel.add(laGroup);
		backgroundPanel.add(cbGroup);
		backgroundPanel.add(updateButton);
		backgroundPanel.add(deleteButton);
	}

	private void initListener() {
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� ���� - ����
				Member m = Member.builder()
						.id(memberId)
						.name(tfName.getText())
						.phone(tfPhone.getText())
						.address(tfAddress.getText())
						.groupType(GroupType.valueOf(cbGroup.getSelectedItem().toString()))
						.build();

				int result = memberService.�����ϱ�(m);
				// memberService.�����ϱ�()
				if (result == 1) {
					mainFrame.notifyUserList();
					detailFrame.dispose();
					mainFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "�ּҷ� ������ �����Ͽ����ϴ�.");
				}

			}
		});

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� ���� - ����

				int result = memberService.�����ϱ�(memberId);

				// result == 1 �̸� �Ʒ� ���� ����, 1�� �ƴϸ� ���̾�α� �ڽ� (���� ����)
				if (result == 1) {
					mainFrame.notifyUserList();
					detailFrame.dispose();
					mainFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "�ּҷ� ������ �����Ͽ����ϴ�.");
				}
			}

		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				mainFrame.setVisible(true);

			}

		});

	}
}
