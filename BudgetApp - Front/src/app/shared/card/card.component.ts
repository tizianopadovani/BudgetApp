import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Transaction} from "../../core/models/Transaction";

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.scss']
})
export class CardComponent implements OnInit {

  @Input() obj ?: Transaction;
  @Input() transactionList : Transaction[] = [];

  @Output() transactionDeleteEmitter : EventEmitter<Transaction> = new EventEmitter<Transaction>();
  @Output() transactionEditEmitter : EventEmitter<Transaction> = new EventEmitter<Transaction>();

  popUp : boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  openPopUp(obj : Transaction) {
    this.obj = {...obj};
    this.popUp = !this.popUp;
  }

  closePopUp() {
    this.popUp = false;
  }

  sendTransactionToDelete() {
    this.transactionDeleteEmitter.emit(this.obj);
    this.closePopUp();
  }

}


