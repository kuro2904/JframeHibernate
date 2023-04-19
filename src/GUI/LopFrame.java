package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Util.HibernateUtil;
import domain.Lop;

public class LopFrame extends JFrame {
	private DefaultTableModel model = new DefaultTableModel (new String[] {"Ma Lop","Ten Lop"},0);
	private JPanel contentPane;
	private JTextField txtMalop;
	private JTextField txtTenlop;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LopFrame frame = new LopFrame();
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
	public LopFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ma lop");
		lblNewLabel.setBounds(34, 14, 46, 14);
		contentPane.add(lblNewLabel);

		txtMalop = new JTextField();
		txtMalop.setBounds(120, 8, 264, 20);
		contentPane.add(txtMalop);
		txtMalop.setColumns(10);

		JLabel lblTenLop = new JLabel("Ten lop");
		lblTenLop.setBounds(34, 42, 46, 14);
		contentPane.add(lblTenLop);

		txtTenlop = new JTextField();
		txtTenlop.setColumns(10);
		txtTenlop.setBounds(120, 36, 264, 20);
		contentPane.add(txtTenlop);

		JButton btnThemLop = new JButton("Them");
		btnThemLop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SessionFactory factory = HibernateUtil.getSessionFactory();
				Session ses = factory.openSession();
				try {
					Transaction tran = ses.beginTransaction();
					Lop lop = new Lop();
					lop.setMalop(Integer.valueOf(txtMalop.getText()));
					lop.setTenlop(txtTenlop.getText());
					ses.save(lop);
					tran.commit();
				}catch(HibernateException e1) {
					e1.printStackTrace();
				}finally {
					ses.close();
					factory.close();
				}
			}
		});
		btnThemLop.setBounds(44, 84, 89, 23);
		contentPane.add(btnThemLop);

		JButton btnXoa = new JButton("Xoa");
		btnXoa.setBounds(160, 84, 89, 23);
		contentPane.add(btnXoa);

		JButton btnSua = new JButton("Sua");
		btnSua.setBounds(295, 84, 89, 23);
		contentPane.add(btnSua);

		table = new JTable();
		table.setModel(model);
		table.setBounds(10, 118, 414, 132);
		contentPane.add(table);
		loadData();
	}

	private void loadData() {
		// TODO Auto-generated method stub
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session ses = factory.openSession();
		List<Lop>dsLop = ses.createQuery("from Lop").list();
		if(dsLop != null) {
			for(Lop lop : dsLop) {
				Object[] row = {lop.getMalop(),lop.getTenlop()};
				model.addRow(row);
			}
		}
		ses.close();
		factory.close();
	}

}
