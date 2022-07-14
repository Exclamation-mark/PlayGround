import {Component, Input, OnInit, TemplateRef, ViewEncapsulation} from '@angular/core';
import {TreeNode} from '@circlon/angular-tree-component/lib/defs/api';

@Component({
  selector: 'app-tree-content',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './tree-content.component.html',
  styleUrls: ['./tree-content.component.less']
})
export class TreeContentComponent implements OnInit {
  @Input() node: TreeNode;
  constructor() { }

  ngOnInit(): void {
  }

}
