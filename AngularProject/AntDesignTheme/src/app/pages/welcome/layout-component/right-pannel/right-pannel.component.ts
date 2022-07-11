import {Component, OnInit} from '@angular/core';
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
  isFirst = true;
  subject = new Subject<ResizeObserverEntry[]>();
  editorOptions = {theme: 'vs-light', language: 'javascript'};
  code = 'function x() {\nconsole.log("Hello world!");\n}';

  constructor() {
  }

  ngOnInit(): void {
    this.subject.pipe(debounceTime(1)).subscribe((e: ResizeObserverEntry[]) => {
        if (this.isFirst) {
          this.isFirst = false;
          return;
        }
        console.log('zzq see pannel width', e[0].contentRect.width);
        if (e[0].contentRect.width >= 240 && this.cWidth === 80) {
          this.cWidth = 240;
          this.toggleCollapsed();
        } else if (e[0].contentRect.width < 250 && this.cWidth === 240) {
          this.cWidth = 80;
          this.toggleCollapsed();
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

}
