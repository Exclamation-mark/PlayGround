import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerticalSplitV2Component } from './vertical-split-v2.component';

describe('VerticalSplitV2Component', () => {
  let component: VerticalSplitV2Component;
  let fixture: ComponentFixture<VerticalSplitV2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerticalSplitV2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerticalSplitV2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
