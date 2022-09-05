import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Wallet} from "../../models/Wallet";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WalletService {

  constructor(private http: HttpClient) { }

  postWallet(wallet: Wallet) : Observable<Wallet> {

    return this.http.post<Wallet>("http://localhost:8080/wallet", wallet);

  }

  getAllWallets() : Observable<Wallet[]> {

    return this.http.get<Wallet[]>("http://localhost:8080/wallet");

  }

  getWalletById(id: string) : Observable<Wallet> {

    return this.http.get<Wallet>("http://localhost:8080/wallet/" + id);

  }

  deleteWallet(id: string) : Observable<Wallet> {

    return this.http.delete<Wallet>("http://localhost:8080/wallet/" + id);

  }

  patchWallet(id: string, wallet: Wallet) : Observable<Wallet> {

    return this.http.patch<Wallet>("http://localhost:8080/wallet/" + id, wallet);

  }

  getWalletsByUserId(id: string) : Observable<Wallet[]> {

    return this.http.get<Wallet[]>("http://localhost:8080/wallet/user/" + id);

  }

}
