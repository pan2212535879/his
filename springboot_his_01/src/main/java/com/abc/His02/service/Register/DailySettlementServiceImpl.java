package com.abc.His02.service.Register;

import com.abc.His02.entity.Invoice;
import com.abc.His02.mapper.Register.DailySettlementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DailySettlementServiceImpl implements DailySettlementService {
	@Autowired
	private DailySettlementMapper dao;

	@Override
	public List<Invoice> selectAllInvoice(Date start_time, Date end_time) {
		return dao.selectAllInvoice(start_time,end_time);
	}

	@Override
	public int updateInvoice(Invoice invoice) {

		return dao.updateInvoice(invoice);
	}
}
