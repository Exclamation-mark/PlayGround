import {Component, OnInit, ViewChild} from '@angular/core';

class Point {
  x: number;
  y: number;
  haveBeen: boolean;
  isWall: boolean;
}

@Component({
  selector: 'app-custom-script',
  templateUrl: './custom-script.component.html',
  styleUrls: ['./custom-script.component.less']
})
export class CustomScriptComponent implements OnInit {
  x = 0;
  y = 0;
  time = 0;
  isRunning = false;
  editorOptions = {theme: 'vs-light', language: 'javascript'};
  points: Point[][];
  interval: any;
  code = `
const getDir = (robot, holder) => {
    return Math.ceil(Math.random()*4);
}
  `;

  constructor() {
  }

  ngOnInit(): void {
    this.points = [];
    for (let i = 0; i < 10; i++) {
      const arr: Point[] = [];
      for (let j = 0; j < 10; j++) {
        arr.push({x: i, y: j, isWall: i === j, haveBeen: false});
      }
      if (i !== 1) {
        arr[5].haveBeen = true;
      }
      this.points.push(arr);
    }
  }

  onStart(): void {
    this.isRunning = true;
    this.x = 0;
    this.y = 0;
    this.interval = setInterval(() => {
      this.move();
    }, 200);
  }

  onStop(): void {
    this.isRunning = false;
    if (this.interval) {
      clearInterval(this.interval);
    }
  }

  private move(): void {
    if (this.x < this.points.length - 1 && this.y < this.points.length - 1) {
      if (this.time % 2 === 0) {
        this.x++;
      } else {
        this.y++;
      }
    }else {
      clearInterval(this.interval);
    }
    const function1 = new Function(`${this.code};return getDir`)();
    console.log('zzq see new direction ', function1({}, {}));
    this.time++;
  }
}
