import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { ITotalAttendSalary } from '../total-attend-salary.model';
import { TotalAttendSalaryService } from '../service/total-attend-salary.service';

@Component({
  standalone: true,
  templateUrl: './total-attend-salary-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class TotalAttendSalaryDeleteDialogComponent {
  totalAttendSalary?: ITotalAttendSalary;

  protected totalAttendSalaryService = inject(TotalAttendSalaryService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.totalAttendSalaryService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
