import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
const asRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/as.component').then(m => m.AsComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default asRoute;
