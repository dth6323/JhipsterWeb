import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IEmployee } from 'app/entities/employee/employee.model';
import { EmployeeService } from 'app/entities/employee/service/employee.service';
import { ITotalAttendSalary } from '../total-attend-salary.model';
import { TotalAttendSalaryService } from '../service/total-attend-salary.service';
import { TotalAttendSalaryFormGroup, TotalAttendSalaryFormService } from './total-attend-salary-form.service';

@Component({
  standalone: true,
  selector: 'jhi-total-attend-salary-update',
  templateUrl: './total-attend-salary-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class TotalAttendSalaryUpdateComponent implements OnInit {
  isSaving = false;
  totalAttendSalary: ITotalAttendSalary | null = null;

  employeesSharedCollection: IEmployee[] = [];

  protected totalAttendSalaryService = inject(TotalAttendSalaryService);
  protected totalAttendSalaryFormService = inject(TotalAttendSalaryFormService);
  protected employeeService = inject(EmployeeService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: TotalAttendSalaryFormGroup = this.totalAttendSalaryFormService.createTotalAttendSalaryFormGroup();

  compareEmployee = (o1: IEmployee | null, o2: IEmployee | null): boolean => this.employeeService.compareEmployee(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ totalAttendSalary }) => {
      this.totalAttendSalary = totalAttendSalary;
      if (totalAttendSalary) {
        this.updateForm(totalAttendSalary);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const totalAttendSalary = this.totalAttendSalaryFormService.getTotalAttendSalary(this.editForm);
    if (totalAttendSalary.id !== null) {
      this.subscribeToSaveResponse(this.totalAttendSalaryService.update(totalAttendSalary));
    } else {
      this.subscribeToSaveResponse(this.totalAttendSalaryService.create(totalAttendSalary));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITotalAttendSalary>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(totalAttendSalary: ITotalAttendSalary): void {
    this.totalAttendSalary = totalAttendSalary;
    this.totalAttendSalaryFormService.resetForm(this.editForm, totalAttendSalary);

    this.employeesSharedCollection = this.employeeService.addEmployeeToCollectionIfMissing<IEmployee>(
      this.employeesSharedCollection,
      totalAttendSalary.employee,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.employeeService
      .query()
      .pipe(map((res: HttpResponse<IEmployee[]>) => res.body ?? []))
      .pipe(
        map((employees: IEmployee[]) =>
          this.employeeService.addEmployeeToCollectionIfMissing<IEmployee>(employees, this.totalAttendSalary?.employee),
        ),
      )
      .subscribe((employees: IEmployee[]) => (this.employeesSharedCollection = employees));
  }
}
