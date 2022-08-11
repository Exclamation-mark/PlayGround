import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-json-schema',
  templateUrl: './json-schema.component.html',
  styleUrls: ['./json-schema.component.less']
})
export class JsonSchemaComponent implements OnInit {
  editorOptions = {theme: 'vs-light', language: 'json', readOnly: false};
  leftCode = '';
  rightCode = `
  {
    "a": 12,
    "b": [ 1, 2, 3],
    "c": true
  }
  `;
  constructor() { }

  ngOnInit(): void {
  }

  run(): void {
  }
}
