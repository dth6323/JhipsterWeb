import dayjs from 'dayjs/esm';

import { ISalaryDistribute, NewSalaryDistribute } from './salary-distribute.model';

export const sampleWithRequiredData: ISalaryDistribute = {
  id: 31357,
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-08'),
  workDay: 30838,
  typeOfSalary: 'yet',
};

export const sampleWithPartialData: ISalaryDistribute = {
  id: 25177,
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-07'),
  workDay: 24060,
  typeOfSalary: 'ew near',
};

export const sampleWithFullData: ISalaryDistribute = {
  id: 14417,
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-07'),
  workDay: 3255,
  typeOfSalary: 'for rudely',
};

export const sampleWithNewData: NewSalaryDistribute = {
  startDate: dayjs('2024-10-08'),
  endDate: dayjs('2024-10-07'),
  workDay: 2995,
  typeOfSalary: 'grubby fledgling legend',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
