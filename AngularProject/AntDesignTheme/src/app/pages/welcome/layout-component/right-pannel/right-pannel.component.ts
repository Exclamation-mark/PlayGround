import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

@Component({
  selector: 'app-right-pannel',
  templateUrl: './right-pannel.component.html',
  styleUrls: ['./right-pannel.component.less']
})
export class RightPannelComponent implements OnInit {
  isCollapsed = true;
  cWidth = 80;
  showBtn = false;
  isFirst = true;
  subject = new Subject<ResizeObserverEntry[]>();
  editorOptions = {theme: 'vs-light', language: 'javascript'};
  code = 'function x() {\nconsole.log("Hello world!");\n}';
  @Output()
  showC = new EventEmitter();
  @Output()
  closeC = new EventEmitter();
  width = 0;

  constructor() {
  }

  ngOnInit(): void {
    this.subject.pipe(debounceTime(10)).subscribe((e: ResizeObserverEntry[]) => {
        if (this.isFirst) {
          this.isFirst = false;
          return;
        }
        this.width = e[0].contentRect.width;
        console.log('zzq see pannel width', this.width);
        if (this.width <= 70) {
          this.showBtn = false;
        }else {
          this.showBtn = true;
        }
      }
    );
  }

  toggleCollapsed(): void {
    this.isCollapsed = !this.isCollapsed;
  }

  // tslint:disable-next-line:typedef
  windowSize(event: any) {
    this.subject.next(event);
  }

  // tslint:disable-next-line:typedef
  onSow() {
    if (this.width > 200) {
      return;
    }
    this.showC.emit({});
  }


  // tslint:disable-next-line:typedef
  onClose() {
    this.showBtn = false;
    this.closeC.emit({});
  }

}
