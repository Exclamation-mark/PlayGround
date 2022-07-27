import {Component, OnInit, ViewChild} from '@angular/core';
import {MonacoStandaloneCodeEditor} from '@materia-ui/ngx-monaco-editor';
import * as ts from 'typescript';

class Point {
  x: number;
  y: number;
  haveBeen: boolean;
  isWall: boolean;
}
class Robot{
  direction = 100;
  readonly top = 103;

  public face(direction: number): void {
    if (Number.isInteger(direction)) {
      this.direction = direction % 4;
    }
  }
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
  editorOptions = {theme: 'vs-light', language: 'typescript'};
  points: Point[][];
  holder = {};
  robot = new Robot();
  interval: any;
  editor: MonacoStandaloneCodeEditor;
  code = `
const getDir = (robot: Robot, holder: any) => {
    if(!holder.i){
        holder.i = 1
    }
    holder.i++;
    console.log(' holder', holder)
    const newDirection = Math.random() * 4
    robot.face(newDirection)
}
  `;

  constructor() {
  }

  ngOnInit(): void {
    this.robot.face(100);
    console.log('zzq see ngOnInit direction', this.robot.direction);
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
    this.holder = {};
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

  onEditorReady(editor: MonacoStandaloneCodeEditor): void {
    monaco.languages.typescript.javascriptDefaults.setDiagnosticsOptions({
      noSemanticValidation: true,
      noSyntaxValidation: false
    });

// compiler options
    monaco.languages.typescript.javascriptDefaults.setCompilerOptions({
      target: monaco.languages.typescript.ScriptTarget.ES2020,
      allowNonTsExtensions: true
    });
    const libSource = [
      'declare class Robot {',
      '    /**',
      '     * Returns the next fact',
      '     * @params direction ',
      '     */',
      '    public face(direction):void',
      '    readonly top = 100',
      '}'
    ].join('\n');
    const libUri = 'ts:filename/Robot.d.ts';
    monaco.languages.typescript.javascriptDefaults.addExtraLib(libSource, libUri);
    monaco.editor.createModel(libSource, 'typescript', monaco.Uri.parse(libUri));
    console.log('zzq see add language');
    this.editor = editor;
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
    const tmpCode = ts.transpile(this.code);
    const function1 = new Function(tmpCode);
    function1(this.robot, this.holder);
    console.log('zzq see direction', this.robot.direction);
    this.time++;
  }
}
