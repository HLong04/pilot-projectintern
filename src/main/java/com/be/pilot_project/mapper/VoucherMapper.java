package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.VoucherDTO;
import com.be.pilot_project.entity.Voucher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoucherMapper extends BaseMapper<Voucher, VoucherDTO>{
}
