import dayjs from 'dayjs/esm';

import { ISalaryDistribute, NewSalaryDistribute } from './salary-distribute.model';

export const sampleWithRequiredData: ISalaryDistribute = {
  id: 9948,
  startDate: dayjs('2024-10-08'),
  endDate: dayjs('2024-10-07'),
  workDay: 17758,
  typeOfSalary: 'legislature',
};

export const sampleWithPartialData: ISalaryDistribute = {
  id: 28134,
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-07'),
  workDay: 18447,
  typeOfSalary: 'strait midst sustenance',
};

export const sampleWithFullData: ISalaryDistribute = {
  id: 32630,
  startDate: dayjs('2024-10-08'),
  endDate: dayjs('2024-10-07'),
  workDay: 31943,
  typeOfSalary: 'afore',
};

export const sampleWithNewData: NewSalaryDistribute = {
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-08'),
  workDay: 2422,
  typeOfSalary: 'which regarding er',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
