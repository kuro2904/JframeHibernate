package domain;
// Generated Apr 19, 2023, 11:51:42 AM by Hibernate Tools 4.3.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Sinhvien generated by hbm2java
 */
@Entity
@Table(name = "sinhvien", catalog = "qlsv")
public class Sinhvien implements java.io.Serializable {

	private Integer mssv;
	@ManyToOne(fetch = FetchType.LAZY)
	private Lop lop;
	private String hoten;
	private Double dtb;

	public Sinhvien() {
	}

	public Sinhvien(Lop lop) {
		this.lop = lop;
	}

	public Sinhvien(Lop lop, String hoten, Double dtb) {
		this.lop = lop;
		this.hoten = hoten;
		this.dtb = dtb;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "mssv", unique = true, nullable = false)
	public Integer getMssv() {
		return this.mssv;
	}

	public void setMssv(Integer mssv) {
		this.mssv = mssv;
	}

	
	@JoinColumn(name = "malop", nullable = false)
	public Lop getLop() {
		return this.lop;
	}

	public void setLop(Lop lop) {
		this.lop = lop;
	}

	@Column(name = "hoten", length = 50)
	public String getHoten() {
		return this.hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	@Column(name = "dtb", precision = 22, scale = 0)
	public Double getDtb() {
		return this.dtb;
	}

	public void setDtb(Double dtb) {
		this.dtb = dtb;
	}

}
