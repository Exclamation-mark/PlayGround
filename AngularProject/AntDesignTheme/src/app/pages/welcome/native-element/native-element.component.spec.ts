import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NativeElementComponent } from './native-element.component';

describe('NativeElementComponent', () => {
  let component: NativeElementComponent;
  let fixture: ComponentFixture<NativeElementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NativeElementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NativeElementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
