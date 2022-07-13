import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-split-v2',
  templateUrl: './split-v2.component.html',
  styleUrls: ['./split-v2.component.less']
})
export class SplitV2Component implements OnInit {
  defaultRightSize = 60;
  constructor() { }

  ngOnInit(): void {
  }

  onResizeRight($event: any) {
    if (this.defaultRightSize === 240) {
      this.defaultRightSize = 239;
      return;
    }
    this.defaultRightSize = 240;
  }

  onClosePanel($event: any) {
    if (this.defaultRightSize === 60) {
      this.defaultRightSize = 59;
      return;
    }
    this.defaultRightSize = 60;
  }

  onRightDragEnd($event: any) {
    this.defaultRightSize = $event.sizes[1];
  }
}
