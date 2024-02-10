package org.mains.Cine.mapper;

import java.util.List;

public interface EntityMapper<D,E> {
	E toEntity(D dto);
	D toDto(E entity);
	List<D> toDto(List<E> entityList);
	List<E> toEntity(List<D> dtoList);
}
