import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-monaco-input',
  templateUrl: './monaco-input.component.html',
  styleUrls: ['./monaco-input.component.less']
})
export class MonacoInputComponent implements OnInit {
  @Input()
  iro = false;
  editorOptions = {theme: 'vs-light', language: 'json'};
  code = '';

  constructor() { }

  ngOnInit(): void {
  }

}
