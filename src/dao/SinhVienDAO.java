package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import domain.Sinhvien;

public class SinhVienDAO implements daoInterface<Sinhvien>{
	
	private List<Sinhvien>dssv = new ArrayList<Sinhvien>();

	@Override
	public Optional<Sinhvien> get(long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(dssv.get((int)id));
	}

	@Override
	public List<Sinhvien> getAll() {
		// TODO Auto-generated method stub
		return dssv;
	}

	@Override
	public void save(Sinhvien t) {
		// TODO Auto-generated method stub
		dssv.add(t);
		
	}

	@Override
	public void update(Sinhvien t, String[] params) {
		// TODO Auto-generated method stub
		t.setHoten(Objects.requireNonNull(params[0],"Name must non null"));
		t.setDtb(Double.valueOf(Objects.requireNonNull(params[1],"Type a number")));
		
	}

	@Override
	public void delete(Sinhvien t) {
		// TODO Auto-generated method stub
		dssv.remove(t);
	}

}
