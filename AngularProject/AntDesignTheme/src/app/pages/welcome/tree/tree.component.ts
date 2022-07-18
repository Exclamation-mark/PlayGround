import {Component, OnInit, ViewChild} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd/message';
import {TreeNode} from '@circlon/angular-tree-component/lib/defs/api';
import {ActivatedRoute, Router} from '@angular/router';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';
import {TreeComponent as TreeEComponent} from '@circlon/angular-tree-component';
import {NgProgressComponent} from 'ngx-progressbar';

@Component({
  selector: 'app-tree',
  templateUrl: './tree.component.html',
  styleUrls: ['./tree.component.less']
})
export class TreeComponent implements OnInit {
  subject = new Subject<string>();
  @ViewChild('tree', {static: false}) tree: TreeEComponent;
  @ViewChild('progressBar', {static: false}) progressComponent: NgProgressComponent;
  nodes = [
    {
      name: '海外仓账单',
      children: [
        { name: 'abcde' },
        { name: 'fghi' },
        { name: 'child' },
        { name: 'gbhajasd' }
      ]
    },
    {
      name: 'root2',
      children: [
        { name: 'long. long. long. long. long. long. long. long. long. long. long. long. long. long. long. long. long. ' },
        { name: 'child2.1' },
        { name: 'child2.2' }
      ]
    },
    { name: 'root4', children: [] }
  ];
  searchText = '';
  isSpinnerShowing = false;
  constructor(
    private router: Router,
    private message: NzMessageService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    for (let i = 0; i < 50; i++) {
      this.nodes[2].children.push({name: i + '_123456789'});
    }
    this.subject.pipe(debounceTime(500)).subscribe(data => {
      this.tree.treeModel.filterNodes(this.searchText);
    });
  }

  onEvent(event: any): void  {
    this.message.success('on event');
    // event.node.toggleExpanded();
    console.log('zzq see event', event);
  }

  tryExpand(node: TreeNode): void  {
    this.message.info('click');
    this.router.navigate(['.'], {
      relativeTo: this.route, queryParams: {
        id: node.displayField
      }
    });
    if (node.hasChildren) {
      if (!node.treeModel.isExpanded(node)) {
        console.log('do expand');
        node.toggleExpanded();
      }
    }
    if (this.isSpinnerShowing) {
      setTimeout(() => {
        this.isSpinnerShowing = false;
        this.progressComponent.complete();
      }, 3000);
    }else {
      this.isSpinnerShowing = true;
      this.progressComponent.start();
      setTimeout(() => {
        this.isSpinnerShowing = false;
        this.progressComponent.complete();
      }, 3000);
    }
    console.log('zzq see click try expand ', node);
  }

  tryCloseExpand(node: TreeNode): void  {
    if (node.isExpanded) {
      node.toggleExpanded();
    }
  }

  mouseComeIn(node: TreeNode): void  {
    node.data.isIn = true;
  }

  mouseComeOut(node: TreeNode): void  {
    node.data.isIn = false;
  }

  showMsg(s: string): void  {
    this.message.success('click: ' + s);
  }

  onFilter(event: any): void  {
    console.log('zzq see change', this.searchText, event);
    this.subject.next(event);
  }

  onStarted(): void {
  }

  onCompleted(): void  {
  }
}
