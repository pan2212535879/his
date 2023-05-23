package com.abc.His02.service.Register;

import java.util.List;

import com.abc.His02.entity.PatientCosts;
import com.abc.His02.mapper.Register.RegisterMapper;
import com.abc.His02.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc.His02.entity.Register;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private RegisterMapper dao;

	@Override
	public List<Register> selectAll(Register r) {
		return dao.selectAll(r);
	}

	@Override
	public Register selectById(Register r) {
		return dao.selectById(r);
	}

	@Override
	public int add(Register r) {
		return dao.add(r);
	}

	@Override
	public int delete(Register r) {
		return dao.delete(r);
	}

	@Override
	public int update(Register r) {
		return dao.update(r);
	}

	@Override
	public Invoice print(Register r) {
		return dao.print(r);
	}

	@Override
	public Invoice selectInvoice(Invoice invoice) {
		return dao.selectInvoice(invoice);
	}

	@Override
	public Invoice selectMaxInvoice() {
		return dao.selectMaxInvoice();
	}

	@Override
	public int addInvoice(Invoice invoice) {
		return dao.addInvoice(invoice);
	}

	@Override
	public int addPatientCosts(PatientCosts patientCosts) {
		return dao.addPatientCosts(patientCosts);
	}

}
