import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestS3Component } from './test-s3.component';

describe('TestS3Component', () => {
  let component: TestS3Component;
  let fixture: ComponentFixture<TestS3Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestS3Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestS3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
