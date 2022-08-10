import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageShownComponent } from './image-shown.component';

describe('ImageShownComponent', () => {
  let component: ImageShownComponent;
  let fixture: ComponentFixture<ImageShownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImageShownComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageShownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
