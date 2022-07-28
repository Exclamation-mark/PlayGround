import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome.component';
import {AgGridDemoComponent} from './ag-grid-demo/ag-grid-demo.component';
import {ResizeDemoComponent} from './resize-demo/resize-demo.component';
import {NavComponent} from './nav/nav.component';
import {SplitComponent} from './split/split.component';
import {SplitV2Component} from './split-v2/split-v2.component';
import {TreeComponent} from './tree/tree.component';
import {EditorComponent} from './editor/editor.component';
import {CustomScriptComponent} from './custom-script/custom-script.component';
import {CanvasTryComponent} from './canvas-try/canvas-try.component';
import {NativeElementComponent} from './native-element/native-element.component';

const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'agGrid', component: AgGridDemoComponent },
  { path: 'nav', component: NavComponent },
  { path: 'split', component: SplitComponent },
  { path: 'tree', component: TreeComponent },
  { path: 'editor', component: EditorComponent },
  { path: 'CustomScript', component: CustomScriptComponent },
  { path: 'NativeElement', component: NativeElementComponent },
  { path: 'Canvas', component: CanvasTryComponent },
  { path: 'split-v2', component: SplitV2Component },
  { path: 'reSize', component: ResizeDemoComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WelcomeRoutingModule { }
