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
import { ButtonCellRendererComponent } from './button-cell-renderer/button-cell-renderer.component';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzMessageModule } from 'ng-zorro-antd/message';
@NgModule({
  imports: [
    WelcomeRoutingModule,
    CommonModule,
    NzTabsModule,
    NzCollapseModule,
    NzMessageModule,
    NzButtonModule,
    NzMenuModule,
    NzIconModule,
    NzDropDownModule,
    NzRadioModule,
    NgxResizableModule,
    ResizeObserverModule,
    FormsModule,
    MonacoEditorModule,
    AgGridModule.withComponents([])
  ],
  declarations: [WelcomeComponent, AgGridDemoComponent, ResizeDemoComponent, NavComponent, ButtonCellRendererComponent],
  exports: [WelcomeComponent],
})
export class WelcomeModule {}
