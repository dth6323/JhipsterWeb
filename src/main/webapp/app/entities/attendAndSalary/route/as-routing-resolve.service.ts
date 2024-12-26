import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { Ias } from '../as.model';
import { AsService } from '../service/as.service';

const asResolve = (route: ActivatedRouteSnapshot): Observable<null | Ias> => {
  const id = route.params.id;
  if (id) {
    return inject(AsService)
      .find(id)
      .pipe(
        mergeMap((as: HttpResponse<Ias>) => {
          if (as.body) {
            return of(as.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default asResolve;
