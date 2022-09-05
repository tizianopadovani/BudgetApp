import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserService} from "../../core/services/user-service/user.service";
import {User} from "../../core/models/User";
import {Subscription} from "rxjs";
import {WalletService} from "../../core/services/wallet-service/wallet.service";
import {TransactionService} from "../../core/services/transaction-service/transaction.service";
import {Wallet} from "../../core/models/Wallet";
import {Transaction} from "../../core/models/Transaction";
import * as dayjs from "dayjs";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, OnDestroy {

  idUtenteVuoto = "902ef764-1e82-4889-a639-224c4e8536e1";
  idUtente = "5e4f7c04-7aac-4957-a3c5-4d6174e2af2c";

  user?: User;
  walletList: Wallet[] = [];
  transactionList: Transaction[] = [];
  lastAccess?: string;
  totalAmount: number = 0;
  showSpinner: boolean = false;

  private userSubscription ?: Subscription;
  private walletSubscription ?: Subscription;
  private transactionSubscription ?: Subscription;


  constructor(private userService : UserService, private walletService : WalletService, private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.getUserById(this.idUtente).then();
    this.getWalletsByUserId(this.idUtente).then();
    this.getTransactionsByUserId(this.idUtente).then();
  }

 async getUserById(id: string) {
    this.showSpinner = true;
    this.userSubscription = await this.userService.getUserById(id).subscribe(
      observer => {this.user = {...observer}},
      error => {console.log(error)},
      () => {
        console.log("User found!")
        this.lastAccess = dayjs(Date.now()).format("DD MMM YYYY")
        this.showSpinner = false;
      }
    )
  }

  async getWalletsByUserId(id: string) {
    this.showSpinner = true;
    this.walletSubscription = await this.walletService.getWalletsByUserId(id).subscribe(
      observer => {this.walletList = [...observer]},
      error => {console.log(error)},
      () => {console.log("Wallets found!"), this.showSpinner = false}
    )
  }

  async getTransactionsByUserId(id: string) {
    this.showSpinner = true;
    this.transactionSubscription = await this.transactionService.getTransactionsByUserId(id).subscribe(
      observer => {this.transactionList = [...observer]},
      error => {console.log("error: ",error)},
      () => {console.log("Transactions found!")
        for(let t of this.transactionList){
          if(t.isIncome)
            this.totalAmount += t.amount;
          else
            this.totalAmount -= t.amount;
        }
        this.showSpinner = false;
      }
    )
  }

  ngOnDestroy(): void {
    this.userSubscription?.unsubscribe();
    this.walletSubscription?.unsubscribe();
    this.transactionSubscription?.unsubscribe();
  }

}
