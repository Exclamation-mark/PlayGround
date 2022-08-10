import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {NgxFullscreenDirective, NgxFullscreenTransition} from '@ultimate/ngx-fullscreen';

@Component({
  selector: 'app-full-screen',
  templateUrl: './full-screen.component.html',
  styleUrls: ['./full-screen.component.less']
})
export class FullScreenComponent implements OnInit, AfterViewInit {
  @ViewChild('fullscreen') fullscreen!: NgxFullscreenDirective;
  editorOptions = {theme: 'vs-light', language: 'json'};
  code = '';
  isFull = false;
  ifFullBrowser = false;

  constructor() {
  }

  ngOnInit(): void {
    const a = [];
    for (let i = 0; i < 1000; i++) {
      a.push({
        index: i,
        time: new Date().getTime(),
        name: new Date().toDateString()
      });
    }
    this.code = JSON.stringify(a, null, '\t');
  }

  ngAfterViewInit(): void {
    this.fullscreen.transition
      .subscribe((change: NgxFullscreenTransition) => {
        this.isFull = change.isFullscreen;
        console.log(change); // { isFullscreen: boolean, element: Element }
      });
  }

  run(): void {
    if (this.isFull) {
      this.fullscreen.exit();
    }else {
      this.fullscreen.enter();
    }
  }

  run2(): void {
    this.ifFullBrowser = !this.ifFullBrowser;
  }
}
