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

      const DEFAULT_OPTIONS = {
        cols       : 10,
        rows       : 10,
        width      : 100,
        height     : 100,
        weight     : 1,
        background : null,
        color      : '#000000'
      };

      const addGrid = (options: any) => {
        createCanvasGrid(options);
      };

      const createCanvasGrid = (options: any) => {
        const opts = Object.assign({}, DEFAULT_OPTIONS, options);
        console.log('zzq see v', opts);
        const canvas = this.myCanvas;
        this.myCanvas.nativeElement.width = opts.width;
        this.myCanvas.nativeElement.height = opts.height;

        const weight2 = opts.weight * 2;
        const weightHalf = opts.weight / 2;

        const availWidth =  opts.width - opts.weight;
        const availHeight = opts.height - opts.weight;

        const cellWidth = availWidth / opts.cols;
        const cellHeight = availHeight / opts.rows;

        if (options.background) {
          ctx.fillStyle = opts.background;
          ctx.fillRect(0, 0, opts.width, opts.height);
        }

        ctx.beginPath();
        ctx.strokeStyle = opts.color;
        ctx.lineWidth = opts.weight;

        for (let col = 0; col <= opts.cols; col++) {
          const newX = Math.floor(col * cellWidth) + weightHalf;
          ctx.moveTo(newX, 0);
          ctx.lineTo(newX, opts.height);
        }

        for (let row = 0; row <= opts.rows; row++) {
          const newY = (row * cellHeight) + weightHalf;
          ctx.moveTo(0, newY);
          ctx.lineTo(opts.width, newY);
        }

        ctx.stroke();

        return canvas;
      };
      addGrid({
        width: 500,
        height: 500,
        background: '#FFF',
        color: '#999'
      });

    }
  }
}
