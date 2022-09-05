import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path:"auth", loadChildren: () => import("./features/auth/auth.module").then(m => m.AuthModule)},
  {path:"home", loadChildren: () => import("./features/home/home.module").then(m => m.HomeModule)},
  {path:"transaction", loadChildren: () => import("./features/transaction/transaction.module").then(m => m.TransactionModule)},
  {path:"wallet", loadChildren: () => import("./features/wallet/wallet.module").then(m => m.WalletModule)},
  {path:"setting", loadChildren: () => import("./features/settings/settings.module").then(m => m.SettingsModule)},
  {path:"", redirectTo:"home", pathMatch:"full"},
  {path:"**", redirectTo:"home", pathMatch:"full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
