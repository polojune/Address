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
		laName = new JLabel("이름");
		laPhone = new JLabel("전화번호");
		laAddress = new JLabel("주소");
		laGroup = new JLabel("그룹");
		tfName = new JTextField(20);
		tfPhone = new JTextField(20);
		tfAddress = new JTextField(20);
		cbGroup = new JComboBox<>(GroupType.values());
		updateButton = new JButton("수정하기");
		deleteButton = new JButton("삭제하기");

	}

	private void initData() {
		// DetailFrame-> MemberService ->MemberDao의 상세보기() -> DB
		Member member = memberService.상세보기(memberId);
		tfName.setText(member.getName());// setText()는 repaint를 들고 있음
		tfPhone.setText(member.getPhone());
		tfAddress.setText(member.getAddress());
		cbGroup.setSelectedItem(member.getGroupType());
	}

	private void initDesign() {
		setTitle("주소록 상세보기");
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
				// 서비스 연결 - 수정
				Member m = Member.builder()
						.id(memberId)
						.name(tfName.getText())
						.phone(tfPhone.getText())
						.address(tfAddress.getText())
						.groupType(GroupType.valueOf(cbGroup.getSelectedItem().toString()))
						.build();

				int result = memberService.수정하기(m);
				// memberService.수정하기()
				if (result == 1) {
					mainFrame.notifyUserList();
					detailFrame.dispose();
					mainFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "주소록 수정에 실패하였습니다.");
				}

			}
		});

		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 서비스 연결 - 삭제

				int result = memberService.삭제하기(memberId);

				// result == 1 이면 아래 로직 실행, 1이 아니면 다이어로그 박스 (삭제 실패)
				if (result == 1) {
					mainFrame.notifyUserList();
					detailFrame.dispose();
					mainFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "주소록 삭제에 실패하였습니다.");
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
