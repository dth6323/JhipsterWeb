import { IWage, NewWage } from './wage.model';

export const sampleWithRequiredData: IWage = {
  id: 28367,
  coefficients: 17113.24,
  baseSalary: 24170.73,
  allowance: 11095.6,
};

export const sampleWithPartialData: IWage = {
  id: 3152,
  coefficients: 12557.58,
  baseSalary: 22263.32,
  allowance: 11522.6,
};

export const sampleWithFullData: IWage = {
  id: 28426,
  coefficients: 2529.05,
  baseSalary: 13618.44,
  allowance: 13694.03,
};

export const sampleWithNewData: NewWage = {
  coefficients: 26123.68,
  baseSalary: 21280.32,
  allowance: 31185.63,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
