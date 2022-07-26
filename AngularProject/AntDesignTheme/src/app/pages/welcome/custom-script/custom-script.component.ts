import { Component, OnInit } from '@angular/core';

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
  x: 0;
  y: 0;
  isRunning = false;
  editorOptions = {theme: 'vs-light', language: 'javascript'};
  points: Point[][];
  code = `
function controlRobot(robot, holder){

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
  }

  onStop(): void {
    this.isRunning = false;
  }
}
