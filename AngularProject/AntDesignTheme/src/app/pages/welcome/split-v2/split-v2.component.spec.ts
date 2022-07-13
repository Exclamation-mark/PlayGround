import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SplitV2Component } from './split-v2.component';

describe('SplitV2Component', () => {
  let component: SplitV2Component;
  let fixture: ComponentFixture<SplitV2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SplitV2Component ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SplitV2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
