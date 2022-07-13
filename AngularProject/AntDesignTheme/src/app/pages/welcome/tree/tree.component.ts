import { Component, OnInit } from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';

@Component({
  selector: 'app-tree',
  templateUrl: './tree.component.html',
  styleUrls: ['./tree.component.less']
})
export class TreeComponent implements OnInit {
  nodes = [
    {
      name: 'root1',
      children: [
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child1' },
        { name: 'child2' }
      ]
    },
    {
      name: 'root2',
      children: [
        { name: 'child2.1', children: [] },
        { name: 'child2.2', children: [
            {name: 'grandchild2.2.1'}
          ] }
      ]
    },
    { name: 'root3' },
    { name: 'root4', children: [] },
    { name: 'root5', children: null }
  ];
  constructor(private message: NzMessageService) { }

  ngOnInit(): void {
  }

  onEvent(event: any) {
    this.message.success('on event');
    event.node.toggleExpanded();
    console.log('zzq see event', event);
  }
}
