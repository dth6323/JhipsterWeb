import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { ITotalAttendSalary } from '../total-attend-salary.model';

@Component({
  standalone: true,
  selector: 'jhi-total-attend-salary-detail',
  templateUrl: './total-attend-salary-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class TotalAttendSalaryDetailComponent {
  totalAttendSalary = input<ITotalAttendSalary | null>(null);

  previousState(): void {
    window.history.back();
  }
}
