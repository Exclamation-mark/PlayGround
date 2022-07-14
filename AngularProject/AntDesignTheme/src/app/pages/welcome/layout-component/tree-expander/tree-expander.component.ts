import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {TreeNode} from '@circlon/angular-tree-component/lib/defs/api';

@Component({
  selector: 'app-tree-expander',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './tree-expander.component.html',
  styleUrls: ['./tree-expander.component.less']
})
export class TreeExpanderComponent implements OnInit {
  @Input() node: TreeNode;
  constructor() { }

  ngOnInit(): void {
  }

}
