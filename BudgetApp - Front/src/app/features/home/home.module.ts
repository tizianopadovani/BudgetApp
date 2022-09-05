import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import {RouterModule, Routes} from "@angular/router";
import {LayoutModule} from "../../core/layout/layout.module";
import {SharedModule} from "../../shared/shared.module";

const routes : Routes = [
  {path:"", component:HomeComponent}
];

@NgModule({
  declarations: [
    HomeComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    LayoutModule,
    SharedModule
  ]
})
export class HomeModule { }
