import { Component, OnInit } from '@angular/core';
import {IHeaderParams} from 'ag-grid-community';
import {IHeaderAngularComp} from 'ag-grid-angular-legacy';

@Component({
  selector: 'app-custom-header',
  templateUrl: './custom-header.component.html',
  styleUrls: ['./custom-header.component.less']
})
export class CustomHeaderComponent implements OnInit, IHeaderAngularComp {

  constructor() {
  }

  ngOnInit(): void {
  }

  agInit(params: IHeaderParams): void {
    console.log('zzq see header params', params);
  }

  refresh(params: IHeaderParams): boolean {
    console.log('zzq see refresh params', params);
    return false;
  }

}
