import dayjs from 'dayjs/esm';

import { IEmployee, NewEmployee } from './employee.model';

export const sampleWithRequiredData: IEmployee = {
  id: 13808,
  name: 'as',
  phone: '(471) 436-4903',
  email: 'Cora.Schaefer94@hotmail.com',
  address: 'brr',
  gender: 2948,
  dateOfBirth: dayjs('2024-10-07'),
};

export const sampleWithPartialData: IEmployee = {
  id: 1708,
  name: 'huzzah gradient velvety',
  phone: '238-832-3863 x9999',
  email: 'Herminio72@hotmail.com',
  address: 'gulp now the',
  gender: 13101,
  dateOfBirth: dayjs('2024-10-07'),
};

export const sampleWithFullData: IEmployee = {
  id: 9031,
  name: 'outlaw',
  phone: '342.261.7707 x652',
  email: 'Keaton_Mosciski17@gmail.com',
  address: 'plugin',
  gender: 28987,
  dateOfBirth: dayjs('2024-10-07'),
};

export const sampleWithNewData: NewEmployee = {
  name: 'though',
  phone: '452-223-0968 x6932',
  email: 'Wilma_Rowe@gmail.com',
  address: 'flint after government',
  gender: 30164,
  dateOfBirth: dayjs('2024-10-07'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
