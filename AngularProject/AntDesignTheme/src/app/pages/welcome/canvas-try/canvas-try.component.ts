import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-canvas-try',
  templateUrl: './canvas-try.component.html',
  styleUrls: ['./canvas-try.component.less']
})
export class CanvasTryComponent implements OnInit, AfterViewInit {
  @ViewChild('myCanvas')
  myCanvas: ElementRef<HTMLCanvasElement>;
  context: CanvasRenderingContext2D;
  constructor() {
  }

  ngOnInit(): void {
  }


  ngAfterViewInit(): void {
    const ctx = this.myCanvas.nativeElement.getContext('2d');
    if (ctx !== null) {
      this.context = ctx;
      ctx.fillStyle = 'green';
      ctx.fillRect(10, 10, 150, 100);
    }
  }
}
