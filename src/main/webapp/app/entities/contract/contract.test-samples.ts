import dayjs from 'dayjs/esm';

import { IContract, NewContract } from './contract.model';

export const sampleWithRequiredData: IContract = {
  id: 17673,
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-07'),
  status: 'exonerate going nippy',
};

export const sampleWithPartialData: IContract = {
  id: 5023,
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-07'),
  status: 'why',
};

export const sampleWithFullData: IContract = {
  id: 17066,
  startDate: dayjs('2024-10-07'),
  endDate: dayjs('2024-10-07'),
  status: 'unhealthy',
};

export const sampleWithNewData: NewContract = {
  startDate: dayjs('2024-10-08'),
  endDate: dayjs('2024-10-07'),
  status: 'annex narrate and',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
