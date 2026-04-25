package com.be.pilot_project.mapper;

import com.be.pilot_project.dto.PaymentDTO;
import com.be.pilot_project.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends BaseMapper<Payment, PaymentDTO> {
}
