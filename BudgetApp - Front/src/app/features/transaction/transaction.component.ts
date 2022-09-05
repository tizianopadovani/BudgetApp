import {Component, OnDestroy, OnInit} from '@angular/core';
import {TransactionService} from "../../core/services/transaction-service/transaction.service";
import {Subscription} from "rxjs";
import {Transaction} from "../../core/models/Transaction";

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.scss']
})
export class TransactionComponent implements OnInit, OnDestroy {

  idUtenteVuoto = "902ef764-1e82-4889-a639-224c4e8536e1";
  idUtente = "5e4f7c04-7aac-4957-a3c5-4d6174e2af2c";

  transactionList : Transaction[] = [];
  labelList : string[] = [];
  isOpen : boolean = true;
  obj ?: Transaction;

  private transactionSubscription ?: Subscription;
  private transactionManageSubscription ?: Subscription;
  showSpinner: boolean = false;


  constructor(private transactionService: TransactionService) {
  }

  ngOnInit(): void {
    this.getTransactionByUserId(this.idUtente).then();
    this.obj = this.transactionList.find(res => this.obj?.id == res.id) as Transaction;

  }

  async getTransactionByUserId(id: string) {
    this.showSpinner = true;
    this.transactionSubscription = await this.transactionService.getTransactionsByUserId(id).subscribe(
      observer => {
        this.transactionList = [...observer]
      },
      error => {
        console.log(error)
      },
      () => {
        console.log("Transactions found!")
          this.getLabel()
          this.showSpinner = false;
      }
    )

  }

  async getLabel() {
    this.showSpinner = true;
    for (let t of this.transactionList) {
      if (t.label != null && t.label.trim() != "") {
        if (this.labelList.length < 1)
          this.labelList = [...this.labelList, t.label]
        else {
          let equals = false;
          for (let l of this.labelList) {
            if (l.trim() == t.label.trim()) {
              equals = true;
              return;
            }
          }
          if (!equals)
            this.labelList = [...this.labelList, t.label]
        }
      }
    }
    this.showSpinner = false;
  }

  openFilter() {
    this.isOpen = !this.isOpen;
  }

  deleteTransaction($event: Transaction) {
    this.transactionManageSubscription = this.transactionService.deleteTransaction($event.id).subscribe(
      () => {this.getTransactionByUserId(this.idUtente).then()},
      error => {console.log(error)},
      () => {console.log("Transaction deleted!")}
    )
  }

  ngOnDestroy(): void {
    this.transactionSubscription?.unsubscribe();
    this.transactionManageSubscription?.unsubscribe();
  }


}
