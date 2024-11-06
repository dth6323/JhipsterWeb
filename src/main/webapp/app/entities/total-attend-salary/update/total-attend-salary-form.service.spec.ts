import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../total-attend-salary.test-samples';

import { TotalAttendSalaryFormService } from './total-attend-salary-form.service';

describe('TotalAttendSalary Form Service', () => {
  let service: TotalAttendSalaryFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TotalAttendSalaryFormService);
  });

  describe('Service methods', () => {
    describe('createTotalAttendSalaryFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTotalAttendSalaryFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            totalAttend: expect.any(Object),
            totalSalary: expect.any(Object),
            employee: expect.any(Object),
          }),
        );
      });

      it('passing ITotalAttendSalary should create a new form with FormGroup', () => {
        const formGroup = service.createTotalAttendSalaryFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            totalAttend: expect.any(Object),
            totalSalary: expect.any(Object),
            employee: expect.any(Object),
          }),
        );
      });
    });

    describe('getTotalAttendSalary', () => {
      it('should return NewTotalAttendSalary for default TotalAttendSalary initial value', () => {
        const formGroup = service.createTotalAttendSalaryFormGroup(sampleWithNewData);

        const totalAttendSalary = service.getTotalAttendSalary(formGroup) as any;

        expect(totalAttendSalary).toMatchObject(sampleWithNewData);
      });

      it('should return NewTotalAttendSalary for empty TotalAttendSalary initial value', () => {
        const formGroup = service.createTotalAttendSalaryFormGroup();

        const totalAttendSalary = service.getTotalAttendSalary(formGroup) as any;

        expect(totalAttendSalary).toMatchObject({});
      });

      it('should return ITotalAttendSalary', () => {
        const formGroup = service.createTotalAttendSalaryFormGroup(sampleWithRequiredData);

        const totalAttendSalary = service.getTotalAttendSalary(formGroup) as any;

        expect(totalAttendSalary).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITotalAttendSalary should not enable id FormControl', () => {
        const formGroup = service.createTotalAttendSalaryFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTotalAttendSalary should disable id FormControl', () => {
        const formGroup = service.createTotalAttendSalaryFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
