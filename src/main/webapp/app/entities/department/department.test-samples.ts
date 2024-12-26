import { IDepartment, NewDepartment } from './department.model';

export const sampleWithRequiredData: IDepartment = {
  id: 21888,
  departmentName: 'although',
  address: 'fooey',
};

export const sampleWithPartialData: IDepartment = {
  id: 31360,
  departmentName: 'airport',
  address: 'gah supposing',
};

export const sampleWithFullData: IDepartment = {
  id: 9706,
  departmentName: 'out since into',
  address: 'through',
};

export const sampleWithNewData: NewDepartment = {
  departmentName: 'genuine where writhing',
  address: 'kiddingly diligently fiddle',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
