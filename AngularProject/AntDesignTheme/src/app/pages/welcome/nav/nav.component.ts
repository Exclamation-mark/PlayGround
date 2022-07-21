import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {NzMessageService} from 'ng-zorro-antd/message';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.less']
})
export class NavComponent implements OnInit {
  tabs = ['Tab 1', 'Tab 2'];
  selectedIndex = 0;
  isIn = false;
  mode = false;
  v: any;
  id = 0;

  constructor(
    private router: Router,
    private message: NzMessageService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.message.success('ngOnInit');
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

  showMsg() {
    this.message.success('hahaha');
  }

  addParameter() {
    this.message.success(window.location.href);
    this.id++;
    this.router.navigate(['.'], {
      relativeTo: this.route, queryParams: {
        id: this.id,
      }
    });
  }

  onInputValueChange(data: any): void {
    this.v = data.target.value;
  }

  fileChanged(data: any): void {
    if (this.mode) {
      console.log('file full path: ' + data.target.value);
      console.log('file name:', data.target.files[0].name);
      this.v = data.target.value;
    }
  }

  changeType(data: any): void {
    if (data && !this.v) {
      this.v = 'select file';
    }
  }
}
