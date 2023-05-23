package com.abc.His02.service.Register;

import java.util.List;

import com.abc.His02.entity.Invoice;
import com.abc.His02.entity.PatientCosts;
import com.abc.His02.entity.Register;

public interface RegisterService {
	List<Register> selectAll(Register r);
	Register selectById(Register r);
	int add(Register r);
	int delete(Register r);
	int update(Register r);
	Invoice print(Register r);
	Invoice selectInvoice(Invoice invoice);
	Invoice selectMaxInvoice();
	int addInvoice(Invoice invoice);
	int addPatientCosts(PatientCosts patientCosts);
}
