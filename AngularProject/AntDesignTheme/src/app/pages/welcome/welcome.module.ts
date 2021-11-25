import { NgModule } from '@angular/core';

import { WelcomeRoutingModule } from './welcome-routing.module';

import { WelcomeComponent } from './welcome.component';
import { NzCollapseModule } from 'ng-zorro-antd/collapse';
import { CommonModule } from '@angular/common';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { FormsModule } from '@angular/forms';
import { AgGridDemoComponent } from './ag-grid-demo/ag-grid-demo.component';
import {AgGridModule} from 'ag-grid-angular';
import { ResizeDemoComponent } from './resize-demo/resize-demo.component';
import {NgxResizableModule} from '@3dgenomes/ngx-resizable';
import {ResizeObserverModule} from '@ng-web-apis/resize-observer';
import {MonacoEditorModule} from '@materia-ui/ngx-monaco-editor';
import { NavComponent } from './nav/nav.component';
import { NzTabsModule } from 'ng-zorro-antd/tabs';

@NgModule({
  imports: [
    WelcomeRoutingModule,
    CommonModule,
    NzTabsModule,
    NzCollapseModule,
    NzRadioModule,
    NgxResizableModule,
    ResizeObserverModule,
    FormsModule,
    MonacoEditorModule,
    AgGridModule.withComponents([])
  ],
  declarations: [WelcomeComponent, AgGridDemoComponent, ResizeDemoComponent, NavComponent],
  exports: [WelcomeComponent],
})
export class WelcomeModule {}
