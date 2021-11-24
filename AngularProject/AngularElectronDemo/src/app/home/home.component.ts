import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {ElectronService} from "../core/services";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  editorOptions = {theme: 'vs-light', language: 'javascript'};
  code = 'function x() {\nconsole.log("Hello world!");\n}';

  constructor(private router: Router, private electronService: ElectronService) {
  }

  ngOnInit(): void {
    console.log('HomeComponent INIT');
  }

  reload() {
    this.electronService.reload();
  }
}
