import { Component, OnInit } from '@angular/core';
import EditorJS from '@editorjs/editorjs';
import {CustomImage} from './plugins/image';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.less']
})
export class EditorComponent implements OnInit {
  editorOptions = {theme: 'vs-light', language: 'json'};
  code = '';
  editor = new EditorJS({
    readOnly: false,
    tools: {
      image: CustomImage
    },
    data: {
      time: 1552744582955,
      blocks: [
        {
          id: 'zzqCustom',
          type: 'image',
          data: {
            url: 'https://cdn.pixabay.com/photo/2017/09/01/21/53/blue-2705642_1280.jpg'
          }
        },
        {
          id: 'c2xeQuzaiJ',
          type: 'paragraph',
          data: {
            text: 'asd'
          }
        },
      ],
      version: '2.11.10'
    }
  });
  constructor() { }

  ngOnInit(): void {
  }

  getJson() {
    this.editor.save().then((res) => {
      this.code = JSON.stringify(res, null, '\t');
    });
  }
}
