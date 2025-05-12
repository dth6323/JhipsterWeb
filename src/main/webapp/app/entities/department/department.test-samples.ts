import { IDepartment, NewDepartment } from './department.model';

export const sampleWithRequiredData: IDepartment = {
  id: 4357,
  departmentName: 'off triumphantly',
  address: 'metabolise physical happily',
};

export const sampleWithPartialData: IDepartment = {
  id: 12701,
  departmentName: 'because',
  address: 'humidity from',
};

export const sampleWithFullData: IDepartment = {
  id: 10259,
  departmentName: 'when',
  address: 'hmph fraudster',
};

export const sampleWithNewData: NewDepartment = {
  departmentName: 'whereas',
  address: 'saw finally',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
