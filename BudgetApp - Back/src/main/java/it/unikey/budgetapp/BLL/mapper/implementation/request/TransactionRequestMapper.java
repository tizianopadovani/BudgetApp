package it.unikey.budgetapp.BLL.mapper.implementation.request;

import it.unikey.budgetapp.BLL.dto.request.TransactionRequestDTO;
import it.unikey.budgetapp.BLL.mapper.abstraction.GenericRequestMapper;
import it.unikey.budgetapp.DAL.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionRequestMapper extends GenericRequestMapper<Transaction, TransactionRequestDTO> {



}
