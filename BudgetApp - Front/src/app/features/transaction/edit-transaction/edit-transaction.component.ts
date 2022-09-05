import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {TransactionService} from "../../../core/services/transaction-service/transaction.service";
import {Transaction} from "../../../core/models/Transaction";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs";
import {WalletService} from "../../../core/services/wallet-service/wallet.service";
import {Wallet} from "../../../core/models/Wallet";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-edit-transaction',
  templateUrl: './edit-transaction.component.html',
  styleUrls: ['./edit-transaction.component.scss']
})
export class EditTransactionComponent implements OnInit, OnDestroy, OnChanges {

  idUtenteVuoto = "902ef764-1e82-4889-a639-224c4e8536e1";
  idUtente = "5e4f7c04-7aac-4957-a3c5-4d6174e2af2c";

  transactionList : Transaction[] = [];
  walletList : Wallet[] = [];
  id ?: string;
  obj ?: Transaction;
  editTransactionForm: FormGroup = new FormGroup<any>({});

  transactionSubscription ?: Subscription;
  transactionEditSubscription ?: Subscription;
  walletSubscription ?: Subscription;


  constructor(private transactionService : TransactionService, private activatedRoute : ActivatedRoute, private walletService : WalletService) { }

  ngOnInit(): void {
    this.getTransactionByUserId(this.idUtente);
    this.walletSubscription = this.walletService.getWalletsByUserId(this.idUtente).subscribe(res => {this.walletList = [...res]});

    this.editTransactionForm = new FormGroup({
      transactionDate: new FormControl(''),
      amount: new FormControl(''),
      isIncome: new FormControl(''),
      description: new FormControl(''),
      label: new FormControl(''),
      wallet_id: new FormControl('')
    });
  }

  async transactionValue() {
    this.activatedRoute.paramMap.subscribe(
      params => {
        this.id = params?.get('id')!;
      }
    );
    this.obj = this.transactionList.find(res => this.id == res.id) as Transaction;
    this.editTransactionForm.patchValue(this.obj);
  }

  getTransactionByUserId(id : string) {
    this.transactionSubscription = this.transactionService.getTransactionsByUserId(id).subscribe(
      res => {this.transactionList = res},
      error => {console.log(error)},
      () => {this.transactionValue().then();}
    )
  }

  submit() {

    if(this.id)
      this.transactionEditSubscription = this.transactionService.patchTransaction(this.id, this.editTransactionForm.value).subscribe(
        () => {this.getTransactionByUserId(this.idUtente)},
        error => {console.log(error)},
        () => {console.log("Transaction updated")}
      )
  }

  ngOnDestroy(): void {
    this.transactionSubscription?.unsubscribe();
    this.transactionEditSubscription?.unsubscribe();
    this.walletSubscription?.unsubscribe();
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.editTransactionForm.value)
  }
}
