import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ITotalAttendSalary, NewTotalAttendSalary } from '../total-attend-salary.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITotalAttendSalary for edit and NewTotalAttendSalaryFormGroupInput for create.
 */
type TotalAttendSalaryFormGroupInput = ITotalAttendSalary | PartialWithRequiredKeyOf<NewTotalAttendSalary>;

type TotalAttendSalaryFormDefaults = Pick<NewTotalAttendSalary, 'id'>;

type TotalAttendSalaryFormGroupContent = {
  id: FormControl<ITotalAttendSalary['id'] | NewTotalAttendSalary['id']>;
  totalAttend: FormControl<ITotalAttendSalary['totalAttend']>;
  totalSalary: FormControl<ITotalAttendSalary['totalSalary']>;
  employee: FormControl<ITotalAttendSalary['employee']>;
};

export type TotalAttendSalaryFormGroup = FormGroup<TotalAttendSalaryFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TotalAttendSalaryFormService {
  createTotalAttendSalaryFormGroup(totalAttendSalary: TotalAttendSalaryFormGroupInput = { id: null }): TotalAttendSalaryFormGroup {
    const totalAttendSalaryRawValue = {
      ...this.getFormDefaults(),
      ...totalAttendSalary,
    };
    return new FormGroup<TotalAttendSalaryFormGroupContent>({
      id: new FormControl(
        { value: totalAttendSalaryRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      totalAttend: new FormControl(totalAttendSalaryRawValue.totalAttend),
      totalSalary: new FormControl(totalAttendSalaryRawValue.totalSalary),
      employee: new FormControl(totalAttendSalaryRawValue.employee),
    });
  }

  getTotalAttendSalary(form: TotalAttendSalaryFormGroup): ITotalAttendSalary | NewTotalAttendSalary {
    return form.getRawValue() as ITotalAttendSalary | NewTotalAttendSalary;
  }

  resetForm(form: TotalAttendSalaryFormGroup, totalAttendSalary: TotalAttendSalaryFormGroupInput): void {
    const totalAttendSalaryRawValue = { ...this.getFormDefaults(), ...totalAttendSalary };
    form.reset(
      {
        ...totalAttendSalaryRawValue,
        id: { value: totalAttendSalaryRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): TotalAttendSalaryFormDefaults {
    return {
      id: null,
    };
  }
}
