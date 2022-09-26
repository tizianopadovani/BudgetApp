import {Component, OnDestroy, OnInit} from '@angular/core';
import {Transaction} from "../../../core/models/Transaction";
import {Subscription} from "rxjs";
import {TransactionService} from "../../../core/services/transaction-service/transaction.service";
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-table-transaction',
  templateUrl: './table-transaction.component.html',
  styleUrls: ['./table-transaction.component.scss']
})
export class TableTransactionComponent implements OnInit, OnDestroy {

  idUtenteVuoto = "902ef764-1e82-4889-a639-224c4e8536e1";
  idUtente = "5e4f7c04-7aac-4957-a3c5-4d6174e2af2c";

  transactionList : Transaction[] = [];
  private transactionSubscription ?: Subscription;
  totalAmount:  number = 0;
  fileName = 'ExcelSheet.xlsx';

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.getTransactionByUserId(this.idUtente).then();
  }

  async getTransactionByUserId(id: string) {
    this.transactionSubscription = await this.transactionService.getTransactionsByUserId(id).subscribe(
      observer => {
        this.transactionList = [...observer]
        for(let t of this.transactionList){
          if(t.isIncome)
            this.totalAmount += t.amount;
          else
            this.totalAmount -= t.amount;
        }
      },
      error => {
        console.log(error)
      },
      () => {
        console.log("Transactions found!")
      }
    )

  }

  exportExcel() {

    let element = document.getElementById('excel-table');
    const ws: XLSX.WorkSheet = XLSX.utils.table_to_sheet(element);

    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

    XLSX.writeFile(wb, this.fileName);

  }

  ngOnDestroy(): void {
    this.transactionSubscription?.unsubscribe();
  }

}
