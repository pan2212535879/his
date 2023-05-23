package com.abc.His02.service.Register;

import com.abc.His02.entity.*;

import java.util.List;

public interface ChargeService {
	List<CheckApply> selectByIDCheckAppy(int RegistID);
	List<Prescription> selectByIDPrescription(int RegistID);
	Fmeditem selectByID(int ID);
	List<PrescriptionDetailed> selectByDetailed(int PrescriptionID);
	Drugs selectByDrugID(int ID);
	int updateCheckApply(Integer ID);
	int updatePrescriptionDetailed(Integer ID);
}
