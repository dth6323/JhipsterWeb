import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITotalAttendSalary, NewTotalAttendSalary } from '../total-attend-salary.model';

export type PartialUpdateTotalAttendSalary = Partial<ITotalAttendSalary> & Pick<ITotalAttendSalary, 'id'>;

export type EntityResponseType = HttpResponse<ITotalAttendSalary>;
export type EntityArrayResponseType = HttpResponse<ITotalAttendSalary[]>;

@Injectable({ providedIn: 'root' })
export class TotalAttendSalaryService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/total-attend-salaries');

  create(totalAttendSalary: NewTotalAttendSalary): Observable<EntityResponseType> {
    return this.http.post<ITotalAttendSalary>(this.resourceUrl, totalAttendSalary, { observe: 'response' });
  }

  update(totalAttendSalary: ITotalAttendSalary): Observable<EntityResponseType> {
    return this.http.put<ITotalAttendSalary>(
      `${this.resourceUrl}/${this.getTotalAttendSalaryIdentifier(totalAttendSalary)}`,
      totalAttendSalary,
      { observe: 'response' },
    );
  }

  partialUpdate(totalAttendSalary: PartialUpdateTotalAttendSalary): Observable<EntityResponseType> {
    return this.http.patch<ITotalAttendSalary>(
      `${this.resourceUrl}/${this.getTotalAttendSalaryIdentifier(totalAttendSalary)}`,
      totalAttendSalary,
      { observe: 'response' },
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITotalAttendSalary>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITotalAttendSalary[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTotalAttendSalaryIdentifier(totalAttendSalary: Pick<ITotalAttendSalary, 'id'>): number {
    return totalAttendSalary.id;
  }

  compareTotalAttendSalary(o1: Pick<ITotalAttendSalary, 'id'> | null, o2: Pick<ITotalAttendSalary, 'id'> | null): boolean {
    return o1 && o2 ? this.getTotalAttendSalaryIdentifier(o1) === this.getTotalAttendSalaryIdentifier(o2) : o1 === o2;
  }

  addTotalAttendSalaryToCollectionIfMissing<Type extends Pick<ITotalAttendSalary, 'id'>>(
    totalAttendSalaryCollection: Type[],
    ...totalAttendSalariesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const totalAttendSalaries: Type[] = totalAttendSalariesToCheck.filter(isPresent);
    if (totalAttendSalaries.length > 0) {
      const totalAttendSalaryCollectionIdentifiers = totalAttendSalaryCollection.map(totalAttendSalaryItem =>
        this.getTotalAttendSalaryIdentifier(totalAttendSalaryItem),
      );
      const totalAttendSalariesToAdd = totalAttendSalaries.filter(totalAttendSalaryItem => {
        const totalAttendSalaryIdentifier = this.getTotalAttendSalaryIdentifier(totalAttendSalaryItem);
        if (totalAttendSalaryCollectionIdentifiers.includes(totalAttendSalaryIdentifier)) {
          return false;
        }
        totalAttendSalaryCollectionIdentifiers.push(totalAttendSalaryIdentifier);
        return true;
      });
      return [...totalAttendSalariesToAdd, ...totalAttendSalaryCollection];
    }
    return totalAttendSalaryCollection;
  }
}
