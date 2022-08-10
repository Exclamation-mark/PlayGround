import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-web-component',
  templateUrl: './web-component.component.html',
  styleUrls: ['./web-component.component.less']
})
export class WebComponentComponent implements OnInit {
  v = false;
  constructor() { }

  ngOnInit(): void {
  }

  run(): void {
    this.v = !this.v;
  }
}
