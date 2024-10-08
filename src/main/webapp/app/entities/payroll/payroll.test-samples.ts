import { IPayroll, NewPayroll } from './payroll.model';

export const sampleWithRequiredData: IPayroll = {
  id: 21084,
  salary: 11048,
  workDay: 24158,
};

export const sampleWithPartialData: IPayroll = {
  id: 32080,
  salary: 9506,
  workDay: 6367,
};

export const sampleWithFullData: IPayroll = {
  id: 10103,
  salary: 21290,
  workDay: 13958,
};

export const sampleWithNewData: NewPayroll = {
  salary: 32041,
  workDay: 7191,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
