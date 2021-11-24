import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';

import { HomeComponent } from './home.component';
import { SharedModule } from '../shared/shared.module';
import {MonacoEditorModule} from "@materia-ui/ngx-monaco-editor";

@NgModule({
  declarations: [HomeComponent],
  imports: [CommonModule,
    MonacoEditorModule,SharedModule, HomeRoutingModule]
})
export class HomeModule {}
