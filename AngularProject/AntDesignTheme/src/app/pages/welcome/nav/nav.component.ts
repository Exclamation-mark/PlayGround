import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {NzMessageService} from 'ng-zorro-antd/message';
import {HttpClient, HttpHeaders} from '@angular/common/http';

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
    private route: ActivatedRoute,
    private httpClient: HttpClient
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
      const formData = new FormData();
      formData.append('adasd', 'asdad');
      formData.append('zzq', data.target.files[0]);
      console.log('zzq see form data target', data);
      console.log('zzq see form data', formData);
      console.log('file full path: ' + data.target.value);
      console.log('file name:', data.target.files[0].name);
      console.log('file name file json:', JSON.stringify(data.target.files[0]));
      console.log('file name encode:', URL.createObjectURL(data.target.files[0]));
      const input = document.getElementById('inputTag');
      console.log('zzq see element:', input);
      localStorage.setItem('filehaha', data.target.files[0]);
      if (window.FileReader) {
        const reader = new FileReader();
        reader.onload = () => {
          const result = reader.result;
          console.log('zzq see saved file base 64', result);
          localStorage.filehaha = result;
        };
        reader.readAsText(data.target.files[0]);
      } else {
        console.log('你的浏览器不支持读取文件');
      }
      this.v = data.target.value;
    }
  }

  onUpload(): void {
    const h = new HttpHeaders();
    h.append('Content-Type', 'multipart/form-data');
    h.append('Accept', 'application/json');
    const options = {headers: h};
    const file = this.getFile();
    const formData = new FormData();
    formData.append('uploadFile', file, 'xasdsd');
    this.httpClient.post('http://localhost:8080/api/user', formData, options)
      .subscribe((res) => {
        console.log('zzq see res', res);
      });
  }

  onUpload2(): void {
  }

  private getFile(): File {
    const base64 = localStorage.filehaha;
    const file = new File([base64], 'file name here', {type: 'data:application/octet-stream'});
    return file;
  }

  changeType(data: any): void {
    if (data && !this.v) {
      this.v = 'select file';
    }
  }
}
