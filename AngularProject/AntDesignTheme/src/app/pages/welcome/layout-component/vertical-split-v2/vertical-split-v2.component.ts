import {Component, ElementRef, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {ColDef} from 'ag-grid-community';
import {CustomHeaderComponent} from '../../custom-header/custom-header.component';
import {ButtonCellRendererComponent} from '../../button-cell-renderer/button-cell-renderer.component';
import {NzMessageService} from 'ng-zorro-antd/message';
import PerfectScrollbar from 'perfect-scrollbar';
import {debounceTime} from 'rxjs/operators';

@Component({
  selector: 'app-vertical-split-v2',
  templateUrl: './vertical-split-v2.component.html',
  styleUrls: ['./vertical-split-v2.component.less']
})
export class VerticalSplitV2Component implements OnInit {

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

  constructor(private message: NzMessageService
    , private elementRef: ElementRef) {
  }

  // tslint:disable-next-line:typedef
  onFirstGridReady(params: any, gridContainer: HTMLElement) {
    this.message.warning('onFirstGridReady');
    const array = ['.ag-body-viewport', ' .ag-body-horizontal-scroll-viewport'];
    array.forEach(element => {
      const container = gridContainer.querySelector(element);
      if (container) {
        this.message.warning('container set');
        const ps = new PerfectScrollbar(container);
        ps.update();
      }else {
        this.message.warning('container not set');
      }
    });
  }

  // tslint:disable-next-line:typedef
  onSecondGridReady(params: any) {
    this.message.warning('onSecondGridReady');
    const agBodyViewport: HTMLElement = this.elementRef.nativeElement.querySelector('#secondGrid .ag-body-viewport');
    const agBodyHorizontalViewport: HTMLElement = this.elementRef.nativeElement.querySelector('#secondGrid .ag-body-horizontal-scroll-viewport');
    if (agBodyViewport) {
      const ps = new PerfectScrollbar(agBodyViewport);
      ps.update();
    }else {
      this.message.warning('have no viewport1');
    }
    if (agBodyHorizontalViewport) {
      const ps = new PerfectScrollbar(agBodyHorizontalViewport);
      ps.update();
    }else {
      this.message.warning('have no viewport2');
    }
  }

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
