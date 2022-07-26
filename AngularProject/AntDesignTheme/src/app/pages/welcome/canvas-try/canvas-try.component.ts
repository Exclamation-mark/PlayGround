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
      const grid = {
        width: 0,
        height: 0,
      };

      grid.width = 25;
      grid.height = 25;
      const canvasWidth = 500;
      const canvasHeight = 500;

      this.myCanvas.nativeElement.style.width = canvasWidth + 'px';
      this.myCanvas.nativeElement.style.height = canvasWidth + 'px';
      this.myCanvas.nativeElement.style.border = '1px solid black';
      ctx.beginPath();
      ctx.lineWidth = 1;
      // @ts-ignore
      ctx.lineStyle = 'black';
      for (let x = 0; x < canvasWidth; x += canvasWidth / grid.width) {
        ctx.moveTo(x, 0);
        ctx.lineTo(x, canvasHeight);
      }
      for (let y = 0; y < canvasHeight; y += canvasHeight / grid.height) {
        ctx.moveTo(0, y);
        ctx.lineTo(canvasWidth, y);
      }
      ctx.stroke();
    }
  }
}
