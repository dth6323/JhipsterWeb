import { IEmployee } from 'app/entities/employee/employee.model';

export interface ITotalAttendSalary {
  id: number;
  totalAttend?: number | null;
  totalSalary?: number | null;
  employee?: IEmployee | null;
}

export type NewTotalAttendSalary = Omit<ITotalAttendSalary, 'id'> & { id: null };
