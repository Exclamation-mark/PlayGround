import { Component, OnInit } from '@angular/core';
import EditorJS from '@editorjs/editorjs';
import {CustomImage} from './plugins/image';
// @ts-ignore
import Header from '@editorjs/header';
// @ts-ignore
import Image from '@editorjs/image';
// @ts-ignore
import SimpleImage from '@editorjs/simple-image';
// @ts-ignore
import CodeTool from '@editorjs/code';
// @ts-ignore
import List from '@editorjs/list';
// @ts-ignore
import Delimiter from '@editorjs/delimiter';
// @ts-ignore
import Table from '@editorjs/table';
// @ts-ignore
import Warning from '@editorjs/warning';
// @ts-ignore
import Checklist from '@editorjs/checklist';
// @ts-ignore
import LinkTool from '@editorjs/link';
// @ts-ignore
import RawTool from '@editorjs/raw';
// @ts-ignore
import Embed from '@editorjs/embed';
// @ts-ignore
import InlineCode from '@editorjs/inline-code';
// @ts-ignore
import Marker from '@editorjs/marker';
import {JsonEditor} from './plugins/json-editor';
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
      header: {
        class: Header,
        inlineToolbar: ['marker', 'inlineCode'],
        config: {
          placeholder: '',
        },
      },
      json: JsonEditor,
      image: {
        class: Image,
        inlineToolbar: true,
        config: {
          types: 'image/*, video/mp4',
          endpoints: {
            byFile: encodeURI('http://localhost:2208/api/file?from=http://localhost:2208'),
            byUrl: '/api/transport/fetch',
          },
          field: 'file',
          additionalRequestData: {
            map: JSON.stringify({
              url: 'file:url',
              size: 'file:size',
              mimetype: 'file:mime',
            }),
          },
        },
      },

      linkTool: {
        class: LinkTool,
        config: {
          endpoint: '/api/fetchUrl',
        },
      },

      code: {
        class: CodeTool,
        shortcut: 'CMD+SHIFT+D',
      },

      list: {
        class: List,
        inlineToolbar: true,
      },

      delimiter: Delimiter,

      table: {
        class: Table,
        inlineToolbar: true,
      },

      warning: {
        class: Warning,
        inlineToolbar: true,
      },

      checklist: {
        class: Checklist,
        inlineToolbar: true,
      },

      /**
       * Inline Tools
       */
      inlineCode: {
        class: InlineCode,
        shortcut: 'CMD+SHIFT+C',
      },

      marker: {
        class: Marker,
        shortcut: 'CMD+SHIFT+M',
      },

      raw: RawTool,

      embed: Embed,
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

  getJson(): void {
    this.editor.save().then((res) => {
      this.code = JSON.stringify(res, null, '\t');
    });
  }

  readOnly(): void {
    this.editor.readOnly.toggle();
  }
}
