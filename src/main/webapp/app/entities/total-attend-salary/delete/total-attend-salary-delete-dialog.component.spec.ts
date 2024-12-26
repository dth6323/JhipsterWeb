jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { TotalAttendSalaryService } from '../service/total-attend-salary.service';

import { TotalAttendSalaryDeleteDialogComponent } from './total-attend-salary-delete-dialog.component';

describe('TotalAttendSalary Management Delete Component', () => {
  let comp: TotalAttendSalaryDeleteDialogComponent;
  let fixture: ComponentFixture<TotalAttendSalaryDeleteDialogComponent>;
  let service: TotalAttendSalaryService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [TotalAttendSalaryDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(TotalAttendSalaryDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TotalAttendSalaryDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TotalAttendSalaryService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
