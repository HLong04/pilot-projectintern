package com.be.pilot_project.mapper;

public interface BaseMapper<E, D> {
	D toDto(E entity);
	E toEntity(D dto);
}
