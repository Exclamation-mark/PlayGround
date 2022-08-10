import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-monaco-input',
  templateUrl: './monaco-input.component.html',
  styleUrls: ['./monaco-input.component.less']
})
export class MonacoInputComponent implements OnInit, OnChanges {
  @Input()
  iro = false;
  editorOptions = {theme: 'vs-light', language: 'json', readOnly: false};
  code = '';

  constructor() {
  }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log('zzq see changed', changes);
    this.editorOptions = {...this.editorOptions, readOnly: this.iro};
  }

}
