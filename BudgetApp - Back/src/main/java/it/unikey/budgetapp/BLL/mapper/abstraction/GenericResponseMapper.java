package it.unikey.budgetapp.BLL.mapper.abstraction;

import java.util.List;

public interface GenericResponseMapper<E, D> {

    D asDTO(E entity);

    List<D> asDTOList(List<E> EntityList);

}
