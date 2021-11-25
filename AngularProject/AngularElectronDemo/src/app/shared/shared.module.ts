import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TranslateModule } from '@ngx-translate/core';

import { PageNotFoundComponent } from './components/';
import { WebviewDirective } from './directives/';
import { FormsModule } from '@angular/forms';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import {MaterialModule} from "../material/material.module";

@NgModule({
  declarations: [PageNotFoundComponent, WebviewDirective, SidenavComponent],
  imports: [CommonModule, TranslateModule, FormsModule, MaterialModule],
  exports: [TranslateModule, WebviewDirective, FormsModule, SidenavComponent]
})
export class SharedModule {}
