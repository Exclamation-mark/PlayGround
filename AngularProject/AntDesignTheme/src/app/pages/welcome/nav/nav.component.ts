import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.less']
})
export class NavComponent implements OnInit {
  tabs = ['Tab 1', 'Tab 2'];
  selectedIndex = 0;
  isIn = false;
  constructor() { }

  ngOnInit(): void {
  }

  closeTab({ index }: { index: number }): void {
    this.tabs.splice(index, 1);
  }

  newTab(): void {
    this.tabs.push('New Tab( ' + this.tabs.length + ' )');
    this.selectedIndex = this.tabs.length;
  }

  mouseComeIn($event: MouseEvent) {
    this.isIn = true;
  }

  mouseComeOut($event: MouseEvent) {
    this.isIn = false;
  }
}
