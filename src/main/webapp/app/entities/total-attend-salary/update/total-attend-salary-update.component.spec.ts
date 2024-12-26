import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IEmployee } from 'app/entities/employee/employee.model';
import { EmployeeService } from 'app/entities/employee/service/employee.service';
import { TotalAttendSalaryService } from '../service/total-attend-salary.service';
import { ITotalAttendSalary } from '../total-attend-salary.model';
import { TotalAttendSalaryFormService } from './total-attend-salary-form.service';

import { TotalAttendSalaryUpdateComponent } from './total-attend-salary-update.component';

describe('TotalAttendSalary Management Update Component', () => {
  let comp: TotalAttendSalaryUpdateComponent;
  let fixture: ComponentFixture<TotalAttendSalaryUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let totalAttendSalaryFormService: TotalAttendSalaryFormService;
  let totalAttendSalaryService: TotalAttendSalaryService;
  let employeeService: EmployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [TotalAttendSalaryUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(TotalAttendSalaryUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TotalAttendSalaryUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    totalAttendSalaryFormService = TestBed.inject(TotalAttendSalaryFormService);
    totalAttendSalaryService = TestBed.inject(TotalAttendSalaryService);
    employeeService = TestBed.inject(EmployeeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Employee query and add missing value', () => {
      const totalAttendSalary: ITotalAttendSalary = { id: 456 };
      const employee: IEmployee = { id: 27620 };
      totalAttendSalary.employee = employee;

      const employeeCollection: IEmployee[] = [{ id: 27180 }];
      jest.spyOn(employeeService, 'query').mockReturnValue(of(new HttpResponse({ body: employeeCollection })));
      const additionalEmployees = [employee];
      const expectedCollection: IEmployee[] = [...additionalEmployees, ...employeeCollection];
      jest.spyOn(employeeService, 'addEmployeeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ totalAttendSalary });
      comp.ngOnInit();

      expect(employeeService.query).toHaveBeenCalled();
      expect(employeeService.addEmployeeToCollectionIfMissing).toHaveBeenCalledWith(
        employeeCollection,
        ...additionalEmployees.map(expect.objectContaining),
      );
      expect(comp.employeesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const totalAttendSalary: ITotalAttendSalary = { id: 456 };
      const employee: IEmployee = { id: 26895 };
      totalAttendSalary.employee = employee;

      activatedRoute.data = of({ totalAttendSalary });
      comp.ngOnInit();

      expect(comp.employeesSharedCollection).toContain(employee);
      expect(comp.totalAttendSalary).toEqual(totalAttendSalary);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITotalAttendSalary>>();
      const totalAttendSalary = { id: 123 };
      jest.spyOn(totalAttendSalaryFormService, 'getTotalAttendSalary').mockReturnValue(totalAttendSalary);
      jest.spyOn(totalAttendSalaryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ totalAttendSalary });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: totalAttendSalary }));
      saveSubject.complete();

      // THEN
      expect(totalAttendSalaryFormService.getTotalAttendSalary).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(totalAttendSalaryService.update).toHaveBeenCalledWith(expect.objectContaining(totalAttendSalary));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITotalAttendSalary>>();
      const totalAttendSalary = { id: 123 };
      jest.spyOn(totalAttendSalaryFormService, 'getTotalAttendSalary').mockReturnValue({ id: null });
      jest.spyOn(totalAttendSalaryService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ totalAttendSalary: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: totalAttendSalary }));
      saveSubject.complete();

      // THEN
      expect(totalAttendSalaryFormService.getTotalAttendSalary).toHaveBeenCalled();
      expect(totalAttendSalaryService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITotalAttendSalary>>();
      const totalAttendSalary = { id: 123 };
      jest.spyOn(totalAttendSalaryService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ totalAttendSalary });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(totalAttendSalaryService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareEmployee', () => {
      it('Should forward to employeeService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(employeeService, 'compareEmployee');
        comp.compareEmployee(entity, entity2);
        expect(employeeService.compareEmployee).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
