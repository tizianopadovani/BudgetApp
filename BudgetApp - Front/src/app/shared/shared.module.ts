import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardComponent } from './card/card.component';
import {PipesModule} from "../core/pipes/pipes.module";
import {RouterModule} from "@angular/router";
import { SpinnerComponent } from './spinner/spinner.component';



@NgModule({
    declarations: [
        CardComponent,
        SpinnerComponent
    ],
    exports: [
        CardComponent,
        SpinnerComponent
    ],
    imports: [
        CommonModule,
        PipesModule,
        RouterModule
    ]
})
export class SharedModule { }
