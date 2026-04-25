package com.be.pilot_project.dao;

import com.be.pilot_project.dto.VoucherDTO;

import java.util.List;
import java.util.Optional;

public interface VoucherDao {
	List<VoucherDTO> findAll();
	List<VoucherDTO> findActive();
	Optional<VoucherDTO> findById(Integer voucherId);
	Optional<VoucherDTO> findByCode(String voucherCode);
}
