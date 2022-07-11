import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome.component';
import {AgGridDemoComponent} from './ag-grid-demo/ag-grid-demo.component';
import {ResizeDemoComponent} from './resize-demo/resize-demo.component';
import {NavComponent} from './nav/nav.component';
import {SplitComponent} from './split/split.component';

const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'agGrid', component: AgGridDemoComponent },
  { path: 'nav', component: NavComponent },
  { path: 'split', component: SplitComponent },
  { path: 'reSize', component: ResizeDemoComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WelcomeRoutingModule { }
