package it.unikey.budgetapp.BLL.mapper.implementation.response;

import it.unikey.budgetapp.BLL.dto.response.TransactionResponseDTO;
import it.unikey.budgetapp.BLL.mapper.abstraction.GenericResponseMapper;
import it.unikey.budgetapp.DAL.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionResponseMapper extends GenericResponseMapper<Transaction, TransactionResponseDTO> {



}
