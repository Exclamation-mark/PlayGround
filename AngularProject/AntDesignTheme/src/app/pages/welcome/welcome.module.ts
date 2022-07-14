import { NgModule } from '@angular/core';

import { WelcomeRoutingModule } from './welcome-routing.module';

import { WelcomeComponent } from './welcome.component';
import { NzCollapseModule } from 'ng-zorro-antd/collapse';
import { CommonModule } from '@angular/common';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { FormsModule } from '@angular/forms';
import {AgGridDemoComponent} from './ag-grid-demo/ag-grid-demo.component';
import { AgGridModule } from 'ag-grid-angular-legacy';
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
import {CustomHeaderComponent} from './custom-header/custom-header.component';
import {NzSelectModule} from 'ng-zorro-antd/select';
import { SplitComponent } from './split/split.component';
import { RightPannelComponent } from './layout-component/right-pannel/right-pannel.component';
import {NzToolTipModule} from 'ng-zorro-antd/tooltip';
import {VerticalSplitComponent} from './layout-component/vertical-split/vertical-split.component';
import {AngularSplitModule} from 'angular-split';
import { SplitV2Component } from './split-v2/split-v2.component';
import { VerticalSplitV2Component } from './layout-component/vertical-split-v2/vertical-split-v2.component';
import {PerfectScrollbarModule} from 'ngx-perfect-scrollbar';
import { TreeComponent } from './tree/tree.component';
import {TreeModule} from '@circlon/angular-tree-component';
import {NzInputModule} from 'ng-zorro-antd/input';
import { TreeExpanderComponent } from './layout-component/tree-expander/tree-expander.component';
import { TreeContentComponent } from './layout-component/tree-content/tree-content.component';


@NgModule({
    imports: [
        WelcomeRoutingModule,
        CommonModule,
        NzTabsModule,
        AngularSplitModule,
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
        AgGridModule,
        NzSelectModule,
        NzToolTipModule,
        TreeModule,
        PerfectScrollbarModule,
        NzInputModule
    ],
  declarations: [
    WelcomeComponent,
    CustomHeaderComponent,
    AgGridDemoComponent,
    ResizeDemoComponent,
    NavComponent,
    ButtonCellRendererComponent,
    SplitComponent,
    RightPannelComponent,
    VerticalSplitComponent,
    SplitV2Component,
    VerticalSplitV2Component,
    TreeComponent,
    TreeExpanderComponent,
    TreeContentComponent
  ],
  providers: [],
  exports: [WelcomeComponent],
})
export class WelcomeModule {}
