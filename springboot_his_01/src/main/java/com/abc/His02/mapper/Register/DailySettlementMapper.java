package com.abc.His02.mapper.Register;

import com.abc.His02.entity.Invoice;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface DailySettlementMapper {
	List<Invoice> selectAllInvoice(Date start_time, Date end_time);
	int updateInvoice(Invoice invoice);
}
