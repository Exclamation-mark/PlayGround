import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-full-screen',
  templateUrl: './full-screen.component.html',
  styleUrls: ['./full-screen.component.less']
})
export class FullScreenComponent implements OnInit {
  editorOptions = {theme: 'vs-light', language: 'json'};
  code = '';
  constructor() { }

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

}
