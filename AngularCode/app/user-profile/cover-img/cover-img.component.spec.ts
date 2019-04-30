import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CoverImgComponent } from './cover-img.component';

describe('CoverImgComponent', () => {
  let component: CoverImgComponent;
  let fixture: ComponentFixture<CoverImgComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CoverImgComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CoverImgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
