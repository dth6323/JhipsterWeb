import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITotalAttendSalary } from '../total-attend-salary.model';
import { TotalAttendSalaryService } from '../service/total-attend-salary.service';

const totalAttendSalaryResolve = (route: ActivatedRouteSnapshot): Observable<null | ITotalAttendSalary> => {
  const id = route.params.id;
  if (id) {
    return inject(TotalAttendSalaryService)
      .find(id)
      .pipe(
        mergeMap((totalAttendSalary: HttpResponse<ITotalAttendSalary>) => {
          if (totalAttendSalary.body) {
            return of(totalAttendSalary.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default totalAttendSalaryResolve;
