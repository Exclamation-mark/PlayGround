import {Component, Injector, OnInit} from '@angular/core';
import {createCustomElement} from '@angular/elements';
import {MonacoInputComponent} from './monaco-input/monaco-input.component';

@Component({
  selector: 'app-web-component',
  templateUrl: './web-component.component.html',
  styleUrls: ['./web-component.component.less']
})
export class WebComponentComponent implements OnInit {
  v = false;
  constructor() { }

  ngOnInit(): void {
    setTimeout(() => {
      const monin = document.createElement('monaco-input-component');
      const element = document.getElementById('ccc');
      // @ts-ignore
      element.appendChild(monin);
    }, 3000);
  }

  run(): void {
    this.v = !this.v;
  }
}
