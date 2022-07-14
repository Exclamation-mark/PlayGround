import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TreeExpanderComponent } from './tree-expander.component';

describe('TreeExpanderComponent', () => {
  let component: TreeExpanderComponent;
  let fixture: ComponentFixture<TreeExpanderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TreeExpanderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TreeExpanderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
