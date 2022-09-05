package it.unikey.budgetapp.BLL.mapper.implementation.response;

import it.unikey.budgetapp.BLL.dto.response.WalletResponseDTO;
import it.unikey.budgetapp.BLL.mapper.abstraction.GenericResponseMapper;
import it.unikey.budgetapp.DAL.entities.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletResponseMapper extends GenericResponseMapper<Wallet, WalletResponseDTO> {



}
