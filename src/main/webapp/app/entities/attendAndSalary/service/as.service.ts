import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { Ias, Newas } from '../as.model';

export type EntityResponseType = HttpResponse<Ias>;
export type EntityArrayResponseType = HttpResponse<Ias[]>;

@Injectable({ providedIn: 'root' })
export class AsService {
  protected http = inject(HttpClient);
  protected applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/payrolls/attendance-report');
  find(id: number): Observable<EntityResponseType> {
    return this.http.get<Ias>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<Ias[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  getAsIdentifier(as: Pick<Ias, 'id'>): number {
    return as.id;
  }
}
