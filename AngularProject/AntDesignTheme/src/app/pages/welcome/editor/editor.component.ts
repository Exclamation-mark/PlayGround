import { Component, OnInit } from '@angular/core';
import EditorJS from '@editorjs/editorjs';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.less']
})
export class EditorComponent implements OnInit {
  editorOptions = {theme: 'vs-light', language: 'json'};
  code = '';
  editor = new EditorJS();
  constructor() { }

  ngOnInit(): void {
  }

  getJson() {
    this.editor.save().then((res) => {
      this.code = JSON.stringify(res, null, '\t');
    });
  }
}
