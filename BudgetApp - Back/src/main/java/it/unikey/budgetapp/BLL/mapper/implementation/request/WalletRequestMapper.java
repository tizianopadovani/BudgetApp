package it.unikey.budgetapp.BLL.mapper.implementation.request;

import it.unikey.budgetapp.BLL.dto.request.WalletRequestDTO;
import it.unikey.budgetapp.BLL.mapper.abstraction.GenericRequestMapper;
import it.unikey.budgetapp.DAL.entities.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletRequestMapper extends GenericRequestMapper<Wallet, WalletRequestDTO> {



}
