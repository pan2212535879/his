package com.abc.His02.service.Register;

import com.abc.His02.entity.Invoice;
import com.abc.His02.mapper.Register.BackNumberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BackNumberServiceImpl implements BackNumberService {
	@Autowired
	private BackNumberMapper dao;

	@Override
	public List<Invoice> selectAllInvoice(Invoice invoice) {
		return dao.selectAllInvoice(invoice);
	}

	@Override
	public int updateInvoice(Invoice invoice) {
		return dao.updateInvoice(invoice);
	}

	@Override
	public int addInvoice(Invoice invoice) {
		return dao.addInvoice(invoice);
	}

	@Override
	public int deleteInvoice(Invoice invoice) {
		return dao.deleteInvoice(invoice);
	}
}
