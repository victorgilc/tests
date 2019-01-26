package br.com.bb.mapper;

/**
 * 
 * Contract for mapper
 *
 * @param <E>
 *            for entity
 * @param <D>
 *            for DTO
 */
public interface IMapper<E, D> {

	/**
	 * Convert a entity do dto
	 * 
	 * @param entity
	 * @return a dto
	 */
	D toDto(E entity);

	/**
	 * Converto a dto to entity
	 * 
	 * @param dto
	 * @return a entity
	 */
	E toEntity(D dto);
}
