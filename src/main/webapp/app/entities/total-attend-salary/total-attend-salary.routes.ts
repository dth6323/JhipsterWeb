import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import TotalAttendSalaryResolve from './route/total-attend-salary-routing-resolve.service';

const totalAttendSalaryRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/total-attend-salary.component').then(m => m.TotalAttendSalaryComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/total-attend-salary-detail.component').then(m => m.TotalAttendSalaryDetailComponent),
    resolve: {
      totalAttendSalary: TotalAttendSalaryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/total-attend-salary-update.component').then(m => m.TotalAttendSalaryUpdateComponent),
    resolve: {
      totalAttendSalary: TotalAttendSalaryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/total-attend-salary-update.component').then(m => m.TotalAttendSalaryUpdateComponent),
    resolve: {
      totalAttendSalary: TotalAttendSalaryResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default totalAttendSalaryRoute;
