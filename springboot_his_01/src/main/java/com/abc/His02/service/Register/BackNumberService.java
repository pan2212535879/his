package com.abc.His02.service.Register;

import com.abc.His02.entity.Invoice;

import java.util.List;

public interface BackNumberService {
	List<Invoice> selectAllInvoice(Invoice invoice);
	int updateInvoice(Invoice invoice);
	int addInvoice(Invoice invoice);
	int deleteInvoice(Invoice invoice);
}
