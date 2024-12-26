import { IWage, NewWage } from './wage.model';

export const sampleWithRequiredData: IWage = {
  id: 27195,
  coefficients: 11713.06,
  baseSalary: 26987.92,
  allowance: 29975.94,
};

export const sampleWithPartialData: IWage = {
  id: 22079,
  coefficients: 24277.78,
  baseSalary: 15085.5,
  allowance: 9956.09,
};

export const sampleWithFullData: IWage = {
  id: 26073,
  coefficients: 6487.22,
  baseSalary: 32422.18,
  allowance: 21029.82,
};

export const sampleWithNewData: NewWage = {
  coefficients: 4675.37,
  baseSalary: 24664.36,
  allowance: 9910.27,
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
