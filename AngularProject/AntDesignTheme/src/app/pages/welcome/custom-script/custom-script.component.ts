import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-custom-script',
  templateUrl: './custom-script.component.html',
  styleUrls: ['./custom-script.component.less']
})
export class CustomScriptComponent implements OnInit {
  isRunning = false;
  constructor() { }

  ngOnInit(): void {
  }

  onStart(): void {
    this.isRunning = true;
  }

  onStop(): void {
    this.isRunning = false;
  }
}
