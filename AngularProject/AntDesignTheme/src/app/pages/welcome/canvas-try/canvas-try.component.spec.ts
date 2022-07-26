import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CanvasTryComponent } from './canvas-try.component';

describe('CanvasTryComponent', () => {
  let component: CanvasTryComponent;
  let fixture: ComponentFixture<CanvasTryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CanvasTryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CanvasTryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
