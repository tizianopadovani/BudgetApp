import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransactionComponent } from './transaction.component';
import {RouterModule, Routes} from "@angular/router";
import {LayoutModule} from "../../core/layout/layout.module";
import {SharedModule} from "../../shared/shared.module";
import { EditTransactionComponent } from './edit-transaction/edit-transaction.component';
import {ReactiveFormsModule} from "@angular/forms";
import { TableTransactionComponent } from './table-transaction/table-transaction.component';
import {PipesModule} from "../../core/pipes/pipes.module";

const routes : Routes = [
  {path:"", component:TransactionComponent},
  {path:"edit-transaction/:id", component:EditTransactionComponent},
  {path:"table-transaction", component:TableTransactionComponent}
]

@NgModule({
  declarations: [
    TransactionComponent,
    EditTransactionComponent,
    TableTransactionComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    LayoutModule,
    SharedModule,
    ReactiveFormsModule,
    PipesModule
  ]
})
export class TransactionModule { }
