package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import domain.Sinhvien;

public class SinhVienFrame extends JFrame {


	private JComboBox<Lop> cbLop;
	private JPanel contentPane;
	private JTextField txtHoTen;
	private JTextField txtDTB;
	private JTable tableSV;
	private DefaultTableModel model = new DefaultTableModel(new String[] {"MSSV","HoTen","DTB","Lop"},0);
	private DefaultComboBoxModel<Lop> modelC = new DefaultComboBoxModel<> ();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SinhVienFrame frame = new SinhVienFrame();
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

	private void loadData() {
		// TODO Auto-generated method stub
		List<Sinhvien> dssv = null;
		List<Lop>dsLop = null;
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();

		try {
			dssv  = session.createQuery("from Sinhvien").list();
			if(dssv!=null) {
				for(Sinhvien sv: dssv) {
					Object[] row = {sv.getMssv(),sv.getHoten(),sv.getDtb(),sv.getLop().getTenlop()};
					model.addRow(row);
				}
			}
			dsLop = session.createQuery("from Lop").list();
			if(dsLop !=null) {
				for(Lop lop: dsLop) {
					modelC.addElement(lop);
				}
			}
		}catch(HibernateException e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}

	public SinhVienFrame() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ho ten:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);

		txtHoTen = new JTextField();
		txtHoTen.setBounds(83, 11, 313, 20);
		contentPane.add(txtHoTen);
		txtHoTen.setColumns(10);

		JLabel lblDtb = new JLabel("DTB:");
		lblDtb.setBounds(10, 39, 46, 14);
		contentPane.add(lblDtb);

		txtDTB = new JTextField();
		txtDTB.setColumns(10);
		txtDTB.setBounds(83, 39, 313, 20);
		contentPane.add(txtDTB);

		JLabel lblNewLabel_2 = new JLabel("Lop:");
		lblNewLabel_2.setBounds(10, 64, 46, 14);
		contentPane.add(lblNewLabel_2);

		cbLop = new JComboBox<>();
		cbLop.setBounds(83, 63, 313, 22);
		cbLop.setModel(modelC);
		contentPane.add(cbLop);

		JButton btnThem = new JButton("Them");
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					them();
			}

			private void them() {
				// TODO Auto-generated method stub
				SessionFactory factory = HibernateUtil.getSessionFactory();
				Session session = factory.openSession();
				Transaction tx = session.beginTransaction();

				Lop lop =(Lop) cbLop.getSelectedItem();
				Sinhvien sv = new Sinhvien(lop,txtHoTen.getText(),Double.valueOf(txtDTB.getText()));
				lop.getSinhviens().add(sv);
				session.save(lop);
				tx.commit();

			 	session.close();
				factory.close();
			}
		});
		btnThem.setBounds(54, 108, 89, 23);
		contentPane.add(btnThem);

		JButton btnXoa = new JButton("Xoa");
		btnXoa.setBounds(172, 108, 89, 23);
		contentPane.add(btnXoa);

		JButton btnSua = new JButton("btnSua");
		btnSua.setBounds(307, 108, 89, 23);
		contentPane.add(btnSua);

		tableSV = new JTable();
		tableSV.setModel(model);
		tableSV.setBounds(10, 156, 414, 94);
		contentPane.add(tableSV);
		loadData();
	}
}
