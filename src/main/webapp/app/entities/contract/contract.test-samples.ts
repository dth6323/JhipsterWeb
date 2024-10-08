import dayjs from 'dayjs/esm';

import { IContract, NewContract } from './contract.model';

export const sampleWithRequiredData: IContract = {
  id: 9662,
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-07'),
  status: 'embody dress republican',
};

export const sampleWithPartialData: IContract = {
  id: 11424,
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-07'),
  status: 'above healthily',
};

export const sampleWithFullData: IContract = {
  id: 29363,
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-07'),
  status: 'hastily hollow',
};

export const sampleWithNewData: NewContract = {
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-07'),
  status: 'reel receptor crank',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
