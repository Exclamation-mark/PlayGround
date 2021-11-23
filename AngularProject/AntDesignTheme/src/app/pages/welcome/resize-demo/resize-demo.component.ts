import { Component, OnInit } from '@angular/core';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

@Component({
  selector: 'app-resize-demo',
  templateUrl: './resize-demo.component.html',
  styleUrls: ['./resize-demo.component.less']
})
export class ResizeDemoComponent implements OnInit {
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
  constructor() { }

  ngOnInit(): void {
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
}
