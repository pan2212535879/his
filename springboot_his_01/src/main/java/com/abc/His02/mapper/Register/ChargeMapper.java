package com.abc.His02.mapper.Register;

import com.abc.His02.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChargeMapper {
	List<CheckApply> selectByIDCheckAppy(int RegistID);
	List<Prescription> selectByIDPrescription(int RegistID);
	Fmeditem selectByID(int ID);
	List<PrescriptionDetailed> selectByDetailed(int PrescriptionID);
	Drugs selectByDrugID(int ID);
	int updateCheckApply(Integer ID);
	int updatePrescriptionDetailed(Integer ID);

}
