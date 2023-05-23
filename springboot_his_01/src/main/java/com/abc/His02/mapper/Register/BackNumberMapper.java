package com.abc.His02.mapper.Register;

import com.abc.His02.entity.Invoice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackNumberMapper {
	List<Invoice> selectAllInvoice(Invoice invoice);
	int updateInvoice(Invoice invoice);
	int addInvoice(Invoice invoice);
	int deleteInvoice(Invoice invoice);
}
