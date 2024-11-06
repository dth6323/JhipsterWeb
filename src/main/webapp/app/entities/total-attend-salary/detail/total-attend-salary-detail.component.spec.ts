import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { TotalAttendSalaryDetailComponent } from './total-attend-salary-detail.component';

describe('TotalAttendSalary Management Detail Component', () => {
  let comp: TotalAttendSalaryDetailComponent;
  let fixture: ComponentFixture<TotalAttendSalaryDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TotalAttendSalaryDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./total-attend-salary-detail.component').then(m => m.TotalAttendSalaryDetailComponent),
              resolve: { totalAttendSalary: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(TotalAttendSalaryDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TotalAttendSalaryDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load totalAttendSalary on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', TotalAttendSalaryDetailComponent);

      // THEN
      expect(instance.totalAttendSalary()).toEqual(expect.objectContaining({ id: 123 }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
