import { Component, inject, input, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { ISalaryDistribute } from '../salary-distribute.model';
import { SalaryDistributeService } from '../service/salary-distribute.service';
import HasAnyAuthorityDirective from '../../../shared/auth/has-any-authority.directive';
import { SortByDirective, SortDirective } from '../../../shared/sort';
import { SalaryDistribute } from '../employee-detail.model';
import { PayrollService } from '../../payroll/service/payroll.service';
import { NewPayroll } from '../../payroll/payroll.model';

@Component({
  standalone: true,
  selector: 'jhi-salary-distribute-detail',
  templateUrl: './salary-distribute-detail.component.html',
  imports: [
    SharedModule,
    RouterModule,
    DurationPipe,
    FormatMediumDatetimePipe,
    FormatMediumDatePipe,
    HasAnyAuthorityDirective,
    SortByDirective,
    SortDirective,
  ],
})
export class SalaryDistributeDetailComponent implements OnInit {
  salaryDistribute = input<ISalaryDistribute | null>(null);
  id: string | null = null;
  protected payroll: NewPayroll | null = null;
  protected route: ActivatedRoute = inject(ActivatedRoute);
  protected employeeDetail?: SalaryDistribute[] = [];
  protected salaryDistributeService = inject(SalaryDistributeService);
  protected payrollService = inject(PayrollService);
  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.showemployee();
  }
  caculate(): void {
    const value = this.salaryDistribute();
    if (value) {
      // eslint-disable-next-line no-console
      console.log(value.id);
    }
  }
  showemployee(): any {
    if (this.id != null)
      return this.salaryDistributeService.showemployee(this.id).subscribe({
        next: data => {
          this.employeeDetail = data;
        },
      });
  }

  previousState(): void {
    window.history.back();
  }
}
