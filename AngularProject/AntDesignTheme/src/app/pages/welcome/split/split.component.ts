import { Component, OnInit } from '@angular/core';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

@Component({
  selector: 'app-split',
  templateUrl: './split.component.html',
  styleUrls: ['./split.component.less']
})
export class SplitComponent implements OnInit {
  version: string = 'asxsasds';
  subject = new Subject<ResizeObserverEntry[]>();
  maxWidth = 500;
  maxHeight = 500;
  width = 500;
  rightWidth = document.body.offsetWidth - 500;
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
      this.rightWidth = document.body.offsetWidth - this.width;
    });
  }

  sendData(e: ResizeObserverEntry[]) {
    this.subject.next(e);
  }

}
