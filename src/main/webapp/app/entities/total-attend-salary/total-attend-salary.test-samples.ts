import { ITotalAttendSalary, NewTotalAttendSalary } from './total-attend-salary.model';

export const sampleWithRequiredData: ITotalAttendSalary = {
  id: 2873,
};

export const sampleWithPartialData: ITotalAttendSalary = {
  id: 18255,
  totalAttend: 13178,
};

export const sampleWithFullData: ITotalAttendSalary = {
  id: 3616,
  totalAttend: 19949,
  totalSalary: 8477,
};

export const sampleWithNewData: NewTotalAttendSalary = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
