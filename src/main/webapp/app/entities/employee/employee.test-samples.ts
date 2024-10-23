import dayjs from 'dayjs/esm';

import { IEmployee, NewEmployee } from './employee.model';

export const sampleWithRequiredData: IEmployee = {
  id: 20859,
  name: 'electronics',
  phone: '942-389-2008 x89121',
  email: 'Delta_West87@gmail.com',
  address: 'past',
  gender: 12800,
  dateOfBirth: dayjs('2024-10-07'),
};

export const sampleWithPartialData: IEmployee = {
  id: 18199,
  name: 'onto wildly focalise',
  phone: '(841) 906-3127 x316',
  email: 'Vance81@yahoo.com',
  address: 'these',
  gender: 7805,
  dateOfBirth: dayjs('2024-10-07'),
};

export const sampleWithFullData: IEmployee = {
  id: 7376,
  name: 'irritably irk never',
  phone: '204-470-8439 x818',
  email: 'Hugh.Brekke@gmail.com',
  address: 'markup atop splay',
  gender: 24627,
  dateOfBirth: dayjs('2024-10-07'),
};

export const sampleWithNewData: NewEmployee = {
  name: 'finally',
  phone: '1-553-712-4927 x973',
  email: 'Lazaro3@yahoo.com',
  address: 'agitated hype disposer',
  gender: 29516,
  dateOfBirth: dayjs('2024-10-08'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
