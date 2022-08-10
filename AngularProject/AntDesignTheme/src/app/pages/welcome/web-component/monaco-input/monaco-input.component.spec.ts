import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonacoInputComponent } from './monaco-input.component';

describe('MonacoInputComponent', () => {
  let component: MonacoInputComponent;
  let fixture: ComponentFixture<MonacoInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonacoInputComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MonacoInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
