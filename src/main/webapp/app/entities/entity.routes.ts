import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'Authorities' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'attendance',
    data: { pageTitle: 'Attendances' },
    loadChildren: () => import('./attendance/attendance.routes'),
  },
  {
    path: 'contract',
    data: { pageTitle: 'Contracts' },
    loadChildren: () => import('./contract/contract.routes'),
  },
  {
    path: 'department',
    data: { pageTitle: 'Departments' },
    loadChildren: () => import('./department/department.routes'),
  },
  {
    path: 'employee',
    data: { pageTitle: 'Employees' },
    loadChildren: () => import('./employee/employee.routes'),
  },
  {
    path: 'payroll',
    data: { pageTitle: 'Payrolls' },
    loadChildren: () => import('./payroll/payroll.routes'),
  },
  {
    path: 'salary-distribute',
    data: { pageTitle: 'SalaryDistributes' },
    loadChildren: () => import('./salary-distribute/salary-distribute.routes'),
  },
  {
    path: 'wage',
    data: { pageTitle: 'Wages' },
    loadChildren: () => import('./wage/wage.routes'),
  },
  {
    path: 'total-attend-salary',
    data: { pageTitle: 'TotalAttendSalaries' },
    loadChildren: () => import('./total-attend-salary/total-attend-salary.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
