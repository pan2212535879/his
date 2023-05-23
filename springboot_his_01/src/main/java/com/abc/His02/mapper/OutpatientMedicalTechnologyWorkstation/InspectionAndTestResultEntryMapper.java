package com.abc.His02.mapper.OutpatientMedicalTechnologyWorkstation;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface InspectionAndTestResultEntryMapper {
    int editMovieInfo(int id, MultipartFile file, String uploadDir);
}
