import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RightPannelComponent } from './right-pannel.component';

describe('RightPannelComponent', () => {
  let component: RightPannelComponent;
  let fixture: ComponentFixture<RightPannelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RightPannelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RightPannelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
