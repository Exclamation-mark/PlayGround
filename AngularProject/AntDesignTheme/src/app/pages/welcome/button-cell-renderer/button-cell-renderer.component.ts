import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import {AgRendererComponent} from 'ag-grid-angular-legacy';
import {IAfterGuiAttachedParams, ICellRendererParams} from 'ag-grid-community';

@Component({
  selector: 'app-button-cell-renderer',
  templateUrl: './button-cell-renderer.component.html',
  styleUrls: ['./button-cell-renderer.component.less']
})
export class ButtonCellRendererComponent implements OnInit, AgRendererComponent {
  dataItem: any;
  constructor(private message: NzMessageService) {
  }

  ngOnInit(): void {
  }

  afterGuiAttached(params?: IAfterGuiAttachedParams): void {
  }

  agInit(params: ICellRendererParams): void {
    this.dataItem = params.data;
    console.log('zzq see', this.dataItem);
  }

  refresh(params: ICellRendererParams): boolean {
    return false;
  }

  run() {
    this.message.success('run ' + this.dataItem.id);
  }

  diff() {
    this.message.success('diff ' + this.dataItem.id);
  }

  detail() {
    this.message.success('detail ' + this.dataItem.id);
  }
}
