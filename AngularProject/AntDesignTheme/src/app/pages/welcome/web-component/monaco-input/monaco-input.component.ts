import {Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output, SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-monaco-input',
  templateUrl: './monaco-input.component.html',
  styleUrls: ['./monaco-input.component.less']
})
export class MonacoInputComponent implements OnInit, OnChanges, OnDestroy {
  @Output() c = new EventEmitter();
  @Input() iro = false;
  @Input() code = '';
  @Input() get getData(): string {
    return this.code;
  }

  isFull = false;
  editorOptions = {theme: 'vs-light', language: 'json', readOnly: false};

  constructor() {
  }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log('zzq see changed', changes);
    if (changes.iro) {
      this.editorOptions = {...this.editorOptions, readOnly: this.iro};
    }
  }

  handleEvent(event: any): any {
    console.log('get event in content', event);
    if (event.keyCode === 13) {
      event.cancelBubble = true;
    }
  }

  ngOnDestroy(): void {
    console.log('monin destroy');
  }

  fullScreen(): void {
    console.log('click');
    this.isFull = !this.isFull;
  }
}
