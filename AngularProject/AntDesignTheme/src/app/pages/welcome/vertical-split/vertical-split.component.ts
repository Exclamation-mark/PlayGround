import { Component, OnInit } from '@angular/core';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';
import {ColDef} from 'ag-grid-community';
import {CustomHeaderComponent} from '../custom-header/custom-header.component';
import {ButtonCellRendererComponent} from '../button-cell-renderer/button-cell-renderer.component';
import {NzMessageService} from 'ng-zorro-antd/message';

@Component({
  selector: 'app-vertical-split',
  templateUrl: './vertical-split.component.html',
  styleUrls: ['./vertical-split.component.less']
})
export class VerticalSplitComponent implements OnInit {
  title: string = 'tttt';
  version: string = 'asxsasds';
  subject = new Subject<ResizeObserverEntry[]>();
  maxWidth = 500;
  maxHeight = 500;
  width = 500;
  height = 500;
  editorOptions = {theme: 'vs-light', language: 'javascript'};
  code = 'function x() {\nconsole.log("Hello world!");\n}';
  code1 = 'function x() {\nconsole.log("Hello world!");\n}';
  code2 = 'function x() {\nconsole.log("Hello world!");\n}';
  code3 = 'function x() {\nconsole.log("Hello world!");\n}';

  sideBar = 'filters';
  columnDefs: ColDef[] = [
    {field: 'id', headerComponent: CustomHeaderComponent },
    {field: 'make' , resizable: true, editable: true,
      cellStyle: {'border-left': '2px solid orange'}
    },
    { field: 'model', resizable: true, editable: true,
      cellStyle: {'border-left': '2px solid orange'}
    },
    { field: 'price',
      cellStyle: {'border-left': '2px solid orange'}
    },
    {
      field: '操作',
      minWidth: 300,
      cellRenderer: 'customRenderer',
      cellStyle: {'border-left': '2px solid orange'}
    }
  ];
  frameworkComponents = {
    customRenderer: ButtonCellRendererComponent,
  };
  defaultColDef = {
    editable: false,
    sortable: false,
    flex: 1,
    minWidth: 100,
    filter: false,
    resizable: false,
    headerComponentParams: {
      menuIcon: 'fa-bars',
    },
  };
  rowData: { id?: number, make: string, model: string, price: number}[] = [
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 }
  ];
  rowData1: { id?: number, make: string, model: string, price: number}[] = [
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 },
    { make: 'Toyota', model: 'Celica', price: 35000 },
    { make: 'Ford', model: 'Mondeo', price: 32000 }
  ];
  constructor(private message: NzMessageService) { }

  ngOnInit(): void {
    this.rowData.forEach((value, index) => {
      value.id = index;
    });
    this.rowData1.forEach((value, index) => {
      value.id = index;
    });
    this.subject.pipe(debounceTime(500)).subscribe((e: ResizeObserverEntry[]) => {
      console.log('zzq see width', e);
      console.log('zzq see width', e[0].contentRect.width);
      console.log('zzq see height', e[0].contentRect.height);
      this.width = e[0].contentRect.width;
      this.height = e[0].contentRect.height;
      this.maxWidth = e[0].contentRect.width - 100;
      this.maxHeight = e[0].contentRect.height - 150;
    });
  }

  sendData(e: ResizeObserverEntry[]) {
    this.subject.next(e);
  }
  // tslint:disable-next-line:typedef
  showData() {
    this.message.success('see ' + JSON.stringify(this.rowData[0]));
  }

}
