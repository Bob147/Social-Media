import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CredentialPageComponent } from './credential-page.component';

describe('CredentialPageComponent', () => {
  let component: CredentialPageComponent;
  let fixture: ComponentFixture<CredentialPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CredentialPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CredentialPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
