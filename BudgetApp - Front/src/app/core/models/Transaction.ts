import {Wallet} from "./Wallet";

export interface Transaction {

  id: string,
  createdAt: Date,
  updatedAt: Date,
  transactionDate: Date,
  amount: number,
  isIncome: boolean,
  description: string,
  label: string,
  walletResponseDTO: Wallet

}
