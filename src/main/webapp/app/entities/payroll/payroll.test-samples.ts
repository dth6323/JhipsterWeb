import { IPayroll, NewPayroll } from './payroll.model';

export const sampleWithRequiredData: IPayroll = {
  id: 24327,
  salary: 17720,
  workDay: 12291,
};

export const sampleWithPartialData: IPayroll = {
  id: 10559,
  salary: 19897,
  workDay: 23450,
};

export const sampleWithFullData: IPayroll = {
  id: 6383,
  salary: 31015,
  workDay: 14874,
};

export const sampleWithNewData: NewPayroll = {
  salary: 9559,
  workDay: 23240,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
