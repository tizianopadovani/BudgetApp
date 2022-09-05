import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Transaction} from "../../models/Transaction";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  constructor(private http: HttpClient) { }

  postTransaction(transaction: Transaction) : Observable<Transaction> {

    return this.http.post<Transaction>("http://localhost:8080/transaction", transaction);

  }

  getTransactionById(id: string) : Observable<Transaction> {

    return this.http.get<Transaction>("http://localhost:8080/transaction/" + id);

  }

  deleteTransaction(id: string) : Observable<Transaction> {

    return this.http.delete<Transaction>("http://localhost:8080/transaction/" + id);

  }

  patchTransaction(id: string, transaction: Transaction) : Observable<Transaction> {

    return this.http.patch<Transaction>("http://localhost:8080/transaction/" + id, transaction);

  }

  getTransactionsByWalletId(id: string) : Observable<Transaction[]> {

    return this.http.get<Transaction[]>("http://localhost:8080/transaction/wallet/" + id);

  }

  getTransactionsByUserId(id: string) : Observable<Transaction[]> {

    return this.http.get<Transaction[]>("http://localhost:8080/transaction/user/" + id);

  }

}
