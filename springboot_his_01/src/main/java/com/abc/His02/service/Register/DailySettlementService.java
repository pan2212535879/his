package com.abc.His02.service.Register;

import com.abc.His02.entity.Invoice;

import java.util.Date;
import java.util.List;

public interface DailySettlementService {
	List<Invoice> selectAllInvoice(Date start_time, Date end_time);
	int updateInvoice(Invoice invoice);
}
